package agecalabir.android.myjetpackcompose.data_store

import agecalabir.android.myjetpackcompose.jetpackcompose.wineash
import agecalabir.android.myjetpackcompose.ui.theme.Poppins
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable


val Context.dataStore by preferencesDataStore(name = "note_storage")
val NOTE_STORAGE_KEY = stringPreferencesKey("note_app_data")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteApp(){

    var text by remember { mutableStateOf("") }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val savedNote by remember(context){
        context.dataStore.data.map { preferences ->
            preferences[NOTE_STORAGE_KEY]
        }
    }.collectAsState(initial = null)

    LaunchedEffect(savedNote) {
        if(savedNote != null && text.isEmpty()){
            text = savedNote!!
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Note App", fontFamily = Poppins, fontSize = 26.sp)},
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                    scope.launch {
                                        context.dataStore.edit { preferences ->
                                            preferences[NOTE_STORAGE_KEY] = text
                                        }
                                    }
                                    Toast.makeText(context,"Note Saved Successfully",Toast.LENGTH_SHORT).show()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(
                                text = "Save",
                                fontSize = 18.sp,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(
                            onClick = {/*Demo Icon*/}
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More"
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                )
            )
        }
    ) {innerPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            BasicTextField(
                value = text,
                onValueChange = {text = it},
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = Poppins
                ),
                cursorBrush = SolidColor(wineash),
                decorationBox = {innerTextField->
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                    contentAlignment = Alignment.TopStart
                    ){

                        if (text.isEmpty()){
                            Text(
                                text = "Type your text from here...",
                                fontFamily = Poppins,
                                color = Color.Gray,
                                fontSize = 16.sp
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }
    }
}


// Test Function
@Composable
fun Test(){
    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello Android",
            fontSize = 40.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold
        )
    }
}

/*

Kotlin
// String (টেক্সট) রাখার জন্য
val USER_NAME_KEY = stringPreferencesKey("user_name")

// Int (সংখ্যা) রাখার জন্য
val USER_AGE_KEY = intPreferencesKey("user_age")

// Boolean (True/False) রাখার জন্য
val IS_DARK_MODE_KEY = booleanPreferencesKey("dark_mode_enabled")


২. Write করা (কীভাবে ডেটা সেভ করবেন):
আগের মতোই edit ফাংশন ব্যবহার করবেন। আপনি চাইলে একটি ডেটা সেভ করতে পারেন, আবার চাইলে একসাথে অনেকগুলো ডেটাও সেভ করতে পারেন।

Kotlin
scope.launch {
    context.dataStore.edit { preferences ->
        preferences[USER_NAME_KEY] = "Abir ASP"
        preferences[USER_AGE_KEY] = 15
        preferences[IS_DARK_MODE_KEY] = true
    }
}
৩. Read করা (কীভাবে ডেটা পড়বেন):
আগের মতোই data.map ব্যবহার করে আলাদা আলাদা Flow বানাতে পারেন এবং Compose-এ collectAsState দিয়ে পড়তে পারেন।

Kotlin
// নাম পড়া (ডেটা না থাকলে ডিফল্ট ভ্যালু "Guest" দেখাবে)
val userName by remember(context) {
    context.dataStore.data.map { preferences ->
        preferences[USER_NAME_KEY] ?: "Guest"
    }
}.collectAsState(initial = "Guest")

// ডার্ক মোড চেক করা (ডেটা না থাকলে ডিফল্ট ভ্যালু false হবে)
val isDarkMode by remember(context) {
    context.dataStore.data.map { preferences ->
        preferences[IS_DARK_MODE_KEY] ?: false
    }
}.collectAsState(initial = false)
৪. Delete করা (কীভাবে নির্দিষ্ট কোনো ডেটা মুছবেন):
ধরা যাক, ইউজার তার প্রোফাইল মুছে ফেলেছে, তাই আপনি শুধু ইউজারের নাম DataStore থেকে ডিলিট করতে চান। এক্ষেত্রে .remove() ফাংশন ব্যবহার করতে হবে।

Kotlin
scope.launch {
    context.dataStore.edit { preferences ->
        preferences.remove(USER_NAME_KEY) // শুধু নাম মুছে যাবে, বাকিগুলো ঠিক থাকবে
    }
}
৫. Clear করা (কীভাবে সব ডেটা একসাথে মুছবেন):
যদি ইউজার অ্যাপ থেকে লগ-আউট করে এবং আপনি চান DataStore-এর সেভ করা সবকিছু (নাম, বয়স, ডার্ক মোড ইত্যাদি) একসাথে মুছে যাক, তাহলে .clear() ব্যবহার করবেন।

Kotlin
scope.launch {
    context.dataStore.edit { preferences ->
        preferences.clear() // DataStore-এর সব Key এবং ডেটা পুরোপুরি মুছে যাবে
    }
}
এভাবেই একটি মাত্র dataStore ফাইলের ভেতর আপনি অসংখ্য Key বানিয়ে ভিন্ন ভিন্ন ডেটা সুন্দরভাবে ম্যানেজ করতে পারবেন।
*
* */

// Data store practice

val Context.practiceDataStore by preferencesDataStore(name = "practice_store")
val NAME_KEY = stringPreferencesKey("user_name")
val AGE_KEY = intPreferencesKey("user_age")
val IS_DEV_KEY  = booleanPreferencesKey("id_developer")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataStoreMasterApp(){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val savedName by remember(context){
        context.practiceDataStore.data.map { prefs -> prefs[NAME_KEY] ?: "No Name" }
    }.collectAsState(initial = "Loading")

    val savedAge by remember(context) {
        context.practiceDataStore.data.map { prefs -> prefs[AGE_KEY] ?: 0 }
    }.collectAsState(initial =  0)

    val savedIsDev by remember(context){
        context.practiceDataStore.data.map { prefs-> prefs[IS_DEV_KEY] ?: false}
    }.collectAsState(initial = false)

    var nameInput by remember { mutableStateOf("") }
    var ageInput by remember { mutableStateOf("") }
    var isDevInput by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("DataStore Master")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                )
            )
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = nameInput,
                onValueChange = {nameInput = it},
                label = {Text("Enter Name")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = ageInput,
                onValueChange = {ageInput = it},
                label = {Text("Enter Age")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Checkbox(
                    checked = isDevInput,
                    onCheckedChange = {isDevInput = it}
                )
                Text(
                    text = "Are you an Android Developer?"
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.SpaceEvenly
            ){
                Button(
                    onClick = {
                        scope.launch {
                            context.practiceDataStore.edit { prefs->
                                prefs[NAME_KEY] = nameInput
                            prefs[AGE_KEY] = ageInput.toIntOrNull()?: 0
                                prefs[IS_DEV_KEY] = isDevInput
                            }
                        }
                        Toast.makeText(context,"All Data Saved!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Save All"
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        scope.launch {
                            context.practiceDataStore.edit { prefs->
                                prefs.clear()
                            }
                        }
                        Toast.makeText(context,"DataStore Cleared!",Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Clear All"
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = {
                    scope.launch {
                        context.practiceDataStore.edit { prefs->
                            prefs.remove(NAME_KEY)
                        }
                    }
                    Toast.makeText(context,"Name Removed!",Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Delete Name Only"
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                )
            ){
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("Live DataStore Status:", fontWeight = FontWeight.Bold, fontSize = 18.sp )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Name: $savedName")
                    Text("Age: $savedAge")
                    Text("Is Developer?: ${ if(savedIsDev) "Yes" else "No" }")
                }
            }
        }
    }
}

// Data store practice 07/11/2026

val Context.megaDataStore by preferencesDataStore("mega_storage")

val KEY_NAME = stringPreferencesKey("user_name")
val KEY_AGE = intPreferencesKey("user_age")
val KEY_IS_PREMIUM = booleanPreferencesKey("is_premium")
val KEY_APP_APP_RATING = floatPreferencesKey("app_rating")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UltimateDataStoreApp(){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val savedName by remember(context){
        context.megaDataStore.data.map { prefs-> prefs[NAME_KEY] ?: "No Name Saved Yet" }
    }.collectAsState(initial = "Loading...")

    val savedAge by remember(context) {
        context.megaDataStore.data.map { prefs -> prefs[AGE_KEY] ?: 0 }
    }.collectAsState(initial = 0)

    val isPremium by remember(context){
        context.megaDataStore.data.map { prefs-> prefs[KEY_IS_PREMIUM] ?: false }
    }.collectAsState(initial = false)

    val savedRating by remember(context) {
        context.megaDataStore.data.map { prefs-> prefs[KEY_APP_APP_RATING]?: 0.0f}
    }.collectAsState(initial = 0.0f)

    var nameInput by remember { mutableStateOf("") }
    var ageInput by remember { mutableStateOf("") }
    var premiumInput by remember { mutableStateOf(false) }
    var ratingInput by remember { mutableFloatStateOf(0f) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Ultimate DataStore")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                ),
                elevation = CardDefaults.cardElevation(4.dp)
            ){
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Live DataStore Status:", fontWeight = FontWeight.Bold, fontSize = 20.sp,color = MaterialTheme.colorScheme.primary, fontFamily = Poppins)
                    HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                    Text("Name: $savedName", fontSize = 16.sp)
                    Text("Age: $savedAge", fontSize = 16.sp)
                    Text("Premium User: ${if(isPremium) "Yes" else "No"}", fontSize = 16.sp)
                    Text("App Rating: ${"%.1f".format(savedRating)} ⭐", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = nameInput,
                onValueChange = {nameInput = it},
                label = {Text("Enter Name")},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = ageInput,
                onValueChange = {ageInput = it},
                label = {Text("Enter Age")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text("Enable Premium Features?", fontSize = 16.sp)
                Switch(
                    checked = premiumInput,
                    onCheckedChange = {premiumInput = it}
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text("Rate this app: ${"%.1f".format(ratingInput)}", modifier = Modifier.align(Alignment.Start))
            Slider(
                value = ratingInput,
                onValueChange = {ratingInput = it},
                valueRange = 0f..5f,
                steps = 4
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    scope.launch {
                        context.megaDataStore.edit { prefs->
                            if(nameInput.isNotBlank()) prefs[KEY_NAME] = nameInput
                            if(ageInput.isNotBlank()) prefs[KEY_AGE] = ageInput.toIntOrNull() ?: 0
                            prefs[KEY_IS_PREMIUM] = premiumInput
                            prefs[KEY_APP_APP_RATING] = ratingInput
                        }
                    }
                    Toast.makeText(context,"All Data Saved!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Save All Data", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                OutlinedButton(
                    onClick = {
                        scope.launch {
                            context.megaDataStore.edit { prefs->
                                prefs[KEY_APP_APP_RATING] = ratingInput
                            }
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Update Rating Only", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedButton(
                    onClick = {
                        scope.launch {
                            context.megaDataStore.edit {prefs->
                                prefs.remove(KEY_IS_PREMIUM)
                            }
                        }
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {Text("Revoke Premium", fontSize = 16.sp) }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ElevatedButton(
                onClick = {
                    scope.launch {
                        context.megaDataStore.edit { prefs->
                            prefs.clear()
                        }
                    }
                    Toast.makeText(context, "DataStore Format Complete!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Clear")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Format/Clear DataStore", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(10.dp))

        }
    }
}


// Data Store Practice 08/11/2026

val Context.grandMasterDataStore by preferencesDataStore(name = "miravo_core_setting")

val KEY_USER_NAME = stringPreferencesKey("user_name")
val KEY_USER_AGE = intPreferencesKey("user_age")
val KEY_DARK_MODE = booleanPreferencesKey("dark_mode_enabled")
val KEY_NOTIFICATIONS = booleanPreferencesKey("notifications_enabled")
val KEY_UI_SCALE = floatPreferencesKey("ui_scale")
val KEY_LAST_LOGIN = longPreferencesKey("last_login_timestamp")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataStoreGrandMasterApp() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // Read DataStore

    val name by remember(context) {
        context.grandMasterDataStore.data.map { prefs ->
            prefs[KEY_USER_NAME] ?: "Guest User"
        }
    }.collectAsState(initial = "Loading...")

    val age by remember(context) {
        context.grandMasterDataStore.data.map { prefs ->
            prefs[KEY_USER_AGE] ?: 0
        }
    }.collectAsState(initial = 0)

    val isDarkMode by remember(context) {
        context.grandMasterDataStore.data.map { prefs ->
            prefs[KEY_DARK_MODE] ?: false
        }
    }.collectAsState(initial = false)

    val isNotifEnable by remember(context) {
        context.grandMasterDataStore.data.map {
            it[KEY_NOTIFICATIONS] ?: true// prefs , it
        }
    }.collectAsState(initial = true)

    val uiScale by remember(context) {
        context.grandMasterDataStore.data.map {
            it[KEY_UI_SCALE] ?: 1.0f
        }
    }.collectAsState(initial = 1.0f)

    val lastLogin by remember(context) {
        context.grandMasterDataStore.data.map {
            it[KEY_LAST_LOGIN] ?: 0L
        }
    }.collectAsState(initial = 0L)

    // User Input
    var inputName by remember { mutableStateOf("") }
    var inputAge by remember { mutableStateOf("") }
    var inputDarkMode by remember { mutableStateOf(false) }
    var inputNotif by remember { mutableStateOf(true) }
    var inputUiScale by remember { mutableFloatStateOf(1.0f) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Setting DashBoard", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    Icon(
                        Icons.Default.Settings,
                        contentDescription = "Setting",
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Live DataStore Monitor",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    Text("Name: $name")
                    Text("Age: $age")
                    Text("Dark Mode: ${if (isDarkMode) "ON" else "OFF"}")
                    Text("Notifications: ${if (isNotifEnable) "ON" else "OFF"}")
                    Text("UI Scale: ${"%.2f".format(uiScale)}x")
                    Text("Last Action: $lastLogin")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "Profile Setting",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            OutlinedTextField(
                value = inputName,
                onValueChange = { inputName = it },
                label = { Text("Update Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = inputAge,
                onValueChange = { inputAge = it },
                label = { Text("Update Age") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "App Preferences",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Enable Dark Mode")
                Switch(
                    checked = inputDarkMode,
                    onCheckedChange = { inputDarkMode = it }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Allow Notification")
                Switch(
                    checked = inputNotif,
                    onCheckedChange = { inputNotif = it }
                )
            }
            Text(
                "UI Scale: ${"%.1f".format(inputUiScale)}",
                modifier = Modifier.align(Alignment.Start)
            )
            Slider(
                value = inputUiScale,
                onValueChange = { inputUiScale = it },
                valueRange = 0.5f..2.0f
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    scope.launch {
                        context.grandMasterDataStore.edit {
                            if (inputName.isNotBlank()) it[KEY_USER_NAME] = inputName
                            if (inputAge.isNotBlank()) it[KEY_USER_AGE] =
                                inputAge.toIntOrNull() ?: 0
                            it[KEY_DARK_MODE] = inputDarkMode
                            it[KEY_NOTIFICATIONS] = inputNotif
                            it[KEY_UI_SCALE] = inputUiScale
                            it[KEY_LAST_LOGIN] = System.currentTimeMillis()
                        }
                    }
                    Toast.makeText(context, "Settings Updated!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Save All Setting", fontSize = 16.sp, modifier = Modifier.padding(4.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    onClick = {
                        scope.launch {
                            context.grandMasterDataStore.edit {
                                it[KEY_DARK_MODE] = !isDarkMode
                            }
                            Toast.makeText(
                                context,
                                "Toggle Theme Successfully!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Toggle Theme Only", fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedButton(
                    onClick = {
                        scope.launch {
                            context.grandMasterDataStore.edit {
                                it.remove(KEY_USER_NAME)
                            }
                        }
                        Toast.makeText(
                            context,
                            "User Name Removed Successfully!",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
                ) {
                    Text(text = "Clear Name", fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedCard(
                colors = CardDefaults.outlinedCardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer.copy(
                        alpha = 0.3f
                    )
                ),
                border = CardDefaults.outlinedCardBorder(true).copy(brush = SolidColor(Color.Red))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Danger Zone", color = Color.Red, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    ElevatedButton(
                        onClick = {
                            scope.launch {
                                context.grandMasterDataStore.edit {
                                    it.clear()
                                }
                            }
                            Toast.makeText(context, "All Data Wiped!", Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        )
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Wipe")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Factory Reset DataStore")
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

        }
    }
}


// Practice Get Started Screen


val Context.myDataStore by preferencesDataStore("data_store")
val IS_FIRST_TIME_KEY = booleanPreferencesKey("is_first_time")
// new key
val USER_NAME_KEY = stringPreferencesKey("user_name")

/*
@Composable
fun AppNavigation(){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val isFirstTime by remember(context) {
        context.myDataStore.data.map {
            it[IS_FIRST_TIME_KEY] ?: true
        }
    }.collectAsState(initial =  true)

    if(isFirstTime){
        OnboardingScreen(
            onGetStartedClick = {
                scope.launch {
                    context.myDataStore.edit {
                        it[IS_FIRST_TIME_KEY] = false
                    }
                }
            }
        )
    } else {
        HomeScreen()
    }
}
/*

 */
@Composable
fun OnboardingScreen(onGetStartedClick: () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to MiravoLabs!", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = onGetStartedClick) {
            Text("Get Started")
        }
    }
}

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home Screen", style = MaterialTheme.typography.headlineLarge)
        Text("You have skipped the Onboarding!")
    }
}



@Serializable
sealed interface DataStorePracticeApp{
    @Serializable
    object OnboardingRoute
    @Serializable
    object HomeRoure
}

@Composable
fun AppNavigationProfessional(){

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    val isFirstTimeState by remember(context) {
        context.myDataStore.data.map {
            it[IS_FIRST_TIME_KEY] ?: true
        }
    }.collectAsState(initial =  null)

    if(isFirstTimeState == null){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
        return
    }

    NavHost(
        navController = navController,
        startDestination = if(isFirstTimeState == true ) DataStorePracticeApp.OnboardingRoute else DataStorePracticeApp.HomeRoure
    ){
        composable<DataStorePracticeApp.OnboardingRoute>{
            OnboardingScreen(
                onGetStartedClick = {
                    scope.launch {
                        context.myDataStore.edit {
                            it[IS_FIRST_TIME_KEY] = false
                        }
                    }
                    navController.navigate(DataStorePracticeApp.HomeRoure){
                        popUpTo<DataStorePracticeApp.OnboardingRoute>{inclusive = true}
                    }
                }
            )
        }
        composable<DataStorePracticeApp.HomeRoure>{
            HomeScreen()
        }
    }
}
*/

@Composable
fun OnboardingScreen(onGetStartedClick: (String) -> Unit) {
    val context = LocalContext.current
    var nameInput by remember { mutableStateOf("") } // নাম ইনপুটের স্টেট

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to MiravoLabs!", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = nameInput,
            onValueChange = { nameInput = it },
            label = { Text("Enter your name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (nameInput.isNotBlank()) {
                    onGetStartedClick(nameInput) // নামটা NavHost-এ পাঠিয়ে দিলাম
                } else {
                    Toast.makeText(context, "Please enter your name!", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Get Started")
        }
    }
}

@Composable
fun HomeScreen() {
    val context = LocalContext.current

    val savedName by remember(context) {
        context.myDataStore.data.map { preferences ->
            preferences[USER_NAME_KEY] ?: "Guest"
        }
    }.collectAsState(initial = "Loading...")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home Screen", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Welcome back, $savedName! 👋",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}



@Serializable
sealed interface DataStorePracticeApp{
    @Serializable
    object OnboardingRoute
    @Serializable
    object HomeRoure
}

@Composable
fun AppNavigationProfessional(){

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    val isFirstTimeState by remember(context) {
        context.myDataStore.data.map {
            it[IS_FIRST_TIME_KEY] ?: true
        }
    }.collectAsState(initial =  null)

    if(isFirstTimeState == null){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
        return
    }

    NavHost(
        navController = navController,
        startDestination = if(isFirstTimeState == true ) DataStorePracticeApp.OnboardingRoute else DataStorePracticeApp.HomeRoure
    ){
        composable<DataStorePracticeApp.OnboardingRoute>{
            OnboardingScreen(
                onGetStartedClick = {userName->
                    scope.launch {
                        context.myDataStore.edit {
                            it[IS_FIRST_TIME_KEY] = false
                            it[USER_NAME_KEY] = userName
                        }
                    }
                    navController.navigate(DataStorePracticeApp.HomeRoure){
                        popUpTo<DataStorePracticeApp.OnboardingRoute>{inclusive = true}
                    }
                }
            )
        }
        composable<DataStorePracticeApp.HomeRoure>{
            HomeScreen()
        }
    }
}


//