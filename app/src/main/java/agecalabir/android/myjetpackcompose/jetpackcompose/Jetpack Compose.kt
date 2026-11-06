package agecalabir.android.myjetpackcompose.jetpackcompose
import agecalabir.android.myjetpackcompose.R
import agecalabir.android.myjetpackcompose.ui.theme.Poppins
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


//@Preview(showBackground = true)
@Composable
fun MyfirstOutlinedTextField() {
    var email by remember { mutableStateOf("") }
    OutlinedTextField(
        value = email,
        onValueChange = { email = it },
        label = { Text("Email Address") },
        placeholder = { Text("example@gmail.com")},
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = mycolor,
            unfocusedBorderColor = mycolor,
            focusedLabelColor = mycolor,
            cursorColor = Color.Black
        ),
        singleLine = true,
        shape =  RoundedCornerShape(16.dp), // CircleShape,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Mail,
                contentDescription = "Email Icon",
                tint = mycolor
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        )
    )
}
//@Preview(showBackground = true, showSystemUi = true )
@Composable
fun MyFirsRow(){
    var email by remember { mutableStateOf("")}
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Email",
            color = mycolor,
            fontSize = 25.sp
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label  = { Text("Email Address") },
            placeholder = { Text("example@gmail.com")},

        )
    }
}
val mycolor =  Color(0xFFFF3366)
//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyFirstColmunandRowandBox(){
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center,

        ){
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Pofile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                   // .border(width = 3.dp, color=Color.Blue, shape = (CircleShape))
            )
        }
        Text(
            text = "Profile / Account Login",
            color = mycolor,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            placeholder = {Text("example@gmail.com")},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Mail,
                    contentDescription = "Email Icon",
                    tint = mycolor
                )
            },
            shape = RoundedCornerShape(25.dp),
            maxLines = 1,
            singleLine = true
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            placeholder = { Text("Enter Your Password")},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Password,
                    contentDescription = "Password Icon",
                    tint = mycolor
                )
            },
            shape = RoundedCornerShape(25.dp),
            maxLines = 1,
            singleLine = true
        )
            Text(
                text = "Login",
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .clip(RoundedCornerShape(20.dp))
                    .background(mycolor)
                    .clickable(onClick = { })
                    .padding(vertical = 16.dp)
            )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Create an acount",
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable{}
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Login",
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable { }
            )
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyFistButton(){
// Defult Button
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                Toast.makeText(context,
                    "Button Clicked!",
                    Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
            containerColor = mycolor,
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 8.dp,  // স্বাভাবিক অবস্থায় শ্যাডো
                pressedElevation = 2.dp,  // চাপ দিলে বাটন নিচের দিকে দেবে যাবে
                hoveredElevation = 10.dp  // কার্সর আনলে শ্যাডো বাড়বে
            )
        ) {
            Text("Defult Button")
        }
        // OutlinedButton
        OutlinedButton(
            onClick = {Toast.makeText(context
                ,"Removed"
                ,Toast.LENGTH_SHORT).show()},
            modifier = Modifier.fillMaxWidth(0.3f),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(width = 1.dp , color = Color.Blue)

        ) {
            Text("Delete",
                color = mycolor)
        }
// Text Button
        TextButton(
            onClick = {Toast.makeText(context,
                "Wait for Admin Approval",
                Toast.LENGTH_SHORT).show()}
        ) {
            Text("Forget Password?",
                color = Color.Gray)
        }
        // Icon Button
        IconButton(
            onClick = {Toast.makeText(context,
                "Saved to Favorite ",
                Toast.LENGTH_SHORT).show()}
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite Icon",
                tint = mycolor
            )
        }
    }
}
//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyFirstCard(){
   Box(
       modifier = Modifier
           .fillMaxSize()
           .padding(16.dp),
       contentAlignment = Alignment.Center
   ){
       Card(
           modifier = Modifier.fillMaxWidth(),
           shape = RoundedCornerShape(16.dp),
           colors = CardDefaults.cardColors(
               containerColor = Color.White
           ),
           elevation = CardDefaults.cardElevation(
               defaultElevation = 8.dp
           )
       )
       {
           Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(16.dp),
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.spacedBy(16.dp)
           ){
               Image(
                   painter = painterResource(id = R.drawable.abir_pic),
                   contentDescription = "Profile Pic",
                   contentScale = ContentScale.Crop,
                   modifier = Modifier
                       .size(60.dp)
                       .clip(CircleShape)
               )
           }
           Column {
               Text(
                   text = "Abir Hossen",
                   fontWeight = FontWeight.Bold,
                   fontSize = 18.sp,
                   color = Color.Black
               )
               Spacer(modifier = Modifier.height(4.dp))
               Text(
                   text = "Android App Developer",
                   fontSize = 14.sp,
                   color = Color.Gray
               )
           }
       }
   }
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TestRowColumn(){
    Row(
        modifier =  Modifier
            .fillMaxSize()
            .padding(top = 25.dp, bottom = 25.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Pofile Picture",
            contentScale = ContentScale.Crop)
    }
    Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
    contentAlignment = Alignment.Center
    ){
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.abir_pic),
                    contentDescription = "Profile Pic",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                )
            }
            Column {
                Text(
                    text = "Abir Hossen",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Android App Developer",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
@Preview
@Composable
fun IconExample(){
    Column(
        modifier = Modifier.fillMaxSize().padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = wineash)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Icon(
                    painter = painterResource(R.drawable.house),
                    contentDescription = "Home icon",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text ="Go Home",
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Poppins,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
        }
    }
}