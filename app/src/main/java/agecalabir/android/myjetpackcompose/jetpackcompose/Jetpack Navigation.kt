package agecalabir.android.myjetpackcompose.jetpackcompose

import agecalabir.android.myjetpackcompose.ui.theme.Poppins
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen{
    @Serializable
    data object ScreenA : Screen
    @Serializable
    data object ScreenB : Screen
    @Serializable
    data object ScreenC : Screen
}
@Composable
fun ScreenA(onNavigationToB :() -> Unit){
    Column(
        modifier = Modifier.fillMaxSize().padding(18.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("My first Screen", fontFamily = Poppins, fontWeight = FontWeight.Medium, fontSize = 36.sp)
        Spacer(modifier= Modifier.height(20.dp))
        Button(
            onClick = onNavigationToB,
            colors = ButtonDefaults.buttonColors(containerColor = wineash)
        ){
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
                , horizontalArrangement = Arrangement.Center
            ){
                Text("Go ScreenB", fontFamily = Poppins)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null
                )
            }
        }
    }
}
@Composable
fun ScreenB(
    onNavigationBackToA: () -> Unit,
    onNavigationToC : () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(18.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "My Secend Screen",
            fontSize = 36.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Button(onClick = onNavigationBackToA,
                colors = ButtonDefaults.buttonColors(containerColor = wineash)){
                Row(horizontalArrangement = Arrangement.Center){
                   Icon(
                       imageVector = Icons.Default.ArrowBack,
                       contentDescription = null,
                       modifier = Modifier.size(30.dp)
                   )
                    Text("Back A", fontFamily = Poppins, fontSize = 20.sp)
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onNavigationToC,
                colors = ButtonDefaults.buttonColors(containerColor = wineash)) {
                Row(horizontalArrangement = Arrangement.Center){
                    Text("Go To C", fontFamily = Poppins, fontSize = 20.sp)
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}
@Composable
fun ScreenC( onNavigationBackToB : () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize().padding(18.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("My Screen C", fontSize = 36.sp, fontFamily = Poppins, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onNavigationBackToB
        , colors = ButtonDefaults.buttonColors(containerColor = wineash)){
            Row(horizontalArrangement = Arrangement.Center){
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
                Text("Back to B", fontFamily = Poppins, fontSize = 20.sp)
            }
        }
    }
}
@Preview
@Composable
fun MyApp(){
    val navController =  rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.ScreenA
    ) {
        composable<Screen.ScreenA>{
            ScreenA(onNavigationToB = {
                navController.navigate(Screen.ScreenB){
                    launchSingleTop = true
                }
            }
            )
        }
        composable<Screen.ScreenB>{
            ScreenB(
                onNavigationToC = {
                    navController.navigate(Screen.ScreenC){
                        launchSingleTop = true
                    }
                },
                onNavigationBackToA = {
                    navController.navigateUp()
                }
            )
        }
        composable<Screen.ScreenC>{
            ScreenC(
                onNavigationBackToB = {
                    navController.navigateUp()
                }
            )
        }
    }
}