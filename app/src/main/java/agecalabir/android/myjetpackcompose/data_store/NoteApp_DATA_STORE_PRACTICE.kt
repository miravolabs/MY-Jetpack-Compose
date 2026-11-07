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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


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
                    HorizontalDivider(color = Color.Gray, thickness = 2.dp)
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