package agecalabir.android.myjetpackcompose.data_store

import agecalabir.android.myjetpackcompose.ui.theme.Poppins
import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.themeDataSTore by preferencesDataStore("theme_prefs")
val THEME_MODE_KEY = intPreferencesKey("theme_mode")

@Composable
fun ThemePracticeApp(){
    val context = LocalContext.current

    val themeMode by remember(context) {
        context.themeDataSTore.data.map {
            it[THEME_MODE_KEY] ?: 0
        }
    }.collectAsState(initial = 0)

    val isDarkTheme = when(themeMode){
        1-> false
        2 -> true
        else -> isSystemInDarkTheme()
    }

    MaterialTheme(
        colorScheme = if(isDarkTheme) darkColorScheme() else lightColorScheme()
    ){
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ThemeSettingScreen(currentTheme = themeMode)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSettingScreen(currentTheme: Int){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val options = listOf("Auto","Light","Dark")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Theme Manager",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(32.dp))

        SingleChoiceSegmentedButtonRow {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    selected = currentTheme == index,
                    onClick = {
                        scope.launch {
                            context.themeDataSTore.edit {
                                it[THEME_MODE_KEY] = index
                            }
                        }
                    },
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size)
                ) {
                    Text(label)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Current Mode: ${options[currentTheme]}",
            color = MaterialTheme.colorScheme.primary
        )
    }
}




// Data Store Practice 08-11-2026 Evening Time --- 09-11-2026 Morning Time .

val Context.profileSaverDataStore by preferencesDataStore("profile_saver_datastore")
val PROFILE_NAME_KEY = stringPreferencesKey("user_name")
val PROFILE_EMAIL_KEY = stringPreferencesKey("user_email")
val PROFILE_NUMBER_KEY = intPreferencesKey("user_number")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSaver(){

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val name by remember(context) {
        context.profileSaverDataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map {
                it[PROFILE_NAME_KEY] ?: "Empty"
            }
    }.collectAsState(initial = "Loading...")

    val email by remember(context) {
        context.profileSaverDataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map {
                it[PROFILE_EMAIL_KEY]?: "Empty"
            }
    }.collectAsState(initial = "Loading...")

    val number by remember(context) {
        context.profileSaverDataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map {
                it[PROFILE_NUMBER_KEY] ?: 0
            }
    }.collectAsState(initial = 0)

    var inputName by remember { mutableStateOf("") }
    var inputEmail by remember { mutableStateOf("") }
    var inputNumber by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
            title = {Text("Data Store", color = MaterialTheme.colorScheme.onPrimaryContainer, fontFamily = Poppins)},
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ))
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Data Store Practice",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = Poppins
            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = inputName,
                onValueChange = {inputName = it},
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = inputEmail,
                onValueChange = {inputEmail = it},
                label = {Text("Email")},
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            OutlinedTextField(
                value = inputNumber,
                onValueChange = {inputNumber = it},
                label = {Text("Number")},
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Button(
                    onClick = {
                        if(inputName.isNotBlank() && inputEmail.isNotBlank() && inputNumber.isNotBlank()){
                            scope.launch {
                                context.profileSaverDataStore.edit {
                                    it[PROFILE_NAME_KEY] = inputName
                                    it[PROFILE_EMAIL_KEY] = inputEmail
                                    it[PROFILE_NUMBER_KEY] = inputNumber.toIntOrNull() ?: 0
                                }
                            }
                            Toast.makeText(context,"Saved Data Successfully!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context,"Please give every info first!", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text("Save All", fontSize = 12.sp, fontFamily = Poppins)
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        scope.launch {
                            context.profileSaverDataStore.edit {
                                it.remove(PROFILE_NUMBER_KEY)
                            }
                        }
                        Toast.makeText(context,"Phone number removed successfully!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red.copy(alpha = 0.8f),
                        contentColor = MaterialTheme.colorScheme.onBackground
                    )
                ){
                    Text("Delete Number", fontSize = 12.sp, fontFamily = Poppins)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainer),
                elevation = CardDefaults.cardElevation(8.dp)
            ){
                Column(
                    modifier = Modifier.fillMaxWidth().padding(14.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("Data Store Practice Status: ", fontSize = 16.sp, fontFamily = Poppins, fontWeight = FontWeight.SemiBold)
                    HorizontalDivider(
                        thickness = DividerDefaults.Thickness,
                        color = DividerDefaults.color
                    )
                    Text("Saved User Name: $name", fontSize = 14.sp,maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Text("Saved Email Address: $email", fontSize = 14.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Text("Saved Number: $number", fontSize = 14.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}

// Data Store Advance With viewmodel Practice

val Application.vmDataSTore by preferencesDataStore("vm_prefs")
val VM_NAME_KEY = stringPreferencesKey("vm_user_name")
class SimpleViewModel(application: Application) : AndroidViewModel(application){

    private var dataStore = application.vmDataSTore

    val userNameFlow: Flow<String> = dataStore.data.map {
        it[VM_NAME_KEY] ?: "No Name Yet"
    }

    fun savedName(newName: String){
        viewModelScope.launch {
            dataStore.edit {
                it[VM_NAME_KEY] = newName
            }
        }
    }
}

// Screen

@Composable
fun VmPracticeScreen(viewModel: SimpleViewModel = viewModel()){
    val savedName by viewModel.userNameFlow.collectAsState(initial = "Loading...")

    var inputText by remember {mutableStateOf("")}

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Text(text = "Saved Name: $savedName", fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Enter Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if(inputText.isNotBlank()){
                    viewModel.savedName(inputText)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Save via ViewModel")
        }
    }
}


// Practice Data Store with viewmodel

val Application.masterVmDataStore by preferencesDataStore("master_vm_datastore")

val VM_KEY_NAME = stringPreferencesKey("vm_user_name")
val VM_KEY_AGE = intPreferencesKey("vm_user_age")
val VM_KEY_IS_PR0 = booleanPreferencesKey("vm_is_pro_user")
val VM_KEY_RATING = floatPreferencesKey("vm_app_rating")

class MasterDataStoreViewModel(application: Application): AndroidViewModel(application){
    private val dataStore = application.masterVmDataStore

    val userNameFlow : Flow<String> = dataStore.data.map { it[VM_KEY_NAME] ?: "Empty" }
    val userAgeFlow : Flow<Int> = dataStore.data.map { it[VM_KEY_AGE] ?: 0 }
    val isProUserFlow : Flow<Boolean> = dataStore.data.map { it[VM_KEY_IS_PR0] ?: false }
    val appRatingFlow : Flow<Float> = dataStore.data.map { it[VM_KEY_RATING] ?: 0.0f }

    fun saveAllData(name : String, age : Int, isPro: Boolean, rating: Float){
        viewModelScope.launch {
            dataStore.edit {
                it[VM_KEY_NAME] = name
                it[VM_KEY_AGE] = age
                it[VM_KEY_IS_PR0] = isPro
                it[VM_KEY_RATING] = rating
            }
        }
    }

    fun updateRatingOnly(newRating: Float){
        viewModelScope.launch {
            dataStore.edit {
                it[VM_KEY_RATING] = newRating
            }
        }
    }

    fun clearAllData(){
        viewModelScope.launch {
            dataStore.edit {
                it.clear()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterViewModelScreen(viewModel: MasterDataStoreViewModel = viewModel()){

    val context = LocalContext.current

    val savedName by viewModel.userNameFlow.collectAsState(initial = "Loading...")
    val savedAge by viewModel.userAgeFlow.collectAsState(initial = 0)
    val isProUser by viewModel.isProUserFlow.collectAsState(initial = false)
    val savedRating by viewModel.appRatingFlow.collectAsState(initial = 0.0f)

    var inputName by remember { mutableStateOf("") }
    var inputAge by remember { mutableStateOf("") }
    var inputIsPro by remember { mutableStateOf(false) }
    var inputRating by remember { mutableFloatStateOf(0f) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("ViewModel DataStore Master")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
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
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ){
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Live Status from ViewModel", fontWeight = FontWeight.Bold, fontSize = 18.sp, fontFamily = Poppins,)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    Text("Name: $savedName")
                    Text("Age: $savedAge")
                    Text("Pro Member: ${if(isProUser) "Yes" else "No"}")
                    Text("Rating: $savedRating ⭐")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = inputName,
                onValueChange = {inputName = it},
                label = {Text("Name")},
                placeholder = {Text("Enter your name")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = inputAge,
                onValueChange = {inputAge = it},
                label = {Text("Age")},
                placeholder = {Text("Enter your age")},
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text("Pro Subscription")
                Switch(
                    checked = inputIsPro,
                    onCheckedChange = {inputIsPro = it}
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Rating: $inputRating", modifier = Modifier.align(Alignment.Start))
            Slider(
                value = inputRating,
                onValueChange = {inputRating = it},
                valueRange = 0f..5f,
                steps = 4
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {

                    val ageInt = inputAge.toIntOrNull() ?: 0

                    if(inputName.isNotBlank() && inputAge.isNotBlank()){
                        viewModel.saveAllData(name = inputName, age = ageInt, isPro = inputIsPro, rating = inputRating)
                        Toast.makeText(context,"Saved via ViewModel", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context,"Please fill all the info first!", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save All Data")
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = {
                    viewModel.updateRatingOnly(inputRating)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Update Rating Only")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    viewModel.clearAllData()
                    Toast.makeText(context,"DataStore Cleared!",Toast.LENGTH_SHORT).show()
                },
                modifier =  Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red.copy(alpha = 0.8f)
                )
            ) {

            Text("Clear DataStore")}
        }
    }
}