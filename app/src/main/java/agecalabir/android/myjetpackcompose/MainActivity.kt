package agecalabir.android.myjetpackcompose

import agecalabir.android.myjetpackcompose.data_store.MasterViewModelScreen
import agecalabir.android.myjetpackcompose.ui.theme.MyJetpackComposeTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
      installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyJetpackComposeTheme {
                MasterViewModelScreen(viewModel = viewModel())
            }
        }
    }
}

/*
@Composable
fun CounterScreen(viewModel: CounterViewModel = viewModel()){
    val currentCount by viewModel.count.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ViewModel Counter",
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            color = wineash,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "$currentCount",
            fontSize = 48.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = Poppins,
            color = wineash
        )
        Row{
        Button(
            onClick = {viewModel.incrementCount()},
            colors = ButtonDefaults.buttonColors(containerColor = wineash),
            shape = RoundedCornerShape(10.dp)
        ){
            Text(
                text = "Increment (+1) ",
                fontFamily = Poppins,
                color = Color.White
            )
        }
            Spacer(modifier = Modifier.width(6.dp))
            Button(
                onClick = {viewModel.decrementCount()},
                colors = ButtonDefaults.buttonColors(containerColor = wineash),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Decrement (-1) ",
                    fontFamily = Poppins,
                    color = Color.White
                )
            }
        }
    }
}

*/
