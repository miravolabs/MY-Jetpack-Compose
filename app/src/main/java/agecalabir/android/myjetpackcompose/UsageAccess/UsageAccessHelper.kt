package agecalabir.android.myjetpackcompose.UsageAccess

import android.app.AppOpsManager
import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Process
import android.provider.Settings


// ডেটা মডেল: অ্যাপের নাম, প্যাকেজ, আইকন এবং অতিরিক্ত স্ট্যাটাস একসাথে রাখার জন্য

/*
data class ForegroundAppInfo(
    val appName: String,
    val packageName: String,
    val appIcon: ImageBitmap? = null,
    val isSystemApp: Boolean = false,
    val isScreenLocked: Boolean = false
)

object AppUsageTrackerEngine {

    private const val SHORT_LOOKBACK_MS = 15_000L          // ১৫ সেকেন্ড (রিয়েল-টাইম ইভেন্ট স্ক্যান)
    private const val EXTENDED_LOOKBACK_MS = 120_000L      // ২ মিনিট (মিড-লেয়ার ইভেন্ট স্ক্যান)
    private const val FALLBACK_STATS_WINDOW_MS = 3_600_000L // ১ ঘণ্টা (UsageStats ফলব্যাক)
    private const val MAX_ICON_SIZE_PX = 128               // মেমোরি বাঁচাতে আইকন ম্যাক্স সাইজ

    // থ্রেড-সেফ নাম ক্যাশ এবং OutOfMemoryError রোধে LruCache ভিত্তিক আইকন ক্যাশ
    private val appLabelCache = ConcurrentHashMap<String, String>()
    private val systemAppCache = ConcurrentHashMap<String, Boolean>()
    private val appIconCache = object : LruCache<String, ImageBitmap>(60) {}

    // ইউজার একই অ্যাপে অনেকক্ষণ স্থির থাকলে যেন "Unknown" না দেখায় তার জন্য লাস্ট স্টেট ক্যাশ
    @Volatile
    private var lastKnownPackage: String? = null

    // যে সিস্টেম ওভারলে প্যাকেজগুলো ফোরগ্রাউন্ড ট্র্যাকিংয়ে ইগনোর করা উচিত
    private val ignoredTransientPackages = setOf(
        "com.android.systemui",
        "android",
        "com.google.android.permissioncontroller",
        "com.android.packageinstaller"
    )

    // ১. Usage Access পারমিশন চেক
    fun hasUsageStatsPermission(context: Context): Boolean {
        val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as? AppOpsManager ?: return false
        val mode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            appOps.unsafeCheckOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                Process.myUid(),
                context.packageName
            )
        } else {
            @Suppress("DEPRECATION")
            appOps.checkOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                Process.myUid(),
                context.packageName
            )
        }
        return mode == AppOpsManager.MODE_ALLOWED
    }

    // ২. Overlay (Draw Over Other Apps) পারমিশন চেক
    fun hasOverlayPermission(context: Context): Boolean {
        return Settings.canDrawOverlays(context)
    }

    // ৩. সরাসরি নিজের অ্যাপের Usage Access সেটিংস পেজে নিয়ে যাওয়া
    fun openUsageAccessSettings(context: Context) {
        val appContext = context.applicationContext
        try {
            val intent = Intent(
                Settings.ACTION_USAGE_ACCESS_SETTINGS,
                Uri.parse("package:${appContext.packageName}")
            ).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            appContext.startActivity(intent)
        } catch (e: Exception) {
            try {
                val fallbackIntent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                appContext.startActivity(fallbackIntent)
            } catch (ex: Exception) {
                openAppDetailsSettings(appContext)
            }
        }
    }

    // ৪. সরাসরি নিজের অ্যাপের Overlay পারমিশন পেজে নিয়ে যাওয়া
    fun openOverlaySettings(context: Context) {
        val appContext = context.applicationContext
        try {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:${appContext.packageName}")
            ).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            appContext.startActivity(intent)
        } catch (e: Exception) {
            try {
                val fallbackIntent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                appContext.startActivity(fallbackIntent)
            } catch (ex: Exception) {
                openAppDetailsSettings(appContext)
            }
        }
    }

    private fun openAppDetailsSettings(context: Context) {
        runCatching {
            val intent = Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:${context.packageName}")
            ).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }
    }

    // ৫. ব্যাকগ্রাউন্ড থ্রেডে রিয়েল-টাইম ফোরগ্রাউন্ড অ্যাপের সম্পূর্ণ তথ্য বের করা
    suspend fun getCurrentForegroundApp(context: Context): ForegroundAppInfo = withContext(Dispatchers.Default) {
        val appContext = context.applicationContext

        // স্ক্রিন বন্ধ বা লক থাকলে আলাদাভাবে শনাক্ত করা
        if (isDeviceLockedOrScreenOff(appContext)) {
            return@withContext ForegroundAppInfo(
                appName = "Screen Off / Locked",
                packageName = "lockscreen",
                appIcon = null,
                isSystemApp = true,
                isScreenLocked = true
            )
        }

        val pkg = detectForegroundPackage(appContext)
        if (pkg.isNullOrEmpty()) {
            return@withContext ForegroundAppInfo(
                appName = "Home Screen / System",
                packageName = "system",
                appIcon = null,
                isSystemApp = true,
                isScreenLocked = false
            )
        }

        val appName = resolveAppName(appContext, pkg)
        val appIcon = resolveAppIcon(appContext, pkg)
        val isSystem = isSystemPackage(appContext, pkg)

        ForegroundAppInfo(
            appName = appName,
            packageName = pkg,
            appIcon = appIcon,
            isSystemApp = isSystem,
            isScreenLocked = false
        )
    }

    // ৬. Jetpack Compose-এ সরাসরি রিয়েল-টাইম স্ট্রিম হিসেবে ব্যবহার করার জন্য Flow
    fun observeForegroundApp(
        context: Context,
        pollIntervalMs: Long = 700L
    ): Flow<ForegroundAppInfo> = flow {
        val appContext = context.applicationContext
        val safeInterval = pollIntervalMs.coerceAtLeast(300L)
        while (currentCoroutineContext().isActive) {
            if (hasUsageStatsPermission(appContext)) {
                emit(getCurrentForegroundApp(appContext))
            } else {
                emit(
                    ForegroundAppInfo(
                        appName = "Permission Required",
                        packageName = "no_permission",
                        appIcon = null,
                        isSystemApp = true,
                        isScreenLocked = false
                    )
                )
            }
            delay(safeInterval)
        }
    }.distinctUntilChanged { old, new ->
        old.packageName == new.packageName &&
                old.isScreenLocked == new.isScreenLocked &&
                old.appName == new.appName
    }.flowOn(Dispatchers.Default)

    // ৭. ট্রিপল-লেয়ার ফোরগ্রাউন্ড প্যাকেজ ডিটেকশন লজিক
    private fun detectForegroundPackage(context: Context): String? {
        val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as? UsageStatsManager
            ?: return lastKnownPackage

        val endTime = System.currentTimeMillis()

        // লেয়ার ১: বিগত ১৫ সেকেন্ডের ইভেন্ট স্ক্যান করা
        val recentEventPkg = scanForegroundEvents(
            usageStatsManager = usageStatsManager,
            startTime = endTime - SHORT_LOOKBACK_MS,
            endTime = endTime
        )
        if (!recentEventPkg.isNullOrEmpty()) {
            lastKnownPackage = recentEventPkg
            return recentEventPkg
        }

        // লেয়ার ২: যদি ক্যাশ খালি থাকে, বিগত ২ মিনিটের ইভেন্ট স্ক্যান করা
        if (lastKnownPackage == null) {
            val extendedEventPkg = scanForegroundEvents(
                usageStatsManager = usageStatsManager,
                startTime = endTime - EXTENDED_LOOKBACK_MS,
                endTime = endTime
            )
            if (!extendedEventPkg.isNullOrEmpty()) {
                lastKnownPackage = extendedEventPkg
                return extendedEventPkg
            }
        }

        // লেয়ার ৩: ১ ঘণ্টার UsageStats ফলব্যাক
        if (lastKnownPackage == null) {
            val stats = runCatching {
                usageStatsManager.queryUsageStats(
                    UsageStatsManager.INTERVAL_DAILY,
                    endTime - FALLBACK_STATS_WINDOW_MS,
                    endTime
                )
            }.getOrNull()

            if (!stats.isNullOrEmpty()) {
                val recentStat = stats
                    .filter { it.lastTimeUsed > 0L && it.packageName !in ignoredTransientPackages }
                    .maxByOrNull { it.lastTimeUsed }

                if (recentStat != null) {
                    lastKnownPackage = recentStat.packageName
                }
            }
        }

        return lastKnownPackage
    }

    private fun scanForegroundEvents(
        usageStatsManager: UsageStatsManager,
        startTime: Long,
        endTime: Long
    ): String? {
        val usageEvents = runCatching {
            usageStatsManager.queryEvents(startTime, endTime)
        }.getOrNull() ?: return null

        val event = UsageEvents.Event()
        var latestPackage: String? = null
        var latestTimestamp = 0L

        while (usageEvents.hasNextEvent()) {
            usageEvents.getNextEvent(event)
            val pkg = event.packageName ?: continue
            if (pkg in ignoredTransientPackages) continue

            val isForegroundEvent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                event.eventType == UsageEvents.Event.ACTIVITY_RESUMED
            } else {
                @Suppress("DEPRECATION")
                event.eventType == UsageEvents.Event.MOVE_TO_FOREGROUND ||
                        event.eventType == UsageEvents.Event.ACTIVITY_RESUMED
            }

            if (isForegroundEvent && event.timeStamp >= latestTimestamp) {
                latestTimestamp = event.timeStamp
                latestPackage = pkg
            }
        }

        return latestPackage
    }

    // ৮. যেকোনো অ্যাপের আসল নাম (App Label) বের করার ৪-লেয়ার গ্যারান্টেড মেথড
    private fun resolveAppName(context: Context, packageName: String): String {
        appLabelCache[packageName]?.let { return it }

        val pm = context.packageManager

        // মেথড ১: ডিরেক্ট ApplicationInfo থেকে নাম নেওয়া
        val directLabel = runCatching {
            val appInfo = getAppInfoCompat(pm, packageName)
            pm.getApplicationLabel(appInfo).toString().trim()
        }.getOrNull()

        // মেথড ২: LauncherApps সার্ভিস থেকে নাম নেওয়া
        val launcherAppsLabel = if (directLabel.isNullOrBlank() || directLabel == packageName) {
            runCatching {
                val launcherApps = context.getSystemService(Context.LAUNCHER_APPS_SERVICE) as? LauncherApps
                val userManager = context.getSystemService(Context.USER_SERVICE) as? UserManager
                val profiles = userManager?.userProfiles.orEmpty()
                profiles.firstNotNullOfOrNull { userHandle ->
                    val activities = launcherApps?.getActivityList(packageName, userHandle)
                    activities?.firstOrNull()?.label?.toString()?.trim()
                }
            }.getOrNull()
        } else null

        // মেথড ৩: Launcher Intent Query করে নাম বের করা
        val intentLabel = if (directLabel.isNullOrBlank() && launcherAppsLabel.isNullOrBlank()) {
            runCatching {
                val launchIntent = pm.getLaunchIntentForPackage(packageName)
                val resolveInfo = launchIntent?.let { intent ->
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        pm.resolveActivity(intent, PackageManager.ResolveInfoFlags.of(0L))
                    } else {
                        @Suppress("DEPRECATION")
                        pm.resolveActivity(intent, 0)
                    }
                }
                resolveInfo?.loadLabel(pm)?.toString()?.trim()
            }.getOrNull()
        } else null

        // মেথড ৪: স্মার্ট ফলব্যাক ফরম্যাটিং
        val finalLabel = listOfNotNull(directLabel, launcherAppsLabel, intentLabel)
            .firstOrNull { it.isNotBlank() && it != packageName }
            ?: formatFallbackPackageName(packageName)

        if (finalLabel.isNotBlank()) {
            appLabelCache[packageName] = finalLabel
        }
        return finalLabel
    }

    // ৯. অ্যাপের আসল আইকন বের করা (LruCache ও সাইজ অপ্টিমাইজড)
    private fun resolveAppIcon(context: Context, packageName: String): ImageBitmap? {
        synchronized(appIconCache) {
            appIconCache.get(packageName)?.let { return it }
        }

        val pm = context.packageManager

        val drawable: Drawable? = runCatching {
            pm.getApplicationIcon(packageName)
        }.getOrElse {
            runCatching {
                val launcherApps = context.getSystemService(Context.LAUNCHER_APPS_SERVICE) as? LauncherApps
                val userManager = context.getSystemService(Context.USER_SERVICE) as? UserManager
                val userHandle = userManager?.userProfiles?.firstOrNull()
                if (launcherApps != null && userHandle != null) {
                    launcherApps.getActivityList(packageName, userHandle)
                        .firstOrNull()
                        ?.getIcon(0)
                } else null
            }.getOrNull()
        }

        if (drawable == null) return null

        return runCatching {
            val bitmap = drawableToOptimizedBitmap(drawable).asImageBitmap()
            synchronized(appIconCache) {
                appIconCache.put(packageName, bitmap)
            }
            bitmap
        }.getOrNull()
    }

    private fun drawableToOptimizedBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable && drawable.bitmap != null) {
            val existing = drawable.bitmap
            if (existing.width <= MAX_ICON_SIZE_PX && existing.height <= MAX_ICON_SIZE_PX) {
                return existing
            }
        }

        val rawWidth = if (drawable.intrinsicWidth > 0) drawable.intrinsicWidth else 96
        val rawHeight = if (drawable.intrinsicHeight > 0) drawable.intrinsicHeight else 96

        val width = rawWidth.coerceAtMost(MAX_ICON_SIZE_PX)
        val height = rawHeight.coerceAtMost(MAX_ICON_SIZE_PX)

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    private fun isSystemPackage(context: Context, packageName: String): Boolean {
        systemAppCache[packageName]?.let { return it }
        val isSystem = runCatching {
            val appInfo = getAppInfoCompat(context.packageManager, packageName)
            val mask = ApplicationInfo.FLAG_SYSTEM or ApplicationInfo.FLAG_UPDATED_SYSTEM_APP
            (appInfo.flags and mask) != 0
        }.getOrDefault(false)

        systemAppCache[packageName] = isSystem
        return isSystem
    }

    private fun getAppInfoCompat(pm: PackageManager, packageName: String): ApplicationInfo {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pm.getApplicationInfo(
                packageName,
                PackageManager.ApplicationInfoFlags.of(PackageManager.MATCH_UNINSTALLED_PACKAGES.toLong())
            )
        } else {
            @Suppress("DEPRECATION")
            pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
        }
    }

    private fun isDeviceLockedOrScreenOff(context: Context): Boolean {
        val powerManager = context.getSystemService(Context.POWER_SERVICE) as? PowerManager
        val isInteractive = powerManager?.isInteractive ?: true
        if (!isInteractive) return true

        val keyguardManager = context.getSystemService(Context.KEYGUARD_SERVICE) as? KeyguardManager
        return keyguardManager?.isKeyguardLocked == true
    }

    private fun formatFallbackPackageName(packageName: String): String {
        val knownPackages = mapOf(
            "com.whatsapp" to "WhatsApp",
            "com.whatsapp.w4b" to "WhatsApp Business",
            "com.google.android.dialer" to "Phone",
            "com.android.settings" to "Settings",
            "com.android.systemui" to "System UI",
            "com.google.android.youtube" to "YouTube",
            "com.facebook.katana" to "Facebook",
            "com.facebook.lite" to "Facebook Lite",
            "com.facebook.orca" to "Messenger",
            "com.instagram.android" to "Instagram",
            "com.android.chrome" to "Chrome",
            "com.google.android.gm" to "Gmail",
            "com.google.android.apps.maps" to "Google Maps",
            "com.google.android.apps.photos" to "Google Photos",
            "com.zhiliaoapp.musically" to "TikTok",
            "org.telegram.messenger" to "Telegram"
        )
        knownPackages[packageName]?.let { return it }

        val ignoredSegments = setOf("android", "google", "com", "org", "net", "app", "apps", "mobile")
        return packageName.split(".")
            .lastOrNull { it.lowercase() !in ignoredSegments && it.isNotBlank() }
            ?.replaceFirstChar { it.uppercase() }
            ?: packageName
    }

    fun clearCache() {
        appLabelCache.clear()
        systemAppCache.clear()
        synchronized(appIconCache) {
            appIconCache.evictAll()
        }
        lastKnownPackage = null
    }
}

// Open app tracking engine simple code

object SimpleAppTracker {

    data class AppInfo(
        val appName: String,
        val packageName: String
    )

    // 1. Permission check - eita must
    fun hasPermission(context: Context): Boolean {
        val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            Process.myUid(),
            context.packageName
        )
        return mode == AppOpsManager.MODE_ALLOWED
    }

    // 2. Settings e pathano
    fun openSettings(context: Context) {
        val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    // 3. Main logic - kon app samne ache
    fun getForegroundApp(context: Context): AppInfo? {
        val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val endTime = System.currentTimeMillis()
        val startTime = endTime - 10_000 // last 10 sec check

        val events = usageStatsManager.queryEvents(startTime, endTime)
        val event = UsageEvents.Event()
        var lastPackage: String? = null

        while (events.hasNextEvent()) {
            events.getNextEvent(event)
            if (event.eventType == UsageEvents.Event.MOVE_TO_FOREGROUND) {
                lastPackage = event.packageName
            }
        }

        if (lastPackage == null) return null

        // package name theke app er sundor nam ber kora
        val pm = context.packageManager
        val appName = try {
            val appInfo = pm.getApplicationInfo(lastPackage, 0)
            pm.getApplicationLabel(appInfo).toString()
        } catch (e: Exception) {
            lastPackage // nam na pele package name e dekhao
        }

        return AppInfo(appName, lastPackage)
    }
}
*/
// function 3rd time

data class ForegroundAppInfo(
    val appName: String,
    val packageName: String
)

object SimpleAppTracker {

    private var lastKnownPackage: String? = null

    fun hasPermission(context: Context): Boolean {
        val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            appOps.unsafeCheckOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                Process.myUid(),
                context.packageName
            )
        } else {
            @Suppress("DEPRECATION")
            appOps.checkOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                Process.myUid(),
                context.packageName
            )
        }
        return mode == AppOpsManager.MODE_ALLOWED
    }

    fun openUsageSettings(context: Context) {
        val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    fun openOverlaySettings(context: Context) {
        val intent = Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            Uri.parse("package:${context.packageName}")
        ).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    fun getForegroundApp(context: Context): ForegroundAppInfo {
        val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val endTime = System.currentTimeMillis()
        val startTime = endTime - 10_000

        val events = usageStatsManager.queryEvents(startTime, endTime)
        val event = UsageEvents.Event()

        while (events.hasNextEvent()) {
            events.getNextEvent(event)
            if (event.eventType == UsageEvents.Event.ACTIVITY_RESUMED) {
                if (event.packageName != "com.android.systemui") {
                    lastKnownPackage = event.packageName
                }
            }
        }

        val currentPkg = lastKnownPackage ?: return ForegroundAppInfo("Home / Opening...", "")


        val pm = context.packageManager
        val appName = try {
            val appInfo = pm.getApplicationInfo(currentPkg, PackageManager.GET_META_DATA)
            pm.getApplicationLabel(appInfo).toString()
        } catch (e: Exception) {
            currentPkg
        }

        return ForegroundAppInfo(appName, currentPkg)
    }
}