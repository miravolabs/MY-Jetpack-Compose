package agecalabir.android.myjetpackcompose.jetpackcompose

import agecalabir.android.myjetpackcompose.R
import agecalabir.android.myjetpackcompose.R.font.batica
import agecalabir.android.myjetpackcompose.R.font.godber
import agecalabir.android.myjetpackcompose.R.font.nativera
import agecalabir.android.myjetpackcompose.R.font.nos
import agecalabir.android.myjetpackcompose.shimmerEffect
import agecalabir.android.myjetpackcompose.ui.theme.Poppins
import agecalabir.android.myjetpackcompose.ui.theme.Purple40
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlin.math.roundToInt

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyLoginScreen(){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
            Image(
                painter = painterResource(id = R.drawable.applogo),
                contentDescription = "Profile Pic",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
            )
        Spacer(modifier = Modifier.height(20.dp))
Row(
modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
){
    Text("Mira",
        color = Color.Black, fontSize = 32.sp, fontWeight = FontWeight.Bold)
    Text("Chat",color = Color.Blue, fontSize = 32.sp, fontWeight = FontWeight.Bold)
}
        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = username,
            onValueChange = { username = it},
            placeholder = { Text( "User Name")},
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                cursorColor = Color.Black
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "User Icon",
                    tint = Color.Black
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        )
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Password")},
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                cursorColor = Color.Black
            ),leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password Icon",
                    tint = Color.Black
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )
Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {Toast.makeText(context,
            "Login Successful",
            Toast.LENGTH_SHORT).show()},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ){ Text("Log in")}
        TextButton(
            onClick = {Toast.makeText(context,
                "Waiting for admin approval",
                Toast.LENGTH_SHORT).show()}
        ){ Text("Forgot password?",
        color = Color.Black )}

        Text("or",color= Color.Gray)
        OutlinedButton(
            onClick = {Toast.makeText(context,
                "Wait Server is loading",
                Toast.LENGTH_SHORT).show()},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(width = 1.dp , color = Color.Blue )
        )

        {
            Text("Create an account",
                color = Color.Blue  )
        }

    }
}
val myvenillacolor = Color(0xFFF1FEC8)
val mycosmiccolor = Color(0xFF23212C)
@Preview
@Composable
fun LearnCompose(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .height(100.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = myvenillacolor,
                contentColor = mycosmiccolor
            ),
            border = BorderStroke(2.dp,Color.Gray),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceAround,

                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = R.drawable.abir_pic),
                    contentDescription = "my pic",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                )
                Text("Abir Hossen", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Image(
                    painter = painterResource(R.drawable.mim),
                    contentDescription = "mim pic",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                )
                Text("Mim", fontSize = 20.sp , fontWeight = FontWeight.SemiBold)
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(100.dp),
            colors = CardDefaults.cardColors(
                containerColor = mycolor,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp,Color.DarkGray),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ){
          Row(
              modifier = Modifier
                  .fillMaxSize()
                  .padding(10.dp),
              horizontalArrangement = Arrangement.SpaceAround,

              verticalAlignment = Alignment.CenterVertically
          ){
              Image(
                  painter = painterResource(R.drawable.mimpic),
                  contentDescription = null,
                  contentScale = ContentScale.Crop,
                  modifier = Modifier
                      .size(70.dp)
                      .clip(CircleShape)
              )
              Text("Maisha", fontSize = 20.sp, fontWeight = FontWeight.Bold)
              Image(
                  painter = painterResource(R.drawable.abirpic),
                  contentDescription = null,
                  contentScale = ContentScale.Crop,
                  modifier = Modifier
                      .size(70.dp)
                      .clip(CircleShape)
              )
              Text("Abir", fontWeight = FontWeight.Bold, fontSize = 20.sp)
          }
        }
    }
}
val wineash = Color(0xFF32292F)
@Preview
@Composable
fun newloginscreen(){
    var username by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("MiraLink", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = mycosmiccolor)
        Text("Connect your devices with MiraLink",color = wineash, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(18.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding()
                .heightIn(min = 300.dp),
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEAF4F8)
            ),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp)
            ){
                Text(
                    "Sign In with Username",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,color = Color.Black ,
                    modifier = Modifier
                        .align(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it},
                    placeholder = { Text("Enter username")},
                    shape = RoundedCornerShape(16.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "User icon",
                            tint = Color.Black
                        )
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedPlaceholderColor = wineash,
                        unfocusedPlaceholderColor = wineash,
                        cursorColor = wineash,
                        focusedBorderColor = wineash,
                        unfocusedBorderColor = wineash
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF171717)
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        pressedElevation = 8.dp
                    )
                ){
                    Text("Send OTP Code")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("or", modifier = Modifier.align(alignment = Alignment.CenterHorizontally))
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    border = BorderStroke(1.dp,color = Color.Black),
                    shape = RoundedCornerShape(16.dp)
                ) {
                  Row(
                      modifier = Modifier
                          .fillMaxWidth()
                          .padding(),
                      horizontalArrangement = Arrangement.Center,
                      verticalAlignment = Alignment.CenterVertically
                  ){
                      Image(
                          painter = painterResource(id = R.drawable.googleicon),
                          contentDescription = "google iocn",
                          contentScale = ContentScale.Crop,
                          modifier = Modifier
                              .size(20.dp)
                              .clip(CircleShape)
                      )
                      Spacer(modifier = Modifier.width(10.dp))
                      Text(
                          "Continue with Google",
                          color = wineash
                      )
                  }
                }
            }
        }
    }
}
val NativaFont = FontFamily(Font(nativera))
val BaticaFont = FontFamily(Font(batica))
val GodberFont = FontFamily(Font(godber))
val NosFont = FontFamily(Font(nos))
@Preview
@Composable
fun  Myfirst() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Ahadujjaman", fontSize = 30.sp,color = wineash, fontFamily  = BaticaFont)
        HorizontalDivider(
            thickness = 2.dp,
            color = Color.LightGray
        )
        Text("Mimu Pakhi", fontSize = 30.sp,  color = wineash, fontFamily = BaticaFont)
        HorizontalDivider(
            thickness = 2.dp,
            color = Color.LightGray
        )
       Row(
           modifier = Modifier
               .fillMaxWidth()
               .height(IntrinsicSize.Min),
           horizontalArrangement = Arrangement.SpaceEvenly,
           verticalAlignment = Alignment.CenterVertically
       ){
           Text("Lokki", fontSize = 20.sp, color = wineash, fontFamily = BaticaFont)
           VerticalDivider(
               thickness = 2.dp,
               color = Color.LightGray
           )
           Text("Pakhi", fontSize = 20.sp,  color = wineash, fontFamily = BaticaFont)
       }
        HorizontalDivider(
            thickness = 2.dp,
            color = Color.LightGray
        )
        Text("Moyna Jan", fontSize = 30.sp, color = wineash, fontFamily = BaticaFont)
        HorizontalDivider(
            thickness = 2.dp,
            color = Color.LightGray
        )
        Text("Abir Hossen", fontSize = 30.sp,color = wineash, fontFamily = BaticaFont)
        HorizontalDivider(
            thickness = 2.dp,
            color = Color.LightGray
        )
        Text("Miravolabs", fontSize = 30.sp,color = wineash, fontFamily = BaticaFont)
        HorizontalDivider(
            thickness = 2.dp,
            color = Color.LightGray
        )
        Text("Mim Alif", fontSize = 30.sp,color = wineash, fontFamily = BaticaFont)
        HorizontalDivider(
            thickness = 2.dp,
            color = Color.LightGray
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("Pori", fontSize = 20.sp, color = wineash, fontFamily = BaticaFont)
            VerticalDivider(
                thickness = 2.dp,
                color = Color.LightGray
            )
            Text("Bow", fontSize = 20.sp,  color = wineash, fontFamily = BaticaFont)
        }
        HorizontalDivider(
            thickness = 2.dp,
            color = Color.LightGray
        )
        Text("Maisha Moni", fontSize = 30.sp,color = wineash, fontFamily = BaticaFont)
        HorizontalDivider(
            thickness = 2.dp,
            color = Color.LightGray
        )
        Text("Pokki Bow", fontSize = 30.sp,color = wineash, fontFamily = BaticaFont)
        HorizontalDivider(
            thickness = 2.dp,
            color = Color.LightGray
        )
        Text("Lokki Bow", fontSize = 30.sp,color = wineash, fontFamily = BaticaFont)
        HorizontalDivider(
            thickness = 2.dp,
            color = Color.LightGray
        )
        Text("Suna Bow", fontSize = 30.sp,color = wineash, fontFamily = BaticaFont)
        HorizontalDivider(
            thickness = 2.dp,
            color = Color.LightGray
        )
        Text("My Life Is Mim Alif", fontSize = 30.sp,color = wineash, fontFamily = BaticaFont)
    }
}

@Composable
fun MYFistState(){
    var count by rememberSaveable { mutableIntStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Count: $count" , fontSize = 30.sp, fontFamily = BaticaFont)
        Button(onClick ={ count++ }){
            Text("Increasement")
        }
        Button(
            onClick = { count--},
            enabled = count>0
        ) {
            Text("Dicrasement")
        }
    }
}


@Composable
fun Counter(
   count:Int,
   onIncrement :() -> Unit,
   onDecrement :() -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Count: $count" , fontSize = 30.sp)
        Button(
            onClick = onIncrement,
            enabled = count<10
        ) {Text("Increasement") }
        Button(
            onClick = onDecrement,
            enabled = count>0
        ) {Text("Decresment") }
    }
}
@Preview
@Composable
fun MycountScreen(){
    var count by rememberSaveable { mutableIntStateOf(0)}
    Counter(
        count= count,
        onIncrement = { count++ }
    ) { count-- }
}

@Preview
@Composable
fun Example(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Hello Android",
            fontSize = 30.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold
        )
        Text("MiravoLabs",
            fontSize = 30.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold)
        Text("Abir Hossen",
            fontSize = 30.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Medium)
        Text("MiraChat"
        , fontSize = 30.sp,
            fontFamily = Poppins
            )
        Text("Miravolabs Aura" , fontSize = 30.sp)
    }
}
@Preview
@Composable
fun MyFirstDialogsBox(){
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        // Dialog started by clicking thik button
        Button(
            onClick = {showDialog= true},
            colors = ButtonDefaults.buttonColors(
                containerColor = wineash,
                contentColor = Color.White
            )
        ){
            Text("Delete Item")
        }
        // if state true show the dialog box
        if (showDialog){
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                title = {  Text( "Delete Item")},
                text = { Text("Are you sure you want to delete this item?")},
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                            Toast.makeText(context,
                                "Sussessfully Deleted",
                                Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Text("Delete",color = Color.White)
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showDialog= false}
                    ) {
                        Text("Cancel",color = Color.White)
                    }
                }
            )
        }
    }

}
@Preview
@Composable
fun dialogexample(){
    var dialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { dialog = true }
            , colors = ButtonDefaults.buttonColors(
                containerColor = wineash
            )
        ){
            Text("App Info")
        }
        if (dialog){
            AlertDialog(
                onDismissRequest = { dialog = false },
                title = { Text("App Information")},
                text = {
                    Column() {
                        Text("This app created by Abir Asp", fontSize = 18.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("App Version: 2.0", fontSize = 14.sp,color = Color.Gray)
                    }
                },
                confirmButton = {
                    Button(
                        onClick = { dialog = false },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = wineash
                        )
                    ){
                        Text("OK")
                    }
                }
            )
        }
    }
}
/*@Serializable
sealed interface MyScreenRoutes{
    @Serializable
    data object ScreenA : MyScreenRoutes
    @Serializable
    data object ScreenB : MyScreenRoutes
    @Serializable
    data object ScreenC : MyScreenRoutes
}
@Composable
fun ScreenA(onNavigateToB: () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is Screen A", fontSize = 40.sp,color = wineash)
        Spacer(modifier = Modifier.height(6.dp))
        Button(
            onClick = onNavigateToB
        ) {
            Text("Go to screen B")
        }
    }
}
@Composable
fun ScreenB(onNavigateBackToA: () -> Unit,onNavigationToC: () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is Screen B", fontSize = 40.sp,color = wineash)
        Spacer(modifier = Modifier.height(6.dp))
        Button(onClick = onNavigationToC) {
            Text("Go to screen C")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Button(onClick = onNavigateBackToA) {
            Text("Back to screen A")
    }

    }
}
@Composable
fun ScreenC(onNavigateBackToB: () -> Unit ){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is screen C", fontSize = 40.sp,color = wineash)
        Spacer(modifier = Modifier.height(6.dp))
        Button(onClick = onNavigateBackToB) {
            Text("Back to screen B")
        }
    }
}
@Composable
fun MyThreeNavigateApp(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MyScreenRoutes.ScreenA
    ){
        composable<MyScreenRoutes.ScreenA>{
            ScreenA(onNavigateToB = {
                navController.navigate(MyScreenRoutes.ScreenB){
                    launchSingleTop = true
                }
            }
            )
        }
        composable<MyScreenRoutes.ScreenB>{
            ScreenB(
                onNavigationToC = {
                    navController.navigate(MyScreenRoutes.ScreenC){
                        launchSingleTop = true
                    }
                },
                onNavigateBackToA = {
                    navController.popBackStack()
                }
            )
        }
        composable<MyScreenRoutes.ScreenC>{
            ScreenC(
                onNavigateBackToB = {
                    navController.popBackStack()
                }
            )
        }
    }
}
 */
/*@Serializable
sealed interface AuthRoutes {
    @Serializable
    data object Login : AuthRoutes
    @Serializable
    data object Home : AuthRoutes
}
@Composable
fun LoginScreen(onLoginSuccess : () -> Unit){
    var email by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login Screen", fontSize = 36.sp,)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email")},
            placeholder = { Text("Enter email address")}
        )
        Button(
            onClick = onLoginSuccess
        ){
            Text("Login")
        }
    }
}
@Composable
fun HomeScreen(onLogout : () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to Home Screen", fontSize = 36.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onLogout
        ) {
            Text("LogOUT")
        }
    }
}
@Composable
fun MyScreen(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AuthRoutes.Login
    ){
        composable<AuthRoutes.Login>{
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(AuthRoutes.Home){
                        popUpTo(AuthRoutes.Login){
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<AuthRoutes.Home>{
            HomeScreen(
                onLogout = {
                    navController.navigate(AuthRoutes.Login){
                       popUpTo(AuthRoutes.Home){
                           inclusive = true
                       }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
 */
/*
 */
/*@Serializable
sealed interface MultiArgRoutes{
    @Serializable
    data object Register : MultiArgRoutes
    @Serializable
    data class Profile(
        val name : String = "User",
        val age : Int = 23,
        val isStudent : Boolean = false
    ) : MultiArgRoutes
}
@Composable
fun RegisterScreen(onNavigateToProfile: (name : String, age : Int,isStudent: Boolean) -> Unit ){
    var name by remember { mutableStateOf("")}
    var ageText by remember { mutableStateOf("")}
    var isStudent by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Register Form", fontSize = 28.sp)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = name ,
            onValueChange = { name = it},
            label = { Text("Name")},
            placeholder = { Text("Enter your name")},
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = ageText,
            onValueChange = { ageText= it},
            label = { Text("Age")},
            placeholder = { Text("Enter your age")},
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("Are you Student?")
            Spacer(modifier = Modifier.width(10.dp))
            Switch(
                checked = isStudent,
                onCheckedChange = {isStudent = it}
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                val finalage = ageText.toIntOrNull() ?: 23
                val finalname = if (name.isNotBlank()) name else " User"
                    onNavigateToProfile(finalname, finalage, isStudent)

                    // condition for not empty

                    // val age = ageText.toIntOrNull() ?: 0
                    //if (name.isNotBlank() && age > 0) {
                    //    onNavigateToProfile(name, age, isStudent)
                    //}

            }
        ) {
            Text("Submit and view profile")
        }
    }
}
@Composable
fun ProfileDetailsScreen(
    name : String,
    age : Int,
    isStudent: Boolean,
    onBack: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Column(
                modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.Start
            ) {
                Text("User Profile Details", fontSize = 24.sp, color = wineash)
                Spacer(modifier = Modifier.height(12.dp))
                Text("Name: $name", fontSize = 18.sp)
                Text("Age: $age", fontSize = 18.sp)
                Text(
                    "Student: ${if (isStudent)"Yes Student" else "Not ,working"}",
                    fontSize = 18.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onBack) {
            Text("Go Back")
        }
    }
}
@Composable
fun MultiArgNavigationApp(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MultiArgRoutes.Register
    ){
        composable<MultiArgRoutes.Register>{
            RegisterScreen(
                onNavigateToProfile = {
                    name, age, isStudent ->
                    navController.navigate(
                        MultiArgRoutes.Profile(
                            name = name,
                            age = age,
                            isStudent = isStudent
                        )
                    ){
                        launchSingleTop  = true
                    }
                }
            )
        }
        composable<MultiArgRoutes.Profile>{ backStackEntry ->
            val profileData = backStackEntry.toRoute<MultiArgRoutes.Profile>()
            ProfileDetailsScreen(
                name = profileData.name,
                age = profileData.age,
                isStudent = profileData.isStudent,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
 */
@Composable
fun SimpleLazyColumnExample(){
    val namesList = listOf(
        "Abir", "Rahim", "Karim", "Sabbir",
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(namesList){ index ,singleName ->
            ListItemCard( name = "${index + 1 }. $singleName" )
            // without  index number
            /*items(namesList) { singleName ->
            NameItemCard(name = singleName)*/
        }
    }
}
@Composable
fun ListItemCard(name : String){
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(color = Color.LightGray, width = 1.dp)
    ){
        Text(
            text = name,
            fontSize = 18.sp,
            color = wineash,
            modifier = Modifier.padding(16.dp)
            )
    }
}

/*data class Student(
    val id : Int ,
    val name : String ,
    val roll : Int ,
    val department : String
)
@Preview
@Composable
fun StudentListScreen(){
    val studentList = listOf(
        Student(id = 0,name = "Miravolabs",roll = 354, department = "CSS"),
        Student(id = 1,name = "Abir",roll = 345, department = "CSE"),
        Student(id = 2,name = "Shaker",roll = 345, department = "BSC"),
        Student(id = 3,name = "Nihal",roll = 345, department = "BBA"),
        Student(id = 4,name = "Daud",roll = 454, department = "MA"),
        Student(id = 5,name = "Shakib",roll = 343, department = "BANGLA"),
        Student(id = 6,name = "Tanvir",roll = 333, department = "EEE"),
        Student(id = 7, name = "Mim Alif",roll = 201, department = "Politeck")
    )
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(color = Color.White),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = studentList,
            key = { student -> student.id }
        ){
            student ->
            StudentItemCard(student=student)
        }
    }
}
@Composable
fun StudentItemCard(student: Student){
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(width = 1.dp,color = Color.Gray)
    ){
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = student.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = wineash,
                    fontFamily = Poppins
                )
                Text("Roll: ${student.roll}",
                    color = Color.DarkGray,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Poppins
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Dept: ${student.department}",
                fontSize = 15.sp,
                color = Color.Gray,
                fontFamily = Poppins
            )
        }
    }
} */

/*@Preview
@Composable
fun StudentHorizontalListScreen() {
    val studentList = listOf(
        Student(id = 0, name = "Miravolabs", roll = 354, department = "CSE"),
        Student(id = 1, name = "Abir", roll = 345, department = "CSE"),
        Student(id = 2, name = "Shaker", roll = 345, department = "BSC"),
        Student(id = 3, name = "Nihal", roll = 345, department = "BBA"),
        Student(id = 4, name = "Daud", roll = 454, department = "MA"),
        Student(id = 5, name = "Tanvir", roll = 333, department = "EEE")
    )
    Column(
        modifier = Modifier.fillMaxSize().background(color = Color.White)
    ) {
        Text(
            text = "Students (Muktagacha R.K School)",
            fontSize = 20.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 16.dp, bottom = 12.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = studentList,
                key = { student -> student.id }
            ){student ->
                StudentRowCard(student=student)
            }
        }
    }
}
@Composable
fun StudentRowCard(student: Student ){
    Card(
        modifier = Modifier.width(160.dp).height(160.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp)
    ){
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = student.name,
                fontFamily = Poppins,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = wineash
            )
            Text(
                text = "Roll : ${student.roll}",
                fontWeight = FontWeight.SemiBold,
                color = wineash,
                fontSize = 18.sp,
                fontFamily = Poppins
            )
            Text(
                text = "Depet: ${student.department}",
                fontSize = 18.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                color = wineash
            )
        }
    }
}*/
data class Student(
    val id : Int ,
    val name : String ,
    val roll : Int ,
    val department : String
)
@Composable
fun CombinedListScreen(){
    val categories = listOf("All","Computer","Eletical","Civil","Medical","Agricalcuarl")
    val studentList = listOf(
        Student(id = 0, name = "Miravolabs", roll = 354, department = "CSE"),
        Student(id = 1, name = "Abir", roll = 345, department = "CSE"),
        Student(id = 2, name = "Shaker", roll = 345, department = "BSC"),
        Student(id = 3, name = "Nihal", roll = 345, department = "BBA"),
        Student(id = 4, name = "Daud", roll = 454, department = "MA"),
        Student(id = 5, name = "Tanvir", roll = 333, department = "EEE")
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Department",
                fontSize = 20.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                color = wineash,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories){category->
                    CategoryChip(title = category)
                }
            }

        }
        item {
            Text(
                text = "Student List",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = wineash,
                fontFamily = Poppins,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        items(
            items = studentList,
            key = {student -> student.id}
        ){student->
            Box(modifier = Modifier.padding(horizontal = 16.dp)){
                StudentItemCard(student=student)
            }
        }
    }
}
@Composable
fun StudentItemCard(student: Student){
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ){
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = student.name,
                    color = wineash,
                    fontSize = 18.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Roll: ${student.roll}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color= wineash,
                    fontFamily = Poppins
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Dept: ${student.department}",
                color = Color.Gray,
                fontSize = 18.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal
            )
        }
    }
}
@Composable
fun CategoryChip(title : String) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(width = 1.dp, color = Color.Gray)
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}


// Scaffold
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffoldScreen(){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.Start
                    ){
                        Text("Mira",
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = Poppins
                        , fontSize = 20.sp
                        )
                        Text("Link",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = Poppins,
                            color = Color.Blue
                        )
                    }
                        },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Menu,
                            contentDescription = null,
                            tint = Color.Black  )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Search,
                            contentDescription = null, tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = Color.Blue,
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        painter = painterResource(R.drawable.house),
                        contentDescription = "Home icon",
                        tint = Color.Blue,
                        modifier = Modifier.size(30.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.Inbox,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(30.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(30.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Transparent),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
           Text(
               "Home Screen",
               fontWeight = FontWeight.SemiBold,
               fontSize = 36.sp,
               fontFamily = Poppins,
               color = Color.Black
           )
        }
    }
}

/*
// bottom navigation bar

@Serializable
sealed interface BottomNavRoutes{
    @Serializable
    data object Home : BottomNavRoutes
    @Serializable
    data object Search : BottomNavRoutes
    @Serializable
    data object History : BottomNavRoutes
    @Serializable
    data object Profile : BottomNavRoutes
}
data class BottomNavItem(
    val title: String,
    val icon : ImageVector,
    val route : BottomNavRoutes
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainBottomNavApp(){
    val navController = rememberNavController()

    val navItems = listOf(
        BottomNavItem("Home",Icons.Default.Home, BottomNavRoutes.Home),
        BottomNavItem("Search",Icons.Default.Search, BottomNavRoutes.Search),
        BottomNavItem("History",Icons.Default.History, BottomNavRoutes.History),
        BottomNavItem("Profile",Icons.Default.Person, BottomNavRoutes.Profile)
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            "Mira",
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = Poppins, fontSize = 20.sp
                        )
                        Text(
                            "Chat",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = Poppins,
                            color = Color.Blue
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                navItems.forEach { item->
                    val selected = currentDestination?.hasRoute(item.route::class) == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = {navController.navigate(item.route){
                            popUpTo<BottomNavRoutes.Home>{
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                        },
                        icon = {
                            Icon(imageVector = item.icon, contentDescription = item.title)
                        },
                        label = { Text(
                            text = item.title,
                            fontFamily = Poppins)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = wineash,
                            indicatorColor = wineash,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray
                        )
                    )
                }
            }
        }
    ) { innerPadding->
        NavHost(
            navController = navController,
            startDestination = BottomNavRoutes.Home,
            modifier = Modifier.padding(innerPadding)
        ){
            composable<BottomNavRoutes.Home>{
                ScreenLayout(title = "Home Screen")
            }
            composable<BottomNavRoutes.Search>{
                ScreenLayout(title = "Search Screen")
            }
            composable<BottomNavRoutes.History>{
                ScreenLayout(title = "History Screen")
            }
            composable<BottomNavRoutes.Profile>{
                ScreenLayout(title = "Profile Screen")
            }
        }
    }
}
@Composable
fun ScreenLayout(title: String){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F8FA)),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = title,
            fontSize = 28.sp,
            fontFamily = Poppins,
            color = wineash,
            fontWeight = FontWeight.Medium
        )
    }
}
*/

@Composable
fun SnackBarExample(){
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Snackber Demo",
                fontSize = 22.sp,
                fontFamily = Poppins,
                color = wineash
            )
            Spacer(modifier= Modifier.height(24.dp))
            Button(
                onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "This is simple Snackber",
                            duration = SnackbarDuration.Short
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Show Simple SnackBer",
                    fontFamily = Poppins
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = {
                    coroutineScope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "Item deleted successfully",
                            actionLabel = "Undo",
                            duration = SnackbarDuration.Short
                        )
                        when (result){
                            SnackbarResult.ActionPerformed -> {}
                            SnackbarResult.Dismissed ->{}
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = wineash,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Show SnackBer with action",
                    fontFamily = Poppins
                )
            }
        }
    }
}

@Composable
fun AdvanceSnackBer(){
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState){data ->
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                    containerColor = wineash,
                    action = {
                        data.visuals.actionLabel?.let{ actionLabel ->
                            TextButton(
                                onClick = { data.performAction() }
                            ) {
                                Text(
                                    text = actionLabel,
                                    color = Color(0xFFFFD54F),
                                    fontFamily = Poppins,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }
                ){
                    Text(
                        text = data.visuals.message,
                        color = Color.White,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Custom Snacks Demo",
                fontSize = 24.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                color = wineash
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    coroutineScope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "Item removed to trash!",
                            actionLabel = "Undo",
                            duration = SnackbarDuration.Short
                        )
                        when (result){
                            SnackbarResult.ActionPerformed -> {}
                            SnackbarResult.Dismissed ->{}

                        }
                    }
                },
                modifier  = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = wineash,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ){
                Text(
                    text = "Show custom snackbar",
                    fontFamily = Poppins,
                    fontSize = 16.sp
                )
            }
        }
    }
}
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleBottomSheetScreen(){
    var showSheet by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ){
        Button(
            onClick = {showSheet = true },
            colors = ButtonDefaults.buttonColors(
                containerColor = wineash,
                contentColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Show app info",
                fontFamily = Poppins
            )
        }
        if (showSheet){
            ModalBottomSheet(
                onDismissRequest = {
                    showSheet = false
                },
                containerColor = Color.White,
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "MiraLink App Info",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        color = wineash,
                        fontSize = 20.sp
                    )
                    Spacer(modifier= Modifier.height(8.dp))
                    Text(
                        text = "Version : 1.1\nThis App is created by Abir Asp.",
                        fontSize = 15.sp,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray
                    )
                    Spacer(modifier  = Modifier.height(20.dp))
                    OutlinedButton(
                        onClick = {showSheet = false},
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "OK",
                            fontFamily = Poppins,
                            color = wineash
                        )
                    }
                }
            }
        }
    }
}
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvanceBottomSheetScreen(){
    var showSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    fun hideSheet(onClosed: () -> Unit =  {}){
        scope.launch {
            sheetState.hide()
        }.invokeOnCompletion {
            if(!sheetState.isVisible){
                showSheet = false
                onClosed()
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ){
        Button(
            onClick = {showSheet = true},
            colors = ButtonDefaults.buttonColors(containerColor = Purple40),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Open action menu",
                fontFamily = Poppins,
                color = Color.White,
                fontSize = 16.sp
            )
        }
        if(showSheet){
            ModalBottomSheet(
                onDismissRequest = {showSheet = false},
                sheetState = sheetState,
                containerColor = Color.White,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                dragHandle = {
                    BottomSheetDefaults.DragHandle(color = Color.LightGray)
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 32.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "Actions Menu",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = wineash
                        )
                        IconButton(onClick = {hideSheet()}) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close Icon",
                                tint = Color.Gray
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                    BottomSheetActionRow(
                        icon = Icons.Default.Share,
                        title = "Share icon",
                        onClick = {hideSheet {  }}
                    )
                    BottomSheetActionRow(
                        icon = Icons.Default.Edit,
                        title = "Edit icon",
                        onClick = {hideSheet {  }}
                    )
                    BottomSheetActionRow(
                        icon = Icons.Default.Delete,
                        title = "Delete icon",
                        onClick = {hideSheet {  }},
                        isDestructive = true
                    )
                }
            }
        }
    }
}
@Composable
fun BottomSheetActionRow(
    icon: ImageVector,
    title: String,
    isDestructive: Boolean = false,
    onClick: () -> Unit
){
    val contentColor = if (isDestructive) Color.Red else wineash
    val textColor = if (isDestructive) Color.Red else wineash
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector =  icon,
            contentDescription =  title,
            tint =  contentColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            fontFamily = Poppins,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = textColor
        )
    }
}
@Preview
@Composable
fun OtpScreen() {
    var otpText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Verification Code",
            fontSize = 22.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            color = wineash
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Enter the 4-digit code sent to your phone",
            fontSize = 14.sp,
            fontFamily = Poppins,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        // OTP Input Component
        OtpInputField(
            otpText = otpText,
            onOtpModified = { value ->
                // ৪ সংখ্যার বেশি যাতে না নেওয়া হয়
                if (value.length <= 4) {
                    otpText = value
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Verify বাটন (৪ ডিজিট পূরণ হলে একটিভ হবে)
        Button(
            onClick = {
                // ভেরিফাই লজিক
            },
            enabled = otpText.length == 4,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = wineash,
                disabledContainerColor = Color.LightGray
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Verify OTP",
                fontFamily = Poppins,
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}
@Composable
fun OtpInputField(
    otpText: String,
    onOtpModified: (String) -> Unit
) {
    BasicTextField(
        value = otpText,
        onValueChange = {
            // শুধুমাত্র সংখ্যা ইনপুট নেবে
            if (it.all { char -> char.isDigit() }) {
                onOtpModified(it)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // ৪টি স্কয়ার বক্স তৈরি
                repeat(4) { index ->
                    val char = when {
                        index < otpText.length -> otpText[index].toString()
                        else -> ""
                    }
                    val isFocused = otpText.length == index

                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .border(
                                width = if (isFocused) 2.dp else 1.dp,
                                color = if (isFocused) wineash else Color.LightGray,
                                shape = RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = char,
                            fontSize = 20.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            color = wineash
                        )
                    }
                }
            }
        }
    )
}
@Composable
fun ExampleBasicTextFiled(){
    var text by remember {mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Example Basic Text Filed",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = Poppins,
            color = wineash
        )
        Spacer(modifier = Modifier.height(20.dp))
        BasicTextField(
            value =  text,
            onValueChange = {text = it },
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontFamily = Poppins,
                color = wineash
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF1F1F1))
                .padding(16.dp)
        )
        Spacer(modifier= Modifier.height(24.dp))
        Text(
            text = "My text is : $text",
            fontFamily = Poppins,
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}
@Preview
@Composable
fun BasicTextFiled(){
    var myText by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Learning Basic TextFiled",
            fontSize = 28.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            color = wineash
        )
        Spacer(modifier = Modifier.height(24.dp))
        BasicTextField(
            value =  myText,
            onValueChange = {myText = it },
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontFamily = Poppins,
                color = wineash
            ),
            cursorBrush = SolidColor(wineash),
            singleLine = true,
            decorationBox = {innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.5.dp,
                            color = if (myText.isNotEmpty()) wineash else Color.Gray,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    contentAlignment = Alignment.CenterStart
                ){
                    if (myText.isEmpty()){
                        Text(
                            text = "Type something here...",
                            fontFamily = Poppins,
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            }
        )
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = "My text is : $myText",
            fontFamily = Poppins,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = Color.DarkGray
        )
    }
}
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenu() {
    val courses = listOf("Android Development", "Web Development", "UI/UX Design", "Machine Learning")
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    var selectedCourse by rememberSaveable { mutableStateOf(courses[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Select Your Course",
            fontFamily = Poppins,
            fontSize = 20.sp,
            color = wineash
        )

        Spacer(modifier = Modifier.height(24.dp))

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it }
        ) {
            OutlinedTextField(
                value = selectedCourse,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = wineash,
                    unfocusedBorderColor = Color.LightGray,
                    cursorColor = Color.Transparent,
                    selectionColors = TextSelectionColors(
                        handleColor = Color.Transparent,
                        backgroundColor = Color.Transparent
                    )
                )
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                containerColor = Color.White
            ) {
                courses.forEach { course ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = course,
                                fontFamily = Poppins,
                                color = if (course == selectedCourse) wineash else Color.Black,
                                fontSize = 15.sp
                            )
                        },
                        onClick = {
                            selectedCourse = course
                            isExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Selected : $selectedCourse",
            fontSize = 16.sp,
            fontFamily = Poppins,
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun SelectionControlsScreen(){
    val scrollState = rememberScrollState()
    // 1. Switch State
    var isNotificationOn by rememberSaveable { mutableStateOf(true)}
    // 2. Checkbox State
    var isTermsAccepted by rememberSaveable {mutableStateOf(false) }
    // 3. RadioButton State
    val paymentOptions = listOf("bKash","Nagad","Credit Card")
    var selectedPayment by rememberSaveable {mutableStateOf(paymentOptions[0]) }
    // 4. Slider State (0f theke 100f porjonto)
    var volumeLevel by rememberSaveable { mutableFloatStateOf(50f) }
    // 5 RangeSlider State ( min to max limiit)
    var priceRange by remember { mutableStateOf(200f..800f) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "App Settings & Selection",
            fontSize = 22.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            color = wineash
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "1. Switch (Toggle Option)",
            fontWeight = FontWeight.SemiBold,
            fontFamily = Poppins,
            fontSize = 16.sp,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isNotificationOn = !isNotificationOn }
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Enable Notification",
                fontFamily = Poppins,
                fontSize = 15.sp,
                color = Color.Black
            )
            Switch(
                checked = isNotificationOn,
                onCheckedChange = {isNotificationOn = it},
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = wineash,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color.LightGray
                )
            )
        }
        Divider(modifier = Modifier.padding(vertical = 4.dp),color = Color(0xFFE0E0E0))
        Text(
            text = "2. CheckBox (Multiple / Agreement)",
            fontSize = 16.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isTermsAccepted = !isTermsAccepted }
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Checkbox(
                checked = isTermsAccepted,
                onCheckedChange = {isTermsAccepted = it},
                colors = CheckboxDefaults.colors(
                    checkedColor = wineash,
                    uncheckedColor = Color.Gray,
                    checkmarkColor = Color.White
                )
            )
            Spacer(modifier= Modifier.width(8.dp))
            Text(
                text = "I accept the Terms and Conditions",
                fontFamily = Poppins,
                fontSize = 15.sp,
                color = Color.Black
            )
        }
        Divider(modifier = Modifier.padding(vertical = 16.dp),color = Color(0xFFE0E0E0))
        Text(
            text = "3. Radio Buttons (Select only One",
            fontSize = 16.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(8.dp))
        paymentOptions.forEach { method ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { selectedPayment = method }
                    .padding(vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = (method == selectedPayment),
                    onClick = {selectedPayment = method},
                    colors = RadioButtonDefaults.colors(
                        selectedColor = wineash,
                        unselectedColor = Color.Gray
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = method,
                    fontFamily = Poppins,
                    fontSize = 15.sp,
                    color = if(method == selectedPayment) wineash else Color.Black

                )
            }
        }
        Divider(modifier = Modifier.padding(vertical = 16.dp), color = Color(0xFFE0E0E0))
        Text(
            text = "4. Slider (Volume Level)",
            fontSize = 16.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold,
            color = Color.DarkGray
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "${volumeLevel.roundToInt()}%",
                fontSize = 15.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                color= wineash
            )
            Slider(
                value = volumeLevel,
                onValueChange = {volumeLevel = it},
                valueRange = 0f..100f,
                colors = SliderDefaults.colors(
                   thumbColor = wineash,
                    activeTrackColor = wineash,
                    inactiveTrackColor = Color(0xFFD6D6D6)
                )
            )
        }
        Divider(modifier = Modifier.padding(vertical = 12.dp),color = Color(0xFFE0E0E0))
        Text(
            text = "5. RangeSlider (Price Range)",
            fontSize = 16.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold,
            color = Color.DarkGray
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "$${priceRange.start.roundToInt()} - $${priceRange.endInclusive.roundToInt()}",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = wineash
            )
            RangeSlider(
                value = priceRange,
                onValueChange = {range -> priceRange = range},
                valueRange = 0f..1000f,
                colors = SliderDefaults.colors(
                    thumbColor = wineash,
                    activeTrackColor = wineash,
                    inactiveTrackColor = Color(0xFFD6D6D6)
                )
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF7F7F7)
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ){
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Notification: ${if(isNotificationOn) "ON" else "OFF"}",
                    fontFamily = Poppins,
                    fontSize = 13.sp,
                    color = Color.DarkGray
                )
                Text(
                    text = "Terms Accepted: ${if(isTermsAccepted) "Yes" else "No"}",
                    fontFamily = Poppins,
                    fontSize = 13.sp,
                    color = Color.DarkGray
                )
                Text(
                    text = "Payment Method: $selectedPayment",
                    fontFamily = Poppins,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = wineash
                )
                Text(
                    text = "Volume: ${volumeLevel.roundToInt()}%",
                    fontFamily = Poppins,
                    fontSize = 13.sp,
                    color = Color.DarkGray
                )
                Text(
                    text = "Price Range: $${priceRange.start.roundToInt()} - $${priceRange.endInclusive.roundToInt()}",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = wineash
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SegmentedButtonExample(){
    val options = listOf("Option A","Option B","Option C")
    var selectedOption by rememberSaveable {mutableIntStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Selectable Options",
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            color = wineash,
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEachIndexed  { index , label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = options.size,
                        baseShape = RoundedCornerShape(4.dp)
                    ),
                    onClick = {selectedOption = index},
                    selected = (index == selectedOption),
                    icon = {
                        if(index == selectedOption){
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                modifier = Modifier.size(SegmentedButtonDefaults.IconSize)
                            )
                        }
                    },
                    colors = SegmentedButtonDefaults.colors(
                        activeContainerColor = wineash,
                        activeContentColor = Color.White,
                        inactiveContainerColor = Color(0xFFF5F5F5),
                        inactiveContentColor = Color.Gray,
                        activeBorderColor = wineash,
                        inactiveBorderColor = Color.LightGray
                    )
                ) {
                    Text(
                        text = label,
                        fontFamily = Poppins,
                        fontSize = 14.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Selected: ${options[selectedOption]}",
            fontSize = 20.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            color = wineash
        )
    }
}
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenuExample(){
    var isMenuExpanded by rememberSaveable {mutableStateOf(false) }
    var isSettingSubMenuExpanded by rememberSaveable {mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Jetpack Compose",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        color = wineash,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back Icon",
                            tint = wineash
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {isMenuExpanded = true}) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More Option",
                            tint = wineash
                        )
                    }
                    DropdownMenu(
                        expanded = isMenuExpanded,
                        onDismissRequest = { isMenuExpanded = false},
                        containerColor = Color.White,
                        shape = RoundedCornerShape(12.dp),
                        shadowElevation = 4.dp
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "Share App",
                                    fontSize = 14.sp,
                                    fontFamily = Poppins,
                                    fontWeight = FontWeight.Medium,
                                    color = wineash
                                )
                            },
                            onClick = {isMenuExpanded = false}
                        )
                        Box{
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "Setting",
                                    fontSize = 14.sp,
                                    fontFamily = Poppins,
                                    fontWeight = FontWeight.Medium,
                                    color = wineash
                                )
                            }, trailingIcon = {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    contentDescription = "open sub menu",
                                    tint = wineash,
                                    modifier = Modifier.size(12.dp)
                                )
                            },
                            onClick = { isSettingSubMenuExpanded = true}
                        )
                        }
                    DropdownMenu(
                        expanded = isSettingSubMenuExpanded,
                        onDismissRequest = {isSettingSubMenuExpanded = false},
                        containerColor = Color.White,
                        shape = RoundedCornerShape(12.dp),
                        shadowElevation = 4.dp
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "App info",
                                    fontFamily = Poppins,
                                    color = wineash,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp
                                )
                            },
                            onClick = { isSettingSubMenuExpanded = false }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "History",
                                    fontSize = 14.sp,
                                    fontFamily = Poppins,
                                    fontWeight = FontWeight.Medium,
                                    color = wineash
                                )
                            },
                            onClick = { isSettingSubMenuExpanded = false }
                        )
                    }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Jetpack App",
                fontSize = 26.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                color = wineash
            )
        }
    }
}
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerExample(){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val menuItems = listOf("Option A","Option B","Option C")
    var selectedItem by rememberSaveable {mutableStateOf(menuItems[0]) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.White,
                drawerShape = RoundedCornerShape(topEnd = 18.dp, bottomEnd = 18.dp),
                modifier = Modifier.width(280.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                // Drawer Header
                Text(
                    text = "Header Menu",
                    fontFamily = Poppins,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = wineash,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
                )
                HorizontalDivider(thickness = 1.dp,color = Color.LightGray)
                Spacer(modifier = Modifier.height(20.dp))
                // Item 1
                NavigationDrawerItem(
                    label = { Text("Option A", fontFamily = Poppins) },
                    selected = (selectedItem == "Option A"),
                    icon = {Icon(Icons.Default.Android, contentDescription = null)},
                    onClick = {
                        selectedItem = "Option A"
                        scope.launch { drawerState.close() }
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = wineash,
                        selectedTextColor = Color.White,
                        selectedIconColor = Color.White,
                        unselectedIconColor = Color.DarkGray,
                        unselectedTextColor = Color.DarkGray
                    ),
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    shape = RoundedCornerShape(18.dp)
                )
                // Item 2
                NavigationDrawerItem(
                    label= {Text("Option B", fontFamily = Poppins)},
                    selected = (selectedItem == "Option B"),
                    icon = {Icon(Icons.Default.Android, contentDescription = null)},
                    onClick = {
                        selectedItem = "Option B"
                        scope.launch { drawerState.close() }
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = wineash,
                        selectedTextColor = Color.White,
                        selectedIconColor = Color.White,
                        unselectedTextColor = Color.DarkGray,
                        unselectedIconColor = Color.DarkGray
                    ),
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    shape = RoundedCornerShape(18.dp)
                )
                // Item 3
                NavigationDrawerItem(
                    label = { Text("Option C", fontFamily = Poppins) },
                    selected = (selectedItem == "Option C"),
                    icon = { Icon(Icons.Default.Android, contentDescription = null) },
                    onClick = {
                        selectedItem = "Option C"
                        scope.launch { drawerState.close() }
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = wineash,
                        selectedTextColor = Color.White,
                        selectedIconColor = Color.White,
                        unselectedTextColor = Color.DarkGray,
                        unselectedIconColor = Color.DarkGray
                    ),
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    shape = RoundedCornerShape(18.dp)
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Android App",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            color = wineash
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                    imageVector =  Icons.Default.Menu,
                                    contentDescription = null,
                                    tint = wineash
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
                )
            }
        ) {innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .background(Color(0xFFF9F9F9)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Android App Screen",
                    fontSize = 20.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    color = wineash
                )
                Text(
                    text = "Selected : $selectedItem",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
@Composable
fun ChatsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("💬 All Recent Chats", fontFamily = Poppins, fontSize = 20.sp, color = wineash)
    }
}

@Composable
fun StatusScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("🟢 User Status Updates", fontFamily = Poppins, fontSize = 20.sp, color = wineash)
    }
}

@Composable
fun CallsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("📞 Call Logs & History", fontFamily = Poppins, fontSize = 20.sp, color = wineash)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TapPagerExample() {
    val tabs = listOf("Option A", "Option B", "Option C")
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = "Android App",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            color = wineash
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
                )
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    containerColor = Color.White,
                    contentColor = wineash,
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                            color = wineash,
                            height = 3.dp
                        )
                    }
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = (pagerState.currentPage == index),
                            onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                            text = {
                                Text(
                                    text = title,
                                    fontFamily = Poppins,
                                    fontWeight = if (pagerState.currentPage == index) FontWeight.Bold else FontWeight.Normal,
                                    fontSize = 15.sp,
                                    color = if (pagerState.currentPage == index) wineash else Color.Gray
                                )
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) { page ->
            when(page){
                0 -> ChatsScreen()
                1 -> StatusScreen()
                2 -> CallsScreen()
            }
        }
    }
}
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshExample(){
    var isRefreshing by rememberSaveable {mutableStateOf(false) }
    var scope = rememberCoroutineScope()
    var itemList by rememberSaveable {
        mutableStateOf(listOf("Item 1","Item 2","Item 3","Item 4"))
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Pull to Refresh",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        color = wineash
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { innerPadding ->
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = {
                scope.launch {
                    isRefreshing  = true
                    delay(2000)
                    itemList = listOf("Refreshed Item ${itemList.size+1}") + itemList
                    isRefreshing = false
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF9F9F9))
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) { items(itemList){item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = item,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = wineash
                        )
                    }
                }
            } }
        }
    }
}
data class ProductItem(
    val name : String,
    val price : String
)
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LazyGridExample(){
    val productList = listOf(
        ProductItem("HeadPhone","$49"),
        ProductItem("Smart Watch","$80"),
        ProductItem("Sneakers","$120"),
        ProductItem("Backpack","$35"),
        ProductItem("Sunglasses","$25"),
        ProductItem("Mechinal Keyboard","$75"),
        ProductItem("Gaming Mouse","$40"),
        ProductItem("Coffee Mug","$15")
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Store Products",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        color = wineash
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) {
        innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF9F9F9)),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(productList){product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .background(wineash.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center
                        ){
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = null,
                                tint = wineash,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = product.name,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = wineash
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = product.price,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = wineash
                        )
                    }
                }
            }
        }
    }
}


//                              Animation Screen Learning
// real screen
@Composable
fun RealProductCard(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(wineash.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ){
                Text("\uD83C\uDFA7", fontSize = 28.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text  = "Wireless Headphones",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color =wineash
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "$49.99",
                    fontSize = 15.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    color = wineash
                )
            }
        }
    }
}
// Simmer placeHolder
@Composable
fun ShimmerProductCard(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(20.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .height(16.dp)
                        .clip(RoundedCornerShape((4.dp)))
                        .shimmerEffect()
                )
            }
        }
    }
}
@Preview
@Composable
fun ShimmerExampleScreen(){
    var isLoading by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        delay(3000)
        isLoading = false
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(isLoading){
            ShimmerProductCard()
        } else {
            RealProductCard()
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { isLoading = !isLoading},
            colors = ButtonDefaults.buttonColors(containerColor = wineash)
        ){
            Text(
                text = if(isLoading) "Show Real Card" else "Show Shimmer",
                fontFamily = Poppins
            )
        }
    }
}
@Preview
@Composable
fun AnimatedVisibilityExample(){
    var isVisibility by rememberSaveable {mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { isVisibility = !isVisibility },
            colors = ButtonDefaults.buttonColors(containerColor = wineash),
            shape = RoundedCornerShape(12.dp)
        ){
            Text(
                text = if (isVisibility) "Hide Details" else "Show Details",
                fontFamily = Poppins
            )
            Spacer(modifier = Modifier.width(6.dp))
            Icon(
                imageVector =if (isVisibility) Icons.Default.KeyboardArrowUp  else Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        AnimatedVisibility(
            visible =  isVisibility,
            enter = fadeIn(animationSpec = tween(400)) + expandVertically(animationSpec = tween(400)),
            exit = fadeOut(animationSpec = tween(400)) + shrinkVertically(animationSpec = tween(400))
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ){
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Special Offer Unlocked!",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        color = wineash,
                        fontSize = 17.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Use code COMPOSE20 to get flat 20% discount on your next order.",
                        fontSize = 14.sp,
                        fontFamily = Poppins,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}


//                                  Implicit Intent

@Composable
fun ImplicitIntentScreen(){
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .padding(16.dp),
    verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Implicit Intent Demo",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,
            color = wineash
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                val webIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://miravolabsweb.web.app/")
                )
                context.startActivity(webIntent)
            },
            colors = ButtonDefaults.buttonColors(containerColor = wineash),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                text = "Open Website",
                fontFamily = Poppins,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val dialIntent = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:01721873201")
                )
                context.startActivity(dialIntent)
            },
            colors = ButtonDefaults.buttonColors(containerColor = wineash),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Icon(Icons.Default.Call, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text  = "Open Phone Dialer",
                fontFamily = Poppins,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val explicitIntent = Intent(context, SecondActivity::class.java).apply {
                    putExtra("USER_NAME","Abir")
                }
                context.startActivity(explicitIntent)
            },
            colors = ButtonDefaults.buttonColors(containerColor = wineash),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                text = "Go to second Activity",
                fontFamily = Poppins,
                color = Color.White
            )
        }
    }
}



//                      Navigation Practice 1.



@Composable
fun ProductListScreen(
    onProductClick:(Int, String, String) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Type-Safe Products",
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = wineash
        )
        Spacer(modifier = Modifier.height(12.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onProductClick(101, "Sony WH-1000XM5", "399.99") },
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Column {
                    Text(
                        text ="Sony WH-1000XM5",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = wineash
                    )
                    Text(
                        text ="Click to view details",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                Text(
                    text = "$399.99",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    color = wineash
                )
            }
        }
    }
}
@Composable
fun ProductDetailsScreen(
    product: ProductApp.ProductDetailsRoute,
    onBack :() -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        IconButton(
            onClick = onBack
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ){
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Product ID: #${product.id}",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = product.title,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Price :$${product.price}",
                    fontSize = 18.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    color = wineash
                )
            }
        }
    }
}

@Serializable
sealed interface ProductApp{
    @Serializable
    data object ProductListRoute : ProductApp
    @Serializable
    data class ProductDetailsRoute(
        val id:Int,
        val title: String,
        val price: String
    ) : ProductApp
}





@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ProductApp.ProductListRoute
    ){
        composable<ProductApp.ProductListRoute>{
            ProductListScreen(
                onProductClick = {id,title,price->
                    navController.navigate(ProductApp.ProductDetailsRoute(id = id,title = title,price = price))
                }
            )
        }
        composable<ProductApp.ProductDetailsRoute>{backStackEntry->
            val details = backStackEntry.toRoute<ProductApp.ProductDetailsRoute>()
            ProductDetailsScreen(
                product = details,
                onBack = {navController.popBackStack()}
            )
        }
    }
}


//                              Navigation Practice 2. 05-10-2026
/*
@Serializable
sealed interface PracticeNavigationRoutes{
    @Serializable
    data object Home: PracticeNavigationRoutes
    @Serializable
    data class Profile(val userName : String) : PracticeNavigationRoutes
}
@Composable
fun PracticeNavigationWithOutData(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = PracticeNavigationRoutes.Home
    ){
        composable<PracticeNavigationRoutes.Home>{
            PracticeHomeScreen(
                onNavigationToProfile = {
                    navController.navigate(PracticeNavigationRoutes.Profile){
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<PracticeNavigationRoutes.Profile>{
            PracticeProfileScreen(
                onNavigationBackToHome = {
                    if(navController.previousBackStackEntry!=null){
                        navController.popBackStack()
                    }
                }
            )
        }
    }
}

// Screen 1

@Composable
fun PracticeHomeScreen(onNavigationToProfile:()-> Unit){
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Practice Navigation",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = Poppins,
            color = wineash
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Developer : ABIR ASP",
            fontSize = 26.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.ExtraBold,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Miravolabs",
            fontSize = 18.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Medium,
            color = wineash,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .clickable{
                    val webIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://miravolabsweb.web.app")
                    )
                    context.startActivity(webIntent)
                }
        )
        Spacer(modifier = Modifier.height(14.dp))
        OutlinedButton(
            onClick = onNavigationToProfile,
            border = BorderStroke(1.dp, color = wineash),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowRight, contentDescription = "Right Arrow")
            Text(
                text  = "Go To Profile Screen",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Screen 1(No Data)",
            fontFamily = Poppins,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.LightGray
        )
    }
}
// Screen 2
@Composable
fun PracticeProfileScreen(onNavigationBackToHome:()-> Unit){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text =" This is type-safe navigation profile screen",
            fontFamily = Poppins,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            color = wineash
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "This App Developer is Abir",
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            color = wineash
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = onNavigationBackToHome,
            colors = ButtonDefaults.buttonColors(
                containerColor = wineash,
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.dp
            ),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier.fillMaxWidth(0.5f)
        ){
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Go Back",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "No data",
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,
            color = Color.DarkGray,
            fontSize = 20.sp
        )
    }
}
*/
//                  Type-Safe navigation Practice with data
/*
@Serializable
sealed interface NavigationWithDataRoutes{
    @Serializable
    data object HomeRoute : NavigationWithDataRoutes
    @Serializable
    data class ProfileRoutes(val userName : String) : NavigationWithDataRoutes
}
@Composable
fun PracticeNavigationWithData(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationWithDataRoutes.HomeRoute
    ){
        composable<NavigationWithDataRoutes.HomeRoute>{
            PracticeHomeScreenWithData(
                onNavigateToProfile = {enteredName ->
                    navController.navigate(
                        NavigationWithDataRoutes.ProfileRoutes(userName = enteredName)
                    ){
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<NavigationWithDataRoutes.ProfileRoutes>{ backStackEntry ->
            val profileRoute = backStackEntry.toRoute<NavigationWithDataRoutes.ProfileRoutes>()
            PracticeProfileScreenWithData(
                userName = profileRoute.userName,
                onNavigateBackToHome = {
                    if(navController.previousBackStackEntry != null){
                        navController.popBackStack()
                    }
                }
            )
        }
    }
}
// 1st screen
@Composable
fun PracticeHomeScreenWithData(onNavigateToProfile:(String)-> Unit){
    var inputName by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Type-Safe Navigation Practice,with data send",
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = wineash
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = inputName,
            onValueChange = {inputName = it},
            label = {Text("Enter your Name", fontFamily = Poppins)},
            shape = RoundedCornerShape(6.dp),
            singleLine = true ,
            modifier = Modifier.fillMaxWidth(0.85f)
        )
        Spacer(modifier= Modifier.height(14.dp))
        Button(
            onClick = {
                val finalName = if (inputName.isNotBlank()) inputName else "Guest User"
                onNavigateToProfile(finalName)
            },
            colors = ButtonDefaults.buttonColors(containerColor = wineash),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier.fillMaxWidth(0.7f)
        ) { Icon(
            Icons.AutoMirrored.Filled.ArrowRight, contentDescription = null
        )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Go Profile",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                color =Color.White
            )
        }
    }
}
// 2nd Screen
@Composable
fun PracticeProfileScreenWithData(
    userName : String,
    onNavigateBackToHome : ()-> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome, $userName",
            fontFamily = Poppins,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color =wineash
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = onNavigateBackToHome,
            colors = ButtonDefaults.buttonColors(containerColor = wineash),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Back",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                color  = Color.White
            )
        }
    }
}
*/
/*
@Serializable
sealed interface MultiRoutesNav{
    @Serializable
    data object MainInputRoute : MultiRoutesNav
    @Serializable
    data class ResultRoute(
        val id : Int,
        val name : String,
        val address: String,
        val roll : Int,
        val section : String,
        val favSubject : String
    ) : MultiRoutesNav
}
@Composable
fun MultiDataRoutesApp(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MultiRoutesNav.MainInputRoute
    ){
        composable<MultiRoutesNav.MainInputRoute>{
            MainScreen(
                onNavigateToResultScreen = {name , id , roll , section , address , favsubject ->
                    navController.navigate(
                        MultiRoutesNav.ResultRoute(
                            name = name,
                            id = id,
                            roll =  roll,
                            address =  address,
                            favSubject = favsubject,
                            section = section
                        )
                    ){launchSingleTop  = true }
                }
            )
        }
        composable<MultiRoutesNav.ResultRoute>{backStackEntry ->
            val studentInfo = backStackEntry.toRoute<MultiRoutesNav.ResultRoute>()
            MainResultScreen(
                studentInfo  = studentInfo,
                onBack = {
                    if (navController.previousBackStackEntry != null ){
                        navController.popBackStack()
                    }
                }
            )
        }
    }
}
// Screen A
@Composable
fun MainScreen(
    onNavigateToResultScreen:(String, Int, Int, String, String, String) -> Unit
){
    var id by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var address by remember {mutableStateOf("")}
    var roll by remember { mutableStateOf("") }
    var section by remember { mutableStateOf("") }
    var favSubject by remember { mutableStateOf("") }
    var verticalScroll = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(verticalScroll),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "User Profile From",
            fontSize = 24.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            color = wineash
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = name    ,
            onValueChange = {name = it},
            label = {Text("Name", fontFamily = Poppins)},
            placeholder = {Text("Enter your name", fontFamily = Poppins)},
            singleLine = true,
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = id,
            onValueChange = {id = it},
            label = {Text("ID", fontFamily = Poppins)},
            placeholder = {Text("Enter your student Id", fontFamily = Poppins)},
            singleLine =  true,
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth(0.9f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = roll,
            onValueChange = {roll = it},
            label = {Text("Roll", fontFamily = Poppins)},
            placeholder = {Text("Enter your student roll", fontFamily = Poppins)},
            singleLine = true,
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth(0.9f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = section,
            onValueChange = {section = it},
            label = {Text("Section", fontFamily = Poppins)},
            placeholder = {Text("Enter your section name", fontFamily = Poppins )},
            singleLine = true,
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = address,
            onValueChange = {address = it},
            label = {Text("Address", fontFamily = Poppins)},
            placeholder = {Text("Enter your address", fontFamily = Poppins)},
            singleLine = true,
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = favSubject,
            onValueChange = {favSubject = it},
            label = {Text("Favirate Subject", fontFamily = Poppins)},
            placeholder = {Text("Enter your favarate subject name", fontFamily = Poppins) },
            singleLine = true,
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Spacer(modifier = Modifier.height(26.dp))
        Button(
            onClick = {
                val idInt = id.toIntOrNull() ?: 0
                val rollInt = roll.toIntOrNull() ?: 0
                onNavigateToResultScreen(
                    name,
                    idInt,
                    rollInt,
                    section,
                    address,
                    favSubject
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = wineash,
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.dp
            )
        ) {
            Text(
                text = "Submit Data",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                Icons.AutoMirrored.Filled.ArrowRight,
                contentDescription = "Arrow Icon"
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}
// Screen B
@Composable
fun MainResultScreen(
    studentInfo : MultiRoutesNav.ResultRoute,
    onBack: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color(0xFFF9F9F9)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = "Result Screen",
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = wineash
        )
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .background(Color(0xFFF9F9F9)),
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, color = Color.Gray)
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = "Student Info",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = wineash,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Student Name: ${studentInfo.name}",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Medium,
                    color =  wineash,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Student ID: ${studentInfo.id}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = wineash,
                    fontFamily = Poppins
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Student Roll: ${studentInfo.roll}",
                    fontFamily = Poppins,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = wineash
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Section Name: ${studentInfo.section}",
                    fontFamily = Poppins,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = wineash
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Student Address: ${studentInfo.address}",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    color = wineash,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text  = "Favirate Subject: ${studentInfo.favSubject}",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Poppins,
                    color = Color.Gray
                )
            }
        }
        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(0.85f),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp,wineash)
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Arrow Icon"
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Back To Home",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
*/

//                      Data Store Ppreferences
/*
val Context.simpleStorage by preferencesDataStore(name = "test_storage")
val Note_KEY = stringPreferencesKey("user_note")

@Composable
fun SimpleDataStoreScreen(){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var inputNote by remember { mutableStateOf("") }

   val savedNoteFormPhone by remember(context){
       context.simpleStorage.data.map { preferences ->
           preferences[Note_KEY] ?: "এখনো কিছু সেভ করা হয়নি"
       }
   }.collectAsState(initial = "Loading....")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ফোনে সেভ থাকা ডেটা:",
            fontSize = 18.sp
        )
        Text(
            text = savedNoteFormPhone,
            fontSize = 22.sp,
            color = wineash
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = inputNote,
            onValueChange = {inputNote = it},
            label = {Text("নতুন কিছু লিখুন")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if(inputNote.isNotBlank()){
                scope.launch {
                    context.simpleStorage.edit { preferences ->
                        preferences[Note_KEY] = inputNote
                    }
                    inputNote = ""
                }
            }},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Save to DataStore"
            )
        }
    }
}
*/

/*
val Context.authStorage by preferencesDataStore("auth_storage")

val EMAIL_KEY = stringPreferencesKey("user_email")
val PASSWORD_KEY = stringPreferencesKey("user_password")

@Composable
fun EmailPasswordDataStoreScreen(){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var inputEmail by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }

    val savedAuthData by remember(context){
        context.authStorage.data.map { preferences ->
            val email = preferences[EMAIL_KEY] ?: "No Email"
            var password = preferences[PASSWORD_KEY] ?: "No Password"
            Pair(email,password)
        }
    }.collectAsState(initial = Pair("Loading...","Loading..."))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Simple DataStore Learning Screen",
            fontSize = 24.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold,
            color = wineash
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = inputEmail,
            onValueChange = {inputEmail = it},
            label = {Text("Enter your email")},
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = inputPassword,
            onValueChange = {inputPassword = it},
            label = {Text("Enter your password")},
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if(inputEmail.isNotBlank() && inputPassword.isNotBlank()){
                    scope.launch {
                        context.authStorage.edit { preferences ->
                            preferences[EMAIL_KEY] = inputEmail
                            preferences[PASSWORD_KEY] = inputPassword
                        }
                        inputPassword = ""
                        inputEmail = ""
                    }
                }
            },
            modifier = Modifier.fillMaxWidth().height(40.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Save Email & Password",
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedButton(
            onClick = {
                scope.launch {
                    context.authStorage.edit { preferences ->
                        preferences.clear()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.Red)
        ) {
            Text(
                text = "Clear / Logout",
                color = Color.Red,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ){
            Column(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Saved Data",
                    fontSize = 20.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    color =  wineash
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text  = "Saved Email: ${savedAuthData.first}",
                    fontFamily = Poppins,
                    fontSize = 15.sp,
                    color = Color.DarkGray,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(4.dp  ))
                Text(
                    text = "Saved Password: ${savedAuthData.second}",
                    fontFamily = Poppins,
                    fontSize = 15.sp,
                    color = Color.DarkGray,
                    maxLines = 1
                )
            }
        }
    }
}
*/
/*
val Context.multiTypeStorage by preferencesDataStore("multi_type_storage")

val  USER_NAME_KEY  = stringPreferencesKey("user_name")
val PHONE_NUMBER_KEY = intPreferencesKey("phone_number")
val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")

@Composable
fun MultiTypeDataStoreScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var inputName by remember { mutableStateOf("") }
    var inputNumber by remember { mutableStateOf("") }
    var inputIsLoggedIn by remember { mutableStateOf(false) }

    val savedData by remember(context) {
        context.multiTypeStorage.data.map { preferences ->
            val name = preferences[USER_NAME_KEY] ?: "No Name"
            val phone = preferences[PHONE_NUMBER_KEY] ?: 0
            val isLoggedIn = preferences[IS_LOGGED_IN_KEY] ?: false
            Triple(name, phone, isLoggedIn)
        }
    }.collectAsState(initial = Triple("Loading...", 0, false))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Multi DataStore Screen",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,
            color = wineash
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = inputName,
            onValueChange = {inputName = it},
            placeholder = {Text("Enter your name")},
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = inputNumber ,
            onValueChange = {inputNumber = it},
            placeholder = {Text("Enter your phone number")},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Is Logged In? (Boolean)",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold
            )
            Checkbox(
                checked = inputIsLoggedIn,
                onCheckedChange = {inputIsLoggedIn = it}
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if(inputName.isNotBlank() && inputNumber.isNotBlank()){
                    scope.launch {
                        context.multiTypeStorage.edit { preferences ->
                            preferences[USER_NAME_KEY] = inputName
                            preferences[PHONE_NUMBER_KEY] = inputNumber.toIntOrNull() ?: 0
                            preferences[IS_LOGGED_IN_KEY] = inputIsLoggedIn
                        }
                        inputName = ""
                        inputNumber = ""
                        inputIsLoggedIn = false
                    }
                }
            },
            modifier = Modifier.fillMaxWidth().height(60.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = wineash,
                contentColor = Color.White
            )
        ){
            Text(
                text = "Save all data",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(
            onClick = {
                scope.launch {
                    context.multiTypeStorage.edit { it.clear() }
                }
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().height(50.dp),
            border = BorderStroke(1.dp, color = Color.Red)
        ) {
            Text(
                text = "Clear all data",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.Red
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                contentColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ){
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Saved Data Preview",
                    fontFamily = Poppins,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = wineash
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Name: ${savedData.first}", fontSize = 15.sp,color = Color.DarkGray)
                Text("Number: ${savedData.second}", fontSize = 15.sp,color = Color.DarkGray)
                Text("Is Logged In: ${savedData.third}", fontSize = 15.sp,color = Color.DarkGray)
            }
        }
    }
}
*/

// Old Login Screen(Navigation Practice)



val Context.appAuthStorage by preferencesDataStore("auth_session")

val KEY_IS_LOGGED_IN = booleanPreferencesKey("key_is_logged_in")
val KEY_USER_NAME  = stringPreferencesKey("key_user_name")
@Serializable
sealed interface MyRoutes {
    @Serializable
    data object Login : MyRoutes
    @Serializable
    data class Home(val userName : String) : MyRoutes
    @Serializable
    data object Setting : MyRoutes
}
@Composable
fun ThreeScreenAndDataApp(){

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val authSession by remember(context){
        context.appAuthStorage.data.map { preferences ->
            val isLoggedIn = preferences[KEY_IS_LOGGED_IN] ?: false
            val userName = preferences[KEY_USER_NAME] ?: ""
            Pair(isLoggedIn,userName)
        }
    }.collectAsState(initial = null)

    if(authSession == null){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
        return
    }

    val (isLoggedIn,savedName) = authSession!!
    val navController = rememberNavController()

    val initialRoute: MyRoutes = if(isLoggedIn){
        MyRoutes.Home(userName = savedName)
    }else{
        MyRoutes.Login
    }
    NavHost(
        navController = navController,
        startDestination = initialRoute
    ){
        composable<MyRoutes.Login>{
            LoginScreen(
                onLoginSuccess = {inputName ->
                    scope.launch {
                        context.appAuthStorage.edit { preferences ->
                            preferences[KEY_IS_LOGGED_IN] = true
                            preferences[KEY_USER_NAME] = inputName
                        }
                    }
                    navController.navigate(MyRoutes.Home(userName = inputName)){
                        popUpTo(MyRoutes.Login){
                            inclusive = true
                        }
                        launchSingleTop= true
                    }
                }
            )
        }
        composable<MyRoutes.Home>{backStackEntry ->
            val homeRoute = backStackEntry.toRoute<MyRoutes.Home>()
            HomeScreen(
                userName = homeRoute.userName,
                onNavigateToSetting = {
                    navController.navigate(MyRoutes.Setting)
                }
            )
        }
        composable<MyRoutes.Setting>{
            SettingScreen(
                onLogout = {
                    scope.launch {
                        context.appAuthStorage.edit { it.clear() }
                    }
                    navController.navigate(MyRoutes.Login){
                        popUpTo(0){
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
@Composable
fun LoginScreen(onLoginSuccess:(String) -> Unit){
    var name by remember { mutableStateOf("") }
    Column(
        modifier =  Modifier.fillMaxSize().padding(),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login Screen", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = name ,
            onValueChange = { name = it},
            label = { Text("Email")},
            placeholder = {Text("Enter email address")}
        )
        Spacer(modifier = Modifier.height(6.dp))
        Button(
            onClick = {
                if(name.isNotBlank()) {
                    onLoginSuccess(name)
                }
            }
        ){
            Text("Login")
        }
    }
}
@Composable
fun HomeScreen(
    userName : String,
    onNavigateToSetting:() -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to Home Screen", fontSize = 26.sp)
        Spacer(modifier = Modifier.height(6.dp))
        Text("Hello, $userName", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(6.dp))
        Button(onClick = onNavigateToSetting) {
            Text("Settings")
        }
    }
}
@Composable
fun SettingScreen(onLogout : () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Setting Screen", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(6.dp))
        Button(onClick = onLogout) {
            Text("Log Out")
        }
    }
}