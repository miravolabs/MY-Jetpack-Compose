package agecalabir.android.myjetpackcompose.notification_learning

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput

const val KEY_TEXT_REPLY = "key_text_reply"
const val REPLY_NOTIFICATION_ID = 103
const val CHANNEL_ID = "my_app_channel_id"

class DirectReplyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        val replyText = remoteInput?.getCharSequence(KEY_TEXT_REPLY)?.toString()

        if (!replyText.isNullOrEmpty()) {

            val repliedNotification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Reply sending successful!")
                .setContentText("You: $replyText")
                .build()

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(REPLY_NOTIFICATION_ID, repliedNotification)
        }
    }
}

