package agecalabir.android.myjetpackcompose.screen_overlay_learning

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings

// এই ফাংশনটি চেক করবে ওভারলে পারমিশন দেওয়া আছে কিনা
fun checkOverlayPermissionAndRequest(activity: Context){
    if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
        // যদি পারমিশন না দেওয়া থাকে
        if(!Settings.canDrawOverlays(activity)){
            val intent = Intent(
Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:${activity.packageName}")
            )
            activity.startActivity(intent)
        } else {
            // পারমিশন দেওয়া আছে, এখন সার্ভিস স্টার্ট করতে পারবে
            startOverlayService(activity)
        }
    } else {
        // Android 6.0 এর নিচে অটোমেটিক পারমিশন থাকে
        startOverlayService(activity)
    }
}

// সার্ভিস স্টার্ট করার ফাংশন
fun startOverlayService(context: Context) {
    val intent = Intent(context, FloatingOverlayService::class.java)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        context.startForegroundService(intent)
    } else {
        context.startService(intent)
    }
}
fun stopOverlayService(context: Context) {
    val intent = Intent(context, FloatingOverlayService::class.java)
    context.stopService(intent)
}