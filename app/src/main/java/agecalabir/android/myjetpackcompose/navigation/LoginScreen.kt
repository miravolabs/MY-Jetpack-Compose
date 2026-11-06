package agecalabir.android.myjetpackcompose.navigation

/*import agecalabir.android.myjetpackcompose.jetpackcompose.wineash
import agecalabir.android.myjetpackcompose.ui.theme.Poppins
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(onNavigationToHomeScreen :(String) -> Unit){
    var name by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().padding(12.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "MiraChat",
            fontSize = 34.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = Poppins,
            color = wineash
        )
        Text(
            text = "Chat your friends with MiraChat",
            fontFamily = Poppins,
            color = wineash
        )
        Spacer(modifier = Modifier.height(30.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        ){
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text(
                    text = "Sign In with Name",
                    fontSize = 20.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = wineash,
                    modifier = Modifier.align(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth(),
                    value = name ,
                    onValueChange = { name = it},
                    shape = RoundedCornerShape(18.dp),
                    placeholder = { Text("Your Name ")},
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = wineash,
                        focusedBorderColor = wineash,
                        unfocusedTextColor = wineash,
                        unfocusedBorderColor = Color.Gray,
                        cursorColor = wineash
                    ),
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null ,
                            tint = wineash
                        )
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        if(name.isNotEmpty()){
                            onNavigationToHomeScreen(name)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = wineash),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
                    modifier = Modifier.fillMaxWidth().heightIn(),
                    enabled = name.isNotEmpty()
                ){
                    Text("Sign In with Name", fontFamily = Poppins)
                }
            }
        }
    }
}*/