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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

