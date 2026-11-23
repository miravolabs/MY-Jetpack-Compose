package agecalabir.android.myjetpackcompose.navigation_practice_master

import agecalabir.android.myjetpackcompose.ui.theme.Poppins
import android.net.Uri
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Switch
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf


@Serializable
sealed interface PracticeAppRoute{

    @Serializable
    data object HomeRoute

    @Serializable
    data object ProfileRoute

    @Serializable
    data object Settings
}

@Composable
fun AppNavigationPractice(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PracticeAppRoute.HomeRoute
    ) {
        composable<PracticeAppRoute.HomeRoute>{
            HomeScreen(
                onNavigationToProfile = {
                    navController.navigate(PracticeAppRoute.ProfileRoute)
                }
            )
        }
        composable<PracticeAppRoute.ProfileRoute>{
            ProfileScreen(
                onNavigationToSettings = {
                    navController.navigate(PracticeAppRoute.Settings){
                        launchSingleTop = true
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable<PracticeAppRoute.Settings>{
            SettingsScreen(
                onBackToHome = {
                    navController.navigate(PracticeAppRoute.HomeRoute){
                        popUpTo(PracticeAppRoute.HomeRoute){inclusive = true}
                    }
                }
            )
        }
    }

}

@Composable
fun HomeScreen(onNavigationToProfile: () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home Screen", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onNavigationToProfile) {
            Text("Go to Profile")
        }
    }
}

@Composable
fun ProfileScreen(onNavigationToSettings:() -> Unit,onBackClick: () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profile Screen", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onNavigationToSettings) {
            Text("Go to Settings")
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(onClick = onBackClick) {
            Text("Go Back")
        }
    }
}

@Composable
fun SettingsScreen(onBackToHome: () -> Unit){

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Settings Screen", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {onBackToHome()},
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
        ) {
            Text("Back to Home")
        }
    }
}

// Navigation Practice with data pass

@Serializable
sealed interface DataNavRoute{

    @Serializable
    data object HomeScreenRoute : DataNavRoute

    @Serializable
    data class ProfileScreenRoute(
        val userName : String,
        val userAge : Int
    ) : DataNavRoute
}

@Composable
fun DataNavigationMaster(){

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = DataNavRoute.HomeScreenRoute
    ){
        composable<DataNavRoute.HomeScreenRoute>{
            DataHomeScreen(
                onNavigationToProfile = {nameInput , ageInput ->
                    navController.navigate(
                        DataNavRoute.ProfileScreenRoute(
                            userName = nameInput,
                            userAge = ageInput
                        )
                    )
                }
            )
        }
        composable<DataNavRoute.ProfileScreenRoute>{backStackEntry ->

            val args = backStackEntry.toRoute<DataNavRoute.ProfileScreenRoute>()

            DataProfileScreen(
                name =  args.userName,
                age = args.userAge,
                onBack = {navController.popBackStack()}
            )
        }
    }
}

@Composable
fun DataHomeScreen(onNavigationToProfile: (String, Int) -> Unit){

    var inputName by remember { mutableStateOf("") }
    var inputAge by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sender Screen", fontSize = 28.sp)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = inputName,
            onValueChange = {inputName = it},
             label = {Text("Enter Name")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = inputAge,
            onValueChange = {inputAge = it},
            label = {Text("Enter Age")},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val ageInt = inputAge.toIntOrNull() ?: 0
                if(inputName.isNotBlank() && inputAge.isNotBlank()){
                    onNavigationToProfile(inputName,ageInt)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Send Data to Profile")
        }
    }
}

@Composable
fun DataProfileScreen(name: String,age: Int,onBack: ()-> Unit){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Receiver Screen", fontSize = 28.sp)

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ){
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text("Received Name: $name", fontSize = 18.sp)
                Text("Received Age: $age", fontSize = 18.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedButton(
            onClick = onBack
        ) {
            Text("Go Back")
        }
    }
}

// Navigation Practice

@Serializable
sealed interface ProductNavRoute{

    @Serializable
    data object AddProductRoute : ProductNavRoute

    @Serializable
    data class ProductDetailsRoute(
        val productId: Int,
        val productName : String,
        val productPrice: Float ,
        val isInStock: Boolean
    ) : ProductNavRoute
}

@Composable
fun ECommerceNavigation(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ProductNavRoute.AddProductRoute
    ){
        composable<ProductNavRoute.AddProductRoute>{
            AddProductScreen(
                onSendProduct = {id , name , price , isStock ->
                    navController.navigate(
                        route = ProductNavRoute.ProductDetailsRoute(
                            productId = id,
                            productName = name,
                            productPrice = price,
                            isInStock = isStock
                        )
                    )
                }
            )
        }
        composable<ProductNavRoute.ProductDetailsRoute>{backStackEntry ->

            val productInfo = backStackEntry.toRoute<ProductNavRoute.ProductDetailsRoute>()

            ProductDetailsScreen(
                id = productInfo.productId,
                name = productInfo.productName,
                price = productInfo.productPrice,
                isStock = productInfo.isInStock,
                onBack = {navController.popBackStack()}
            )
        }
    }
}

@Composable
fun AddProductScreen(onSendProduct: (Int, String, Float, Boolean) -> Unit){

    var inputId by remember { mutableStateOf("") }
    var inputName by remember { mutableStateOf("") }
    var inputPrice by remember { mutableStateOf("") }
    var isInStock by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Add New Product", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = inputId,
            onValueChange = {inputId  = it},
            label = {Text("Product ID")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = inputName,
            onValueChange = {inputName = it},
            label = {Text("Product Name")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value =  inputPrice,
            onValueChange = {inputPrice = it},
            label =  {Text("Price ($)")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("Is in Stock?" , fontSize = 16.sp)
            Switch(checked = isInStock, onCheckedChange = {isInStock = it})
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val idInt = inputId.toIntOrNull() ?: 0
                val priceFloat = inputPrice.toFloatOrNull() ?: 0.0f

                if(inputName.isNotBlank() && inputPrice.isNotBlank() && inputId.isNotBlank()){
                    onSendProduct(idInt,inputName,priceFloat,isInStock)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Product Details")
        }
    }
}

@Composable
fun ProductDetailsScreen(
    id : Int,
    name : String,
    price : Float,
    isStock : Boolean,
    onBack: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Product Details", fontSize = 24.sp , fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                if(isStock) MaterialTheme.colorScheme.primaryContainer
                else MaterialTheme.colorScheme.errorContainer
            )
        ){
            Column(modifier = Modifier.padding(16.dp)) {
                Text("ID: #$id", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Name: $name", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Price: $$price", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Status: ${if(isStock) "Available" else "Out of Stock"}")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        OutlinedButton(onClick = onBack) {
            Text("Back to Add Product")
        }
    }
}


// Practice 14-11-2026

@Serializable
sealed interface FoodAppNavRoute{

    @Serializable
    data object HomeRoute : FoodAppNavRoute

    @Serializable
    data class MenuRoute (
        val restaurantName : String,
        val rating: Float
    ) : FoodAppNavRoute

    @Serializable
    data class CheckoutRoute (
        val itemName : String,
        val price : Int,
        val quantity : Int
    ) : FoodAppNavRoute

    @Serializable
    data object Success : FoodAppNavRoute
}

@Composable
fun FoodApp(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = FoodAppNavRoute.HomeRoute
    ){
        composable<FoodAppNavRoute.HomeRoute>{
            HomeScreen(
                onNavigateToMenuScreen = {name , rating ->
                    navController.navigate(
                        FoodAppNavRoute.MenuRoute(
                            restaurantName = name,
                            rating = rating
                        )
                    )
                }
            )
        }

        composable<FoodAppNavRoute.MenuRoute>{backStackEntry ->

            val itemInfo = backStackEntry.toRoute<FoodAppNavRoute.MenuRoute>()

            MenuScreen(
                name = itemInfo.restaurantName,
                rating = itemInfo.rating,
                onNavigateToCheckOutScreen = {orderName , price , quantity ->
                    navController.navigate(
                        FoodAppNavRoute.CheckoutRoute(
                            itemName = orderName,
                            price = price,
                            quantity = quantity
                        )
                    )
                }
            )
        }

        composable<FoodAppNavRoute.CheckoutRoute>{backStackEntry ->

            val orderInfo = backStackEntry.toRoute<FoodAppNavRoute.CheckoutRoute>()

            CheckOutScreen(
                itemName = orderInfo.itemName,
                price = orderInfo.price,
                quantity = orderInfo.quantity,
                onNavigationToSuccessScreen = {
                    navController.navigate(FoodAppNavRoute.Success)
                }
            )
        }
        composable<FoodAppNavRoute.Success>{
            SuccessScreen(
                onBackToHome = {
                    navController.navigate(FoodAppNavRoute.HomeRoute){popUpTo(FoodAppNavRoute.HomeRoute){inclusive = true} }
                }
            )
        }
    }
}

// 1st Screen
@Composable
fun HomeScreen(onNavigateToMenuScreen: (String, Float) -> Unit){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Food APP Home Screen", fontSize = 32.sp, fontWeight = FontWeight.Bold, fontFamily = Poppins, color = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                onNavigateToMenuScreen("KFC" , 4.5f)
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Color.Blue.copy(alpha = 0.8f))
        ) {
            Text("KFC Menu", fontFamily = Poppins, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

// 2nd Screen
@Composable
fun MenuScreen(name : String,rating: Float,onNavigateToCheckOutScreen: (String, Int, Int) -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            )
        ){
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Text("Menu:", fontSize = 16.sp, fontFamily = Poppins, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Name: $name")
                Text("Rating: $rating")
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {onNavigateToCheckOutScreen("Zinger Burger",350,2)
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                Color.Blue.copy(alpha = 0.8f)
            )
        ) {
            Text("Order Burger", fontSize = 16.sp, fontFamily = Poppins, fontWeight = FontWeight.Bold)
        }
    }
}

// 3rd Screen

@Composable
fun CheckOutScreen(itemName : String,price: Int,quantity: Int,onNavigationToSuccessScreen: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainer)
        ){
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Text("Order Details:", fontSize = 16.sp, fontFamily = Poppins, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Order Name: $itemName")
                Text("Price: $$price/per Item")
                Text("Quantity: $quantity Pic")
                Text("Total Price: ${price * quantity} Dolor")
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {onNavigationToSuccessScreen()},
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue.copy(alpha = 0.8f)
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Confirm Order", fontFamily = Poppins, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

// 4th Screen

@Composable
fun SuccessScreen(onBackToHome: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Order Placed Successfully! \uD83C\uDF89", fontSize = 18.sp, fontFamily = Poppins, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {onBackToHome()},
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue.copy(alpha = 0.8f)
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Back to Home",fontFamily = Poppins, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

// Navigation Practice with Data Class pass

@Serializable
data class UserProfile(
    val id: Int,
    val name : String,
    val role : String,
    val isPremium: Boolean
)

@Serializable
sealed interface ObjectNavRoute{

    @Serializable
    data object StartRoute:  ObjectNavRoute

    @Serializable
    data class DetailsRoute(
        val user : UserProfile
    ): ObjectNavRoute
}

val UserProfileType = object  : NavType<UserProfile>(isNullableAllowed = false){

    override fun get(bundle: Bundle, key: String): UserProfile? {
        return bundle.getString(key)?.let { Json.decodeFromString<UserProfile>(it) }
    }

    override fun parseValue(value: String): UserProfile {
        return Json.decodeFromString(Uri.decode(value))
    }

    override fun serializeAsValue(value: UserProfile): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: UserProfile) {
        bundle.putString(key, Json.encodeToString(value))
    }
}

@Composable
fun ObjectNavigationMaster(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ObjectNavRoute.StartRoute
    ){
        composable<ObjectNavRoute.StartRoute>{
            StartScreen(
                onSendUser = {userObject ->
                    navController.navigate(ObjectNavRoute.DetailsRoute(user = userObject))
                }
            )
        }

        composable<ObjectNavRoute.DetailsRoute>(
            typeMap = mapOf(typeOf<UserProfile>() to UserProfileType)
        ) {backStackEntry ->
            val routeParms = backStackEntry.toRoute<ObjectNavRoute.DetailsRoute>()

            ObjectDetailsScreen(
                user = routeParms.user,
                onBack = {navController.popBackStack()}
            )
        }
    }
}

@Composable
fun StartScreen(onSendUser: (UserProfile) -> Unit){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Send Object \uD83D\uDCE6",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val dummyUser = UserProfile(
                    id = 101,
                    name = "Abir Hossen",
                    role = "Seniro Android Developer",
                    isPremium = true
                )
                onSendUser(dummyUser)
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Send User Object",fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ObjectDetailsScreen(user: UserProfile, onBack: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Object Details \uD83D\uDD0D", fontSize = 28.sp, fontWeight = FontWeight.Bold, fontFamily = Poppins)
        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        elevation = CardDefaults.cardElevation(8.dp)
        ){
            Column(modifier = Modifier.padding(20.dp)) {
                Text("User ID: #${user.id}", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Name: ${user.name}", fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Role: ${user.role}", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Premium Member: ${if(user.isPremium) "Yes ⭐" else "No"}")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedButton(
            onClick = {onBack()},
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("Go Back", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}


// Navigation Practice 22/11/2026

@Serializable
data class AppItem(
    val id: Int,
    val name : String ,
    val packageName : String,
    val version : String,
    val isPro : Boolean
)

@Serializable
sealed interface AppCatalogRoute{

    @Serializable
    data object CatalogHomeRoute : AppCatalogRoute

    @Serializable
    data class CatalogDetailsRoute(
        val app : AppItem
    ) : AppCatalogRoute
}


val AppItemNavType = object : NavType<AppItem>(isNullableAllowed = false){
    override fun get(bundle: Bundle, key: String): AppItem? {
        return bundle.getString(key)?.let{ Json.decodeFromString<AppItem>(it) }
    }

    override fun parseValue(value: String): AppItem {
        return Json.decodeFromString<AppItem>(Uri.decode(value))
    }

    override fun serializeAsValue(value: AppItem): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: AppItem) {
        bundle.putString(key, Json.encodeToString(value))
    }
}

@Composable
fun AppCatalogNavigation(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppCatalogRoute.CatalogHomeRoute
    ){
        composable<AppCatalogRoute.CatalogHomeRoute>{
            AppCatalogHomeScreen(
                onAppSelected = {selectedApp ->
                    navController.navigate(AppCatalogRoute.CatalogDetailsRoute(app = selectedApp)){
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<AppCatalogRoute.CatalogDetailsRoute>(
            typeMap = mapOf(typeOf<AppItem>() to AppItemNavType)
        ){ backStackEntry ->
            val routeData = backStackEntry.toRoute<AppCatalogRoute.CatalogDetailsRoute>()
            AppCatalogDetailsScreen(
                app = routeData.app,
                onBack = {
                   if(navController.previousBackStackEntry != null){
                       navController.popBackStack()
                   }
                }
            )
        }
    }
}




@Composable
fun AppCatalogHomeScreen(onAppSelected: (AppItem) -> Unit){

    val appList = listOf(
        AppItem(1,"TextBot","miravolabs.textbot.textbot","v1.5", false),
        AppItem(2,"MiraLink","com.miravolabs.miralink","v2.0",true),
        AppItem(3,"MiraChat","com.miravolabs.mirachat","v1.0",false)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .navigationBarsPadding()
            .statusBarsPadding()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Project Hub \uD83D\uDE80",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Select an app to view serialized configuration",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(appList){ app ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable{onAppSelected(app)},
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        contentColor = MaterialTheme.colorScheme.surface
                    )
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Column{
                            Text(
                                text = app.name,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = app.packageName,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            if(app.isPro){
                                Badge(
                                    containerColor = Color(0xFF2563EB),
                                    contentColor = Color.White
                                ) {
                                    Text("Pro", modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun AppCatalogDetailsScreen(app: AppItem, onBack : () -> Unit){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .navigationBarsPadding()
            .statusBarsPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            )
        ){
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "App Specifications",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                Text("ID: #${app.id}", fontSize = 15.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text("Name: ${app.name}", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(6.dp))
                Text("Package: ${app.packageName}", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text("Version: ${app.version}", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Status: ${if(app.isPro) "Active PRO License \uD83D\uDC8E" else "Standard Free Tier"}",
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Back to Hub", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}


// navigation practice with data class 22//11//2026

@Serializable
data class ServerNode(
    val serverName : String,
    val ipAddress: String,
    val port : Int,
    val ramGb : Int,
    val isSslActive : Boolean
)

@Serializable
sealed interface ServerNavRoute{

    @Serializable
    data object ConfigScreenRoute : ServerNavRoute

    @Serializable
    data class MonitorScreenRoute(
        val node : ServerNode
    ) : ServerNavRoute
}

val ServerNoteType = object : NavType<ServerNode>(isNullableAllowed = false){
    override fun get(bundle: Bundle, key: String): ServerNode? {
        return bundle.getString(key)?.let{ Json.decodeFromString<ServerNode>(it) }
    }

    override fun parseValue(value: String): ServerNode {
        return Json.decodeFromString<ServerNode>(Uri.decode(value))
    }

    override fun serializeAsValue(value: ServerNode): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: ServerNode) {
        bundle.putString(key, Json.encodeToString(value))
    }
}

@Composable
fun ServerDeployNavigation(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ServerNavRoute.ConfigScreenRoute
    ){
        composable<ServerNavRoute.ConfigScreenRoute>{
            ServerConfigScreen (
                onDeployNode = {createNode ->
                    navController.navigate(ServerNavRoute.MonitorScreenRoute(node = createNode)){
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<ServerNavRoute.MonitorScreenRoute>(
            typeMap = mapOf(typeOf<ServerNode>() to ServerNoteType)
        ){ backStackEntry ->
            val routeArgs = backStackEntry.toRoute<ServerNavRoute.MonitorScreenRoute>()
            ServerMonitorScreen(
                node = routeArgs.node,
                onDestroyNode = {
                    if(navController.previousBackStackEntry != null){
                        navController.popBackStack()
                    }
                }
            )
        }
    }
}

@Composable
fun ServerConfigScreen(onDeployNode: (ServerNode) -> Unit) {
    var name by remember { mutableStateOf("") }
    var ip by remember { mutableStateOf("") }
    var port by remember { mutableStateOf("8080") }
    var ram by remember { mutableStateOf("4") }
    var sslEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Deploy Node ⚙️",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Configure parameters to instantiate cluster node",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Server Tag / Name") },
            placeholder = { Text("e.g. Frankfurt-Worker-01") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = ip,
            onValueChange = { ip = it },
            label = { Text("Static IP Address") },
            placeholder = { Text("e.g. 192.168.1.100") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            OutlinedTextField(
                value = port,
                onValueChange = { port = it },
                label = { Text("Port") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp)
            )
            OutlinedTextField(
                value = ram,
                onValueChange = { ram = it },
                label = { Text("RAM (GB)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(10.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Enable SSL / TLS", fontWeight = FontWeight.Medium)
            Switch(checked = sslEnabled, onCheckedChange = { sslEnabled = it })
        }

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = {
                val parsedPort = port.toIntOrNull() ?: 8080
                val parsedRam = ram.toIntOrNull() ?: 4
                if (name.isNotBlank() && ip.isNotBlank()) {
                    val serverNode = ServerNode(
                        serverName = name,
                        ipAddress = ip,
                        port = parsedPort,
                        ramGb = parsedRam,
                        isSslActive = sslEnabled
                    )
                    onDeployNode(serverNode)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB))
        ) {
            Text("Launch Server Node 🚀", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}


@Composable
fun ServerMonitorScreen(node: ServerNode, onDestroyNode: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
            )
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Active Node Telemetry",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Badge(
                        containerColor = if (node.isSslActive) Color(0xFF16A34A) else Color(0xFFCA8A04)
                    ) {
                        Text(
                            text = if (node.isSslActive) "SECURE" else "UNENCRYPTED",
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                Text("Tag: ${node.serverName}", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(6.dp))
                Text("Endpoint: ${node.ipAddress}:${node.port}", fontSize = 15.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text("Allocated Memory: ${node.ramGb} GB LPDDR5", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text("Protocol: ${if (node.isSslActive) "HTTPS / WSS" else "HTTP / WS"}")
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        OutlinedButton(
            onClick = onDestroyNode,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFE11D48))
        ) {
            Text("Terminate & Return", fontWeight = FontWeight.Bold)
        }
    }
}


// navigation practice 23//11//2026

@Serializable
data class InvoiceVoucher(
    val invoiceId : String,
    val clientName : String,
    val serviceTitle : String,
    val subtotal : Double,
    val vatPercent : Float,
    val paymentGateway : String,
    val isSettled : Boolean
)

@Serializable
sealed interface InvoiceNavRoute{
    @Serializable
    data object CreateInvoiceRoute : InvoiceNavRoute

    @Serializable
    data class VoucherReceiptRoute(
        val voucher: InvoiceVoucher
    ) : InvoiceNavRoute
}

val InvoiceVoucherNavType = object : NavType<InvoiceVoucher>(isNullableAllowed = false){
    override fun get(bundle: Bundle, key: String): InvoiceVoucher? {
        return bundle.getString(key)?.let{ Json.decodeFromString<InvoiceVoucher>(it) }
    }

    override fun parseValue(value: String): InvoiceVoucher {
        return Json.decodeFromString<InvoiceVoucher>(Uri.decode(value))
    }

    override fun serializeAsValue(value: InvoiceVoucher): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: InvoiceVoucher) {
        bundle.putString(key, Json.encodeToString(value))
    }
}


@Composable
fun InvoiceSystemNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = InvoiceNavRoute.CreateInvoiceRoute
    ){
        composable<InvoiceNavRoute.CreateInvoiceRoute>{
            CreateInvoiceScreen(
                onGenerateVoucher = {voucherObject ->
                    navController.navigate(
                        InvoiceNavRoute.VoucherReceiptRoute(voucher = voucherObject)
                    ){
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<InvoiceNavRoute.VoucherReceiptRoute>(
            typeMap = mapOf(typeOf<InvoiceVoucher>() to InvoiceVoucherNavType)
        ){ backStackEntry ->
            val routeArgs = backStackEntry.toRoute<InvoiceNavRoute.VoucherReceiptRoute>()
            InvoiceReceiptScreen(
                voucher = routeArgs.voucher,
                onDismissVoucher = {
                    if(navController.previousBackStackEntry != null){
                        navController.popBackStack()
                    }
                }
            )
        }
    }
}

@Composable
fun CreateInvoiceScreen(onGenerateVoucher : (InvoiceVoucher) -> Unit){
    var clientName by remember { mutableStateOf("") }
    var serviceTitle by remember { mutableStateOf("") }
    var amountText by remember { mutableStateOf("") }
    var selectedGateway by remember { mutableStateOf("bKash API") }
    var isSettled by remember { mutableStateOf(true) }

    val gateways = listOf("bKash API", "Stripe Checkout", "Bank Wire")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Generate Invoice 🧾",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Enter financial parameters for automated voucher dispatch",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = clientName,
            onValueChange = { clientName = it },
            label = { Text("Client / Org Name") },
            placeholder = { Text("e.g. Miravo Enterprise") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = serviceTitle,
            onValueChange = { serviceTitle = it },
            label = { Text("Service Description") },
            placeholder = { Text("e.g. Mobile App API Integration") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = amountText,
            onValueChange = { amountText = it },
            label = { Text("Subtotal Amount ($)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Select Payment Channel",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(8.dp))

        SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
            gateways.forEachIndexed { index, gateway ->
                SegmentedButton(
                    selected = selectedGateway == gateway,
                    onClick = { selectedGateway = gateway },
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = gateways.size)
                ) {
                    Text(gateway, fontSize = 12.sp)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(10.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Payment Already Settled?", fontWeight = FontWeight.Medium)
            Switch(checked = isSettled, onCheckedChange = { isSettled = it })
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val parsedAmount = amountText.toDoubleOrNull() ?: 0.0
                if (clientName.isNotBlank() && serviceTitle.isNotBlank() && parsedAmount > 0.0) {
                    val voucher = InvoiceVoucher(
                        invoiceId = "INV-\${System.currentTimeMillis().toString().takeLast(6)}",
                        clientName = clientName,
                        serviceTitle = serviceTitle,
                        subtotal = parsedAmount,
                        vatPercent = 7.5f,
                        paymentGateway = selectedGateway,
                        isSettled = isSettled
                    )
                    onGenerateVoucher(voucher)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB))
        ) {
            Text("Issue Official Voucher 💳", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

@Composable
fun InvoiceReceiptScreen(voucher: InvoiceVoucher, onDismissVoucher: () -> Unit) {
    val vatAmount = voucher.subtotal * (voucher.vatPercent / 100)
    val grandTotal = voucher.subtotal + vatAmount

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
            )
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = voucher.invoiceId,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Badge(
                        containerColor = if (voucher.isSettled) Color(0xFF16A34A) else Color(0xFFE11D48)
                    ) {
                        Text(
                            text = if (voucher.isSettled) "PAID" else "PENDING",
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                Text("Client: ${voucher.clientName}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Service: ${voucher.serviceTitle}", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(6.dp))
                Text("Gateway: ${voucher.paymentGateway}", fontSize = 14.sp)

                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Base Subtotal:")
                    Text("$${String.format("%.2f", voucher.subtotal)}", fontWeight = FontWeight.SemiBold)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Tax (${voucher.vatPercent}% VAT):")
                    Text("$${String.format("%.2f", vatAmount)}", fontWeight = FontWeight.SemiBold)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Grand Total Due:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(
                        "$${String.format("%.2f", grandTotal)}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color(0xFF2563EB)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedButton(
            onClick = onDismissVoucher,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Done / Back to Dashboard", fontWeight = FontWeight.Bold)
        }
    }
}