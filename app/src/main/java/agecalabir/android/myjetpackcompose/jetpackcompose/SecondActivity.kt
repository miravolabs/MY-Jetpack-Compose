package agecalabir.android.myjetpackcompose.jetpackcompose

import agecalabir.android.myjetpackcompose.ui.theme.Poppins
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val receivedName = intent.getStringExtra("USER_NAME") ?: "No Name"
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color(0xFFF9F9F9)),
                contentAlignment = Alignment.Center
            ){
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        text ="Welcome to second Screen",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = wineash
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Hello, $receivedName",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = wineash,
                        fontFamily = Poppins
                    )
                }
            }
        }
    }
}