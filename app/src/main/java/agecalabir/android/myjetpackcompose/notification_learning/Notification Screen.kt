package agecalabir.android.myjetpackcompose.notification_learning

import agecalabir.android.myjetpackcompose.jetpackcompose.wineash
import agecalabir.android.myjetpackcompose.ui.theme.Poppins
import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Notification_Screen(){
    val context = LocalContext.current


    // Android 13+ এর জন্য নোটিফিকেশন পারমিশন হ্যান্ডলার
    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted->
        if(isGranted){
            showBasicNotification(context)
        } else{
            Toast.makeText(context,"নোটিফিকেশন পারমিশন প্রয়োজন!", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Notification Screen",
            fontSize = 24.sp,
            color = wineash,
            fontFamily = Poppins
        )
        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                } else {
                    showBasicNotification(context)
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = wineash,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text  = "Send Notification",
                fontWeight = FontWeight.SemiBold,
                fontFamily = Poppins
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                showProgressNotification(context)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2563EB),
                contentColor = Color.White
            )
        ) {
            Text(text = "Start Download Progress")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { showDirectReplyNotification(context) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF10B981),
                contentColor = Color.White
            )
        ) {
            Text(text = "Direct Reply Notification")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { showHeadsUpNotification(context) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFDC2626),
                contentColor = Color.White
            )
        ) {
            Text(text = "Show Heads-up Notification")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { showGroupedNotifications(context) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF8B5CF6),
                contentColor = Color.White
            )
        ) {
            Text(text = "Show Grouped Notifications")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { showCustomLayoutNotification(context) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0F172A),
                contentColor = Color.White
            )
        ) {
            Text(text = "Show Custom Layout Notification")
        }
    }
}