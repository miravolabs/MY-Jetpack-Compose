package agecalabir.android.myjetpackcompose.notification_learning

import agecalabir.android.myjetpackcompose.MainActivity
import agecalabir.android.myjetpackcompose.R
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
//  for Android 13+ user notification permission
@Composable
fun RequestNotificationPermission(){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = {isGranted ->}

        )
        LaunchedEffect(Unit) {
            launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}

*/

// Notification Helper Function
// basic notification Learning
fun showBasicNotification(context: Context) {
    val channelId = "my_app_channel_id"
    val channelName = "App Notification Channel"

    // ১. Notification Channel তৈরি (শুধু Android 8.0+ এর জন্য)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Channel for general app notification"
        }
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    } // <-- চ্যানেল তৈরির if ব্লক এখানেই শেষ হবে

    // ২. PendingIntent তৈরি করা
    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    val pendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_IMMUTABLE
    )

    // res/drawable ফোল্ডারের যেকোনো ছবিকে Bitmap এ রূপান্তর করা
    val largeImageBitmap = BitmapFactory.decodeResource(
        context.resources,
        agecalabir.android.myjetpackcompose.R.drawable.abir_pic // অথবা তোমার R.drawable.my_image
    )
    // ৩. Notification Build করা
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(android.R.drawable.ic_dialog_info)
        .setContentTitle("Notun Notish")
        .setContentText("ট্যাপ করে অ্যাপে ফিরে যান....")
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText("আমাদের নতুন আপডেটে এসেছে ডার্ক মোড, ফায়ারবেস ক্লাউড সিঙ্ক এবং আরও দ্রুত কাজ করার সুবিধা! এখনই ঘুরে দেখুন।")
        )
        // বড় ব্যানার ইমেজ স্টাইল
        .setStyle(
            NotificationCompat.BigPictureStyle()
                .bigPicture(largeImageBitmap)
                .setSummaryText("ছবির বিস্তারিত বিবরণ")
        )
        // ডানপাশে ছোট থাম্বনেইল হিসেবে দেখাতে চাইলে
        .setLargeIcon(largeImageBitmap)
        .setContentIntent(pendingIntent)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setAutoCancel(true)
        .addAction(
            android.R.drawable.ic_menu_view,
            "ওপেন করুন",
            pendingIntent
        )

    // ৪. নোটিফিকেশন শো করা (সঠিক পারমিশন চেক সহ)
    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED || Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
        ) {
            notify(101, builder.build())
        }
    }
}


// Progress Bar Notification
fun showProgressNotification(context: Context) {
    val channelId = "my_app_channel_id"
    val notificationId = 102
    val maxProgress = 100

    val notificationManager = NotificationManagerCompat.from(context)

    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(android.R.drawable.stat_sys_download)
        .setContentTitle("ফাইল ডাউনলোড হচ্ছে...")
        .setContentText("ডাউনলোড শুরু হচ্ছে")
        .setPriority(NotificationCompat.PRIORITY_LOW)
        .setOngoing(true)
        .setOnlyAlertOnce(true)

    CoroutineScope(Dispatchers.IO).launch {
        // ১. প্রগ্রেস বার বাড়ানো
        for (progress in 0..maxProgress step 10) {
            builder.setProgress(maxProgress, progress, false)
                .setContentText("$progress% সম্পন্ন হয়েছে")

            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED || Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
            ) {
                notificationManager.notify(notificationId, builder.build())
            }

            delay(500)
        }

        // ২. লুপ শেষ হওয়ার পর (১০০% পার হয়ে গেলে) করুটিনের ভেতরেই নোটিফিকেশন আপডেট হবে
        builder.setContentTitle("ডাউনলোড সম্পন্ন!")
            .setContentText("ফাইলটি মেমরিতে সেভ হয়েছে")
            .setProgress(0, 0, false) // প্রগ্রেস বার রিমুভ করা
            .setOngoing(false) // এখন ইউজার সোয়াইপ করে ডিলিট করতে পারবে
            .setSmallIcon(android.R.drawable.stat_sys_download_done)

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED || Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
        ) {
            notificationManager.notify(notificationId, builder.build())
        }
    } // <-- CoroutineScope এর ব্র্যাকেট এখানে শেষ হবে
}




// Direct Reply Helper Function

fun showDirectReplyNotification(context: Context) {
    val channelId = "my_app_channel_id"

    val remoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
        setLabel("Type your reply here...")
        build()
    }

    val replyIntent = Intent(context, DirectReplyReceiver::class.java)
    val replyPendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        replyIntent,
        PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    val replyAction = NotificationCompat.Action.Builder(
        android.R.drawable.ic_menu_send,
        "Reply",
        replyPendingIntent
    ).addRemoteInput(remoteInput).build()

    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(android.R.drawable.ic_dialog_email)
        .setContentTitle("Shakib")
        .setContentText("Dost, ajke ki class ache?")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .addAction(replyAction)
        .setAutoCancel(true)

    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED || Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
        ) {
            notify(REPLY_NOTIFICATION_ID, builder.build())
        }
    }
}

// Heads-up ফাংশন

fun showHeadsUpNotification(context: Context) {

    val channelId = "heads_up_channel_id"
    val channelName = "Urgent Notifications"
    val notificationId = 104

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Channel for urgent alerts and popups"
            enableVibration(true)
        }
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(android.R.drawable.ic_dialog_alert)
        .setContentTitle("জরুরি সিকিউরিটি অ্যালার্ট!")
        .setContentText("আপনার অ্যাকাউন্টে নতুন ডিভাইস থেকে লগইন করা হয়েছে।")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setDefaults(NotificationCompat.DEFAULT_ALL)
        .setAutoCancel(true)


    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED || Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
        ) {
            notify(notificationId, builder.build())
        }
    }
}

// Notification Grouping

fun showGroupedNotifications(context: Context) {
    val channelId = "my_app_channel_id"
    val groupKey = "com.agecalabir.android.EMAIL_GROUP"

    val notificationManager = NotificationManagerCompat.from(context)

    // ১. প্রথম মেসেজ নোটিফিকেশন
    val notification1 = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(android.R.drawable.ic_dialog_email)
        .setContentTitle("রাকিব")
        .setContentText("দোস্ত, কালকের অ্যাসাইনমেন্ট জমা দিয়েছিস?")
        .setGroup(groupKey)
        .setAutoCancel(true)
        .build()

    // ২. দ্বিতীয় মেসেজ নোটিফিকেশন
    val notification2 = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(android.R.drawable.ic_dialog_email)
        .setContentTitle("তানভীর")
        .setContentText("আজকে বিকেলে মাঠে আসবি?")
        .setGroup(groupKey)
        .setAutoCancel(true)
        .build()

    // ৩. সামারি নোটিফিকেশন (পুরো গ্রুপকে একত্রে ধরে রাখার জন্য)
    val summaryNotification = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(android.R.drawable.ic_dialog_email)
        .setStyle(
            NotificationCompat.InboxStyle()
                .addLine("রাকিব: দোস্ত, কালকের অ্যাসাইনমেন্ট...")
                .addLine("তানভীর: আজকে বিকেলে মাঠে আসবি?")
                .setBigContentTitle("২টি নতুন বার্তা")
                .setSummaryText("messages@group.com")
        )
        .setGroup(groupKey)
        .setGroupSummary(true) // এই লাইনটি এটিকে মূল বা সমষ্টিক নোটিফিকেশন বানায়
        .setAutoCancel(true)
        .build()

    // নোটিফিকেশনগুলো পাঠানো (সবার আলাদা আইডি হবে)
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED || Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
    ) {
        notificationManager.notify(105, notification1)
        notificationManager.notify(106, notification2)
        notificationManager.notify(107, summaryNotification)
    }
}

// কাস্টম নোটিফিকেশন ফাংশন

fun showCustomLayoutNotification(context: Context) {
    val channelId = "my_app_channel_id"
    val notificationId = 108

    // ১. RemoteViews ইনস্ট্যান্স তৈরি এবং ডেটা বাইন্ড করা
    val customView = RemoteViews(context.packageName, R.layout.custom_notification_layout).apply {
        setTextViewText(agecalabir.android.myjetpackcompose.R.id.notification_custom_title, "Abir's Track")
        setTextViewText(agecalabir.android.myjetpackcompose.R.id.notification_custom_text, "Jetpack Compose Podcast • 03:45")
    }

    // ২. Notification Builder তৈরি
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(android.R.drawable.ic_media_play)
        .setCustomContentView(customView) // কাস্টম ভিউ সেট করা
        .setStyle(NotificationCompat.DecoratedCustomViewStyle()) // অ্যান্ড্রয়েডের সিস্টেম ফ্রেমওয়ার্ক লুক বজায় রাখতে
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setAutoCancel(true)

    // ৩. নোটিফিকেশন শো করা
    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED || Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
        ) {
            notify(notificationId, builder.build())
        }
    }
}