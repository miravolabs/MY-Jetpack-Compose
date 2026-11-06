package agecalabir.android.myjetpackcompose.navigation

/*import agecalabir.android.myjetpackcompose.jetpackcompose.wineash
import agecalabir.android.myjetpackcompose.ui.theme.Poppins
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomeScreen(
    userName : String ,
    onNavigationToSettingScreen: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
          text =   "MiraChat",
            color = wineash,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 60.sp
        )
        Text(
            text = "Home Screen",
            fontWeight = FontWeight.SemiBold,
            fontFamily = Poppins,
            color = wineash,
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text ="Welcome $userName",
            fontSize = 20.sp,
            fontFamily = Poppins,
            color = wineash,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedButton(
            onClick = onNavigationToSettingScreen ,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = wineash
            ),
            border = BorderStroke(1.dp,wineash),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
        ) {
            Text("Go Setting Screen", fontFamily = Poppins, fontWeight = FontWeight.Medium)
        }
        Spacer(modifier = Modifier.height(22.dp))
        Text(
            text = "Miravolabs",
            fontFamily = Poppins,
            fontSize = 32.sp,
            fontWeight = FontWeight.Medium,
            color = wineash
        )
    }
}*/