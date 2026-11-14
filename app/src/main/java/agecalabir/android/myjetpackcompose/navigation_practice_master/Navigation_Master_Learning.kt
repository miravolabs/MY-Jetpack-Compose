package agecalabir.android.myjetpackcompose.navigation_practice_master

import agecalabir.android.myjetpackcompose.ui.theme.Poppins
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable


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

