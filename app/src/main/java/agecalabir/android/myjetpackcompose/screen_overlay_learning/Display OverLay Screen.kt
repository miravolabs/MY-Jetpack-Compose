package agecalabir.android.myjetpackcompose.screen_overlay_learning

import agecalabir.android.myjetpackcompose.jetpackcompose.wineash
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Display_OverLay_Screen(){
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text ="Display OverLay Screen",
            fontSize = 26.sp,
            color = wineash
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { checkOverlayPermissionAndRequest(context) },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = wineash,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "ON OverLay"
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {stopOverlayService(context)},
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red.copy(alpha = 0.8f),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Off OverLay"
            )
        }
    }
}