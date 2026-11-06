package agecalabir.android.myjetpackcompose.navigationpractice

import agecalabir.android.myjetpackcompose.R
import agecalabir.android.myjetpackcompose.jetpackcompose.wineash
import agecalabir.android.myjetpackcompose.ui.theme.Poppins
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable


@Serializable
sealed interface MultiStepRoutes{
    @Serializable
    data object Splash : MultiStepRoutes
    @Serializable
    data object StepName : MultiStepRoutes
    @Serializable
    data object StepAcademic : MultiStepRoutes
    @Serializable
    data object StepAddress : MultiStepRoutes
    @Serializable
    data object StepDetails : MultiStepRoutes
    @Serializable
    data object FinalSummary : MultiStepRoutes
}

@Composable
fun MultiStepNavigationApp(
    sharedViewModel: StudentFormViewModel
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MultiStepRoutes.Splash
    ){
        composable<MultiStepRoutes.Splash>{
            CustomSplashScreen(
                onNavigationToNext = {
                    navController.navigate(MultiStepRoutes.StepName){
                        popUpTo(MultiStepRoutes.Splash){inclusive = true}
                    }
                }
            )
        }
        composable<MultiStepRoutes.StepName>{
            val persistentName by sharedViewModel.savedPersistentName.collectAsState()
            StepNameScreen(
                savedName = persistentName,
                onNext = {name ->
                    sharedViewModel.updateName(name)
                    navController.navigate(MultiStepRoutes.StepAcademic)
                }
            )
        }
        composable<MultiStepRoutes.StepAcademic>{
            StepAcademicScreen(
                onNext = {id, roll->
                    sharedViewModel.updateIdAndRoll(id,roll)
                    navController.navigate(MultiStepRoutes.StepAddress)
                }
            )
        }
        composable<MultiStepRoutes.StepAddress>{
            StepAddressScreen(
                onNext = {address ->
                    sharedViewModel.updateAddress(address)
                    navController.navigate(MultiStepRoutes.StepDetails)
                }
            )
        }
        composable<MultiStepRoutes.StepDetails>{
            StepDetailsScreen(
                onNext = {section , favSubject ->
                    sharedViewModel.updateSectionAndSubject(section,favSubject)
                    navController.navigate(MultiStepRoutes.FinalSummary)
                }
            )
        }
        composable<MultiStepRoutes.FinalSummary>{
            val state by sharedViewModel.uiState.collectAsState()
            FinalSummaryScreen(
                data = state,
                onRestart = {
                    sharedViewModel.resetForm()
                    navController.navigate(MultiStepRoutes.StepName){
                        popUpTo(MultiStepRoutes.StepName){inclusive = true}
                    }
                }
            )
        }
    }
}

// Splash Screen
@Composable
fun CustomSplashScreen(onNavigationToNext : ()-> Unit){
    val scale = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000)
        )
        delay(1500)
        onNavigationToNext()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(R.drawable.textbot_logotwo),
                contentDescription = "app logo",
                modifier = Modifier
                    .size(120.dp)
                    .scale(scale.value)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Welcome to App",
                fontFamily = Poppins,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = wineash
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Developed by Abir",
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = Color.Gray
            )
        }
    }

}

// Screen 1
@Composable
fun StepNameScreen(
    savedName : String,
    onNext:(String) -> Unit
){
    var name by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Step 1 , Your Name",
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            color = wineash,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Previously Saved: $savedName",
            fontSize = 14.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value =  name,
            onValueChange = {name = it},
            label = {Text("Name")},
            placeholder = {Text("Enter your name ")},
            singleLine = true,
            shape = RoundedCornerShape(2.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton(
            onClick = {
                if(name.isNotBlank()){
                    onNext(name)
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(2.dp),
            border = BorderStroke(2.dp,wineash)
        ) {
            Text(
                text  = "Next Academic Info",
                fontFamily = Poppins,
            fontWeight = FontWeight.Bold
            )
        }
    }
}
// Screen 2
@Composable
fun StepAcademicScreen(onNext:(String, String)-> Unit){
    var id by remember { mutableStateOf("") }
    var roll by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Step 2: Id & Roll",
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = wineash
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = id,
            onValueChange = {id = it},
            label = {Text("ID")},
            placeholder = {Text("Enter your id number")},
            singleLine = true,
            shape = RoundedCornerShape(2.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = roll,
            onValueChange = {roll = it},
            label = {Text("Roll")},
            placeholder = {Text("Enter your Roll")},
            singleLine = true,
            shape = RoundedCornerShape(2.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton(
            onClick = {
                if(id.isNotBlank() && roll.isNotBlank()){
                    onNext(id,roll)
                }
            },
            shape = RoundedCornerShape(2.dp),
            border = BorderStroke(2.dp,wineash),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                text = "Next Address Info",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
// Screen 3
@Composable
fun StepAddressScreen(onNext:(String) -> Unit){
    var address by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text =  "Step 3: Address",
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = wineash
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value =  address,
            onValueChange = {address = it},
            label = {Text("Address")},
            placeholder = {Text("Enter your address")},
            shape = RoundedCornerShape(2.dp),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton(
            onClick = {
                if(address.isNotBlank()){
                    onNext(address)
                }
            },
            shape = RoundedCornerShape(2.dp),
            border = BorderStroke(2.dp,wineash),
            modifier = Modifier.fillMaxWidth(0.8f   )
        ) {
            Text(
                text = "Next: Section & Subject",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
// Screen 4
@Composable
fun StepDetailsScreen(
    onNext: (String, String) -> Unit
){
    var section by remember { mutableStateOf("") }
    var favSubject by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Step 4: Section & Subject",
            fontSize = 22.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            color = wineash
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = section,
            onValueChange = {section= it},
            label = {Text("Section")},
            placeholder = {Text("Enter your  section name")},
            shape = RoundedCornerShape(2.dp),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f   )
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = favSubject,
            onValueChange = {favSubject = it},
            label = {Text("Subject")},
            placeholder = {Text("Enter your favarate subject name")},
            singleLine = true,
            shape = RoundedCornerShape(2.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton(
            onClick = {
                if(section.isNotBlank() && favSubject.isNotBlank()){
                    onNext(section,favSubject)
                }
            },
            shape = RoundedCornerShape(2.dp),
            modifier = Modifier.fillMaxWidth(0.8f),
            border = BorderStroke(2.dp,wineash)
        ) {
            Text(
                text = "Complete & View Summary",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
// Screen 5
@Composable
fun FinalSummaryScreen(
    data : StudentUIState,
    onRestart: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ){
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "All Information Here",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    color = wineash,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text( "Name: ${data.name}", fontSize = 16.sp)
                Text("Id: ${data.id}", fontSize = 16.sp)
                Text("Roll: ${data.roll}", fontSize = 16.sp)
                Text("Address: ${data.address}", fontSize = 16.sp)
                Text("Section: ${data.section}", fontSize = 16.sp)
                Text("Favorite Subject: ${data.favSubject}", fontSize = 16.sp)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton(
            onClick = onRestart,
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth(0.8f),
            border = BorderStroke(2.dp,wineash)
        ) {
            Text(
                text = "Restart Form",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold
            )
        }
    }
}