package agecalabir.android.myjetpackcompose.navigation
/*import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
sealed interface App{
    @Serializable
    data object LoginScreen : App
    @Serializable
    data class HomeScreen( val userName : String) : App
    @Serializable
    data object SettingScreen : App
}
@Preview
@Composable()
fun MiraChatApp(){

    val navController = rememberNavController()
    NavHost(
            navController= navController,
            startDestination = App.LoginScreen,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }

    ){
        composable<App.LoginScreen>{
            LoginScreen(
                onNavigationToHomeScreen = { inputName ->
                    navController.navigate(App.HomeScreen(userName = inputName)){
                        popUpTo<App.LoginScreen>{inclusive = true}
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<App.HomeScreen>{backStackEntry ->
            val homeRoute = backStackEntry.toRoute<App.HomeScreen>()
            HomeScreen(
                userName = homeRoute.userName,
                onNavigationToSettingScreen = {
                    navController.navigate(App.SettingScreen){
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<App.SettingScreen>{
            SettingScreen(
                onNavigationToLoginScreen = {
                    navController.navigate(App.LoginScreen){
                        popUpTo<App.HomeScreen>{inclusive = true}
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}*/