package agecalabir.android.myjetpackcompose.UsageAccess

import agecalabir.android.myjetpackcompose.animation_learn.TypewriterText
import agecalabir.android.myjetpackcompose.jetpackcompose.wineash
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun UsageScreen() {
    val context = LocalContext.current
    var liveAppName by remember { mutableStateOf("Checking...") }

    LaunchedEffect(Unit) {
        while (isActive) {
            if (SimpleAppTracker.hasPermission(context)) {
                liveAppName = SimpleAppTracker.getForegroundApp(context).appName
            } else {
                liveAppName = "Permission Needed"
            }
            delay(1000)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Currently Detected App:", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(6.dp))
                TypewriterText(
                    text = liveAppName,
                    color = wineash,
                    cursorColor = wineash,
                    trigger = 0
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { SimpleAppTracker.openUsageSettings(context) },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE11D48)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "1. Grant Usage Access")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { SimpleAppTracker.openOverlaySettings(context) },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "2. Grant Overlay Permission")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (!SimpleAppTracker.hasPermission(context)) {
                    Toast.makeText(context, "Usage Access পারমিশন দিন!", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (!Settings.canDrawOverlays(context)) {
                    Toast.makeText(context, "Display Overlay পারমিশন দিন!", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                val intent = Intent(context, FloatingOverlayService::class.java)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(intent)
                } else {
                    context.startService(intent)
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = wineash),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "3. Start Live Tracker Overlay")
        }
    }
}
/*@Composable
fun UsageScreen() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var hasUsagePermission by remember {
        mutableStateOf(AppUsageTrackerEngine.hasUsageStatsPermission(context))
    }
    var hasOverlayPermission by remember {
        mutableStateOf(AppUsageTrackerEngine.hasOverlayPermission(context))
    }
    val isOverlayRunning by FloatingOverlayService.isServiceRunning.collectAsState()

    // ইউজার সেটিংস থেকে পারমিশন দিয়ে ফিরলেই অটোমেটিক বাটন আপডেট হবে
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                hasUsagePermission = AppUsageTrackerEngine.hasUsageStatsPermission(context)
                hasOverlayPermission = AppUsageTrackerEngine.hasOverlayPermission(context)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    // ইঞ্জিনের Flow থেকে সরাসরি রিয়েল-টাইম অ্যাপ ইনফো কালেক্ট করা
    val currentApp by remember(context, hasUsagePermission) {
        AppUsageTrackerEngine.observeForegroundApp(context, pollIntervalMs = 700L)
    }.collectAsState(
        initial = ForegroundAppInfo(appName = "Checking...", packageName = "")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // লাইভ স্ট্যাটাস ড্যাশবোর্ড কার্ড
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9)),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "REAL-TIME FOREGROUND MONITOR",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF64748B)
                )

                Spacer(modifier = Modifier.height(14.dp))

                // অ্যাপের আসল লোগো
                if (currentApp.appIcon != null) {
                    Image(
                        bitmap = currentApp.appIcon!!,
                        contentDescription = currentApp.appName,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(14.dp))
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(wineash.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = currentApp.appName.take(1).uppercase(),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = wineash
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // অ্যাপের আসল নাম
                Text(
                    text = currentApp.appName,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = wineash
                )

                // প্যাকেজ নেম এবং অ্যাপ টাইপ ব্যাজ
                if (currentApp.packageName.isNotEmpty() && currentApp.packageName != "no_permission") {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = currentApp.packageName,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        color = if (currentApp.isSystemApp) Color(0xFFE2E8F0) else Color(0xFFDCFCE7),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(
                            text = if (currentApp.isScreenLocked) "Screen Locked"
                            else if (currentApp.isSystemApp) "System Application"
                            else "Installed User App",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (currentApp.isSystemApp) Color(0xFF475569) else Color(0xFF15803D),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // ১. Usage Access পারমিশন বাটন
        Button(
            onClick = { AppUsageTrackerEngine.openUsageAccessSettings(context) },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (hasUsagePermission) Color(0xFF10B981) else Color(0xFFE11D48)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = if (hasUsagePermission) "✓ Usage Access Permission Granted" else "1. Grant Usage Access Permission",
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // ২. Overlay পারমিশন বাটন
        Button(
            onClick = { AppUsageTrackerEngine.openOverlaySettings(context) },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (hasOverlayPermission) Color(0xFF10B981) else Color(0xFF2563EB)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = if (hasOverlayPermission) "✓ Overlay Permission Granted" else "2. Grant Overlay Permission",
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ৩. ওভারলে স্টার্ট / স্টপ বাটন
        Button(
            onClick = {
                if (!hasUsagePermission) {
                    Toast.makeText(context, "আগে Usage Access পারমিশন অন করুন!", Toast.LENGTH_SHORT).show()
                    AppUsageTrackerEngine.openUsageAccessSettings(context)
                    return@Button
                }
                if (!hasOverlayPermission) {
                    Toast.makeText(context, "আগে Display Overlay পারমিশন অন করুন!", Toast.LENGTH_SHORT).show()
                    AppUsageTrackerEngine.openOverlaySettings(context)
                    return@Button
                }

                val serviceIntent = Intent(context, FloatingOverlayService::class.java)
                if (isOverlayRunning) {
                    context.stopService(serviceIntent)
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        context.startForegroundService(serviceIntent)
                    } else {
                        context.startService(serviceIntent)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isOverlayRunning) Color(0xFFDC2626) else wineash
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = if (isOverlayRunning) "Stop Live Tracker Overlay" else "3. Start Live Tracker Overlay",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

*/

