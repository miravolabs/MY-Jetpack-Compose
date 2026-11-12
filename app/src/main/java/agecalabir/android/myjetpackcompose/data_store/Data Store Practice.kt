package agecalabir.android.myjetpackcompose.data_store

import agecalabir.android.myjetpackcompose.ui.theme.Poppins
import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.rememberTooltipState
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
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
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
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
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

// Data Store Practice

val Application.settingDataStore by preferencesDataStore("user_setting_prefs")

val KEY_USERNAME = stringPreferencesKey("username")
val KEY_NOTIF_ENABLED = booleanPreferencesKey("notification")
val KEY_SYS_VOLUME = floatPreferencesKey("system_volume")

class SettingsViewModel(application: Application): AndroidViewModel(application){
    private val dataStore = application.settingDataStore

    val userNameFlow: Flow<String> = dataStore.data
        .catch { exception->
            if( exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map {
            it[KEY_USERNAME] ?: "User"
        }

    val notificationsFlow : Flow<Boolean> = dataStore.data
        .catch {  exception->
            if( exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map {
            it[KEY_NOTIF_ENABLED] ?: true
        }

    val volumeFlow: Flow<Float> = dataStore.data
        .catch {  exception->
            if( exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map {
            it[KEY_SYS_VOLUME] ?: 0.5f
        }

    fun updateUsername(newName : String){
        viewModelScope.launch {
            dataStore.edit {
                it[KEY_USERNAME] = newName
            }
        }
    }

    fun toggleNotification(isEnabled : Boolean){
        viewModelScope.launch {
            dataStore.edit {
                it[KEY_NOTIF_ENABLED] = isEnabled
            }
        }
    }

    fun updateVolume(newVolume : Float){
        viewModelScope.launch {
            dataStore.edit {
                it[KEY_SYS_VOLUME] = newVolume
            }
        }
    }

    fun factoryReset(){
        viewModelScope.launch {
            dataStore.edit {
                it.clear()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel = viewModel()){

    val currentName by viewModel.userNameFlow.collectAsState(initial = "Loading...")
    val isNotifEnabled by viewModel.notificationsFlow.collectAsState(initial = true)
    val currentVolume by viewModel.volumeFlow.collectAsState(initial = 0.5f)

    var nameInput by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("App Settings") },
                actions = {
                    TooltipBox(
                        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                        tooltip = { PlainTooltip { Text("More options") } },
                        state = rememberTooltipState()
                    ) {
                        IconButton(onClick = { },
                            colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onSurface)) {
                            Icon(Icons.Default.MoreVert, contentDescription = "More options")
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ){paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(16.dp)
        ){
            Text("Profile", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground, fontFamily = Poppins)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(Icons.Default.Person,"User", modifier = Modifier.size(40.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = currentName, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, fontFamily = Poppins)
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                OutlinedTextField(
                    value = nameInput,
                    onValueChange = {nameInput = it},
                    label = {Text("Change Name")},
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        if(nameInput.isNotBlank()){
                            viewModel.updateUsername(nameInput)
                            nameInput = ""
                        }
                    }
                ) {
                    Text("Update")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text("Preferences", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, fontFamily = Poppins)
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text("Enable Push Notifications", fontSize = 16.sp, fontFamily = Poppins)
                Switch(
                    checked = isNotifEnabled,
                    onCheckedChange = {
                        viewModel.toggleNotification(it)
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("System Volume: ${(currentVolume * 100).toInt()}%")
            Slider(
                value = currentVolume,
                onValueChange = {
                    viewModel.updateVolume(it)
                },
                valueRange = 0f..1f
            )
            Spacer(modifier = Modifier.weight(1f))

            OutlinedButton(
                onClick = { viewModel.factoryReset() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(Red.copy(alpha = 0.8f))
            ) {
                Text("Reset All Setting to Default")
            }
        }
    }
}


// Practice Data Store With ViewModel and Repository (10/11/2026) Morning

val Context.cleanDataStore by preferencesDataStore(name = "clean_arch_prefs")

class DataStoreRepository(private val dataStore: DataStore<Preferences>){

    private val KEY_FULL_NAME = stringPreferencesKey("full_name")
    private val KEY_SCORE  = intPreferencesKey("user_score")
    private val KEY_IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")

    val fullNameFlow: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map {
            it[KEY_FULL_NAME] ?: "User"
        }

    val scoreFlow : Flow<Int> = dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map {
            it[KEY_SCORE] ?: 0
        }

    val loginStateFlow: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map {
            it[KEY_IS_LOGGED_IN] ?: false
        }

    suspend fun savedFullName(name: String){
        dataStore.edit {
            it[KEY_FULL_NAME] = name
        }
    }

    suspend fun updateScore(score: Int){
        dataStore.edit {
            it[KEY_SCORE] = score
        }
    }

    suspend fun setLoginStatus(isLoggedIn: Boolean){
        dataStore.edit {
            it[KEY_IS_LOGGED_IN] = isLoggedIn
        }
    }
}

class CleanViewModel(application: Application) : AndroidViewModel(application){

    private val repository = DataStoreRepository(application.cleanDataStore)

    val nameFlow = repository.fullNameFlow
    val scoreFlow = repository.scoreFlow
    val loginFlow = repository.loginStateFlow

    fun updateName(newName: String){
        viewModelScope.launch {
            repository.savedFullName(newName)
        }
    }

    fun incrementScore(currentScore: Int){
        viewModelScope.launch {
            repository.updateScore(currentScore + 10)
        }
    }

    fun toggleLogin(status : Boolean){
        viewModelScope.launch {
            repository.setLoginStatus(status)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CleanArchitectureScreen(viewModel: CleanViewModel = viewModel()){

    val name by viewModel.nameFlow.collectAsState(initial = "Loading...")
    val score by viewModel.scoreFlow.collectAsState(initial = 0)
    val isLoggedIn by viewModel.loginFlow.collectAsState(initial = false)

    var inputName by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Clean Architecture DataStore") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            )
        }
    ){paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(16.dp)
        ){
            Card(modifier = Modifier.fillMaxWidth()){
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("User DashBoard", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, fontFamily = Poppins)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    Text("Name: $name")
                    Text("Score: $score")
                    Text("Status: ${if(isLoggedIn) "Logged IN" else "Logged OUT"}")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = inputName,
                onValueChange = {inputName = it},
                label = {Text("Update Name")},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    if(inputName.isNotBlank()){
                        viewModel.updateName(inputName)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("Save Name")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.incrementScore(score) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("Add +10 to Score")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text("Login Status")
                Switch(checked = isLoggedIn, onCheckedChange = {viewModel.toggleLogin(it)})
            }
        }
    }
}

// Practice Evening /brmi

val Context.displayPrefsStore by preferencesDataStore("display_settings")

class DisplayRepository(private val dataStore: DataStore<Preferences>){

    private val KEY_IS_GRID_VIEW = booleanPreferencesKey("is_grid_view")
    private val KEY_TEXT_SCALE = floatPreferencesKey("text_scale")
    private val KEY_CLCIK_COUNT = intPreferencesKey("click_count")

    val isGridViewFlow: Flow<Boolean> = dataStore.data
        .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
        .map { it[KEY_IS_GRID_VIEW] ?: false }

    val textScaleFlow: Flow<Float> = dataStore.data
        .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
        .map { it[KEY_TEXT_SCALE] ?: 1.0f }

    val clickCountFlow : Flow<Int> = dataStore.data
        .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
        .map { it[KEY_CLCIK_COUNT]?: 0 }

    suspend fun setGridView(isGrid: Boolean){
        dataStore.edit {
            it[KEY_IS_GRID_VIEW] = isGrid
        }
    }

    suspend fun setTextScale(scale: Float){
        dataStore.edit {
            it[KEY_TEXT_SCALE] = scale
        }
    }

    suspend fun incrementClick(){
        dataStore.edit {
            val currentCount = it[KEY_CLCIK_COUNT] ?: 0
            it[KEY_CLCIK_COUNT] = currentCount + 1
        }
    }

    suspend fun clearSetting(){
        dataStore.edit {
            it.clear()
        }
    }
}

class DisplayViewModel(application: Application): AndroidViewModel(application){

    private val repository = DisplayRepository(application.displayPrefsStore)

    val isGridView = repository.isGridViewFlow
    val textScale = repository.textScaleFlow
    val clickCount = repository.clickCountFlow

    fun toggleViewMode(isGrid: Boolean){
        viewModelScope.launch {
            repository.setGridView(isGrid)
        }
    }

    fun updateTextScale(scale: Float){
        viewModelScope.launch {
            repository.setTextScale(scale)
        }
    }

    fun addClick(){
        viewModelScope.launch {
            repository.incrementClick()
        }
    }

    fun resetAll(){
        viewModelScope.launch {
            repository.clearSetting()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplaySettingsScreen(viewModel: DisplayViewModel = viewModel()){

    val isGird by viewModel.isGridView.collectAsState(initial = false)
    val scale by viewModel.textScale.collectAsState(initial = 1.0f)
    val clicks by viewModel.clickCount.collectAsState(initial = 0)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Display Preferences") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
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
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Live Preview Text",
                        fontSize = (16 * scale).sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Current Layout: ${if (isGird) "Grid View \uD83D\uDD32" else "List View \uD83D\uDCDC"}")
                    Text(text = "Total Button Clicks: $clicks")
                }
            }
            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Enable Grid View", fontSize = 16.sp)
                Switch(
                    checked = isGird,
                    onCheckedChange = { viewModel.toggleViewMode(it) }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Text Scale: ${String.format("%.1f", scale)}x",
                modifier = Modifier.align(Alignment.Start)
            )
            Slider(
                value = scale,
                onValueChange = { viewModel.updateTextScale(it) },
                valueRange = 0.8f..2.0f,
                steps = 11
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.addClick() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Increment Click Count")
            }

            Spacer(modifier = Modifier.weight(1f))

            OutlinedButton(
                onClick = { viewModel.resetAll() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Reset Defaults")
            }
        }
    }
}

// Professional Level Theme Change
// Practice DataStore

val Context.themeStore by preferencesDataStore(name ="theme_settings")

class ThemeRepository(private val dataStore: DataStore<Preferences>){

    private val Key_THEME_MODE = intPreferencesKey("theme_mode")

    val themeModeFlow : Flow<Int> = dataStore.data
        .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
        .map { it[Key_THEME_MODE] ?: 0 }

    suspend fun setTheme(mode: Int){
        dataStore.edit { it[Key_THEME_MODE] = mode }
    }
}

/*
class ThemeViewModel(application: Application): AndroidViewModel(application){

    private val repository = ThemeRepository(application.themeStore)

    val themeMode = repository.themeModeFlow

    fun changeTheme(mode: Int){
        viewModelScope.launch {
            repository.setTheme(mode)
        }
    }
}
*/

class ThemeViewModel(application: Application): AndroidViewModel(application){

    private val repository = ThemeRepository(application.dataStore)

    private val _isLoading = MutableStateFlow(true)
    val isLoading : StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _themeMode = MutableStateFlow(0)
    val themeMode: StateFlow<Int> = _themeMode.asStateFlow()

    init {
        viewModelScope.launch {
            repository.themeModeFlow.collect {
                _themeMode.value = it
                _isLoading.value = false
            }
        }
    }

    fun changeTheme(mode: Int) {
        viewModelScope.launch { repository.setTheme(mode) }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeScreen(viewModel: ThemeViewModel = viewModel()){

    val currentTheme by viewModel.themeMode.collectAsState(initial = 0)
    val options = listOf("System", "Light", "Dark")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(
                    text = "Theme",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleLarge
                )},
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Navigate to back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ))
        }
    ){innerPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(12.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Choose Theme",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            options.forEachIndexed { index, string ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.changeTheme(index) }
                        .padding(vertical = 12.dp, horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    RadioButton(
                        selected = ( currentTheme == index ),
                        onClick = { viewModel.changeTheme(index) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.secondary,
                            unselectedColor = MaterialTheme.colorScheme.onSurface
                        )
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = string,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}
/**
 * class MainActivity : ComponentActivity() {
 *
 *     private val themeViewModel : ThemeViewModel by viewModels()
 *
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *
 *         val splashScreen = installSplashScreen()
 *
 *         super.onCreate(savedInstanceState)
 *
 *         splashScreen.setKeepOnScreenCondition {
 *             themeViewModel.isLoading.value
 *         }
 *
 *         setContent {
 *
 *             val themeMode by themeViewModel.themeMode.collectAsState()
 *
 *             val isDarkTheme = when(themeMode){
 *                 1-> false
 *                 2-> true
 *                 else -> isSystemInDarkTheme()
 *             }
 *
 *             SideEffect {
 *                 enableEdgeToEdge(
 *                     statusBarStyle = if (isDarkTheme) {
 *                         SystemBarStyle.dark(Color.TRANSPARENT)
 *                     } else {
 *                         SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
 *                     },
 *                     navigationBarStyle = if (isDarkTheme) {
 *                         SystemBarStyle.dark(Color.TRANSPARENT)
 *                     } else {
 *                         SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
 *                     }
 *                 )
 *             }
 *
 *             MyJetpackComposeTheme (darkTheme = isDarkTheme) {
 *                 ThemeScreen(viewModel = themeViewModel)
 *             }
 *         }
 *     }
 * }
 */
// Challenge 1 , data store practice

val Context.authSystem by preferencesDataStore("auth_system")

class LoginSystemRepository(private val dataStore: DataStore<Preferences>){

    private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    private val AUTH_TOKEN = stringPreferencesKey("auth_token")
    private val USER_ID = intPreferencesKey("user_id")

    val isLoggedInFlow : Flow<Boolean> = dataStore.data
        .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
        .map { it[IS_LOGGED_IN] ?: false }

    val authTokenFlow : Flow<String> = dataStore.data
        .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
        .map { it[AUTH_TOKEN] ?: "" }

    val userIdFlow : Flow<Int> = dataStore.data
        .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
        .map { it[USER_ID] ?: 0 }

    suspend fun logout(){
        dataStore.edit {
            it.remove(AUTH_TOKEN)
            it.remove(USER_ID)
            it[IS_LOGGED_IN] = false
        }
    }

    suspend fun login(userId: Int , authToken: String){
        dataStore.edit {
            it[AUTH_TOKEN] = authToken
            it[USER_ID] = userId
            it[IS_LOGGED_IN] = true
        }
    }
}

class LoginSystemViewModel(application: Application) : AndroidViewModel(application){

    private val repository = LoginSystemRepository(application.authSystem)

    val isLoggedIn = repository.isLoggedInFlow
    val authToken = repository.authTokenFlow
    val userId = repository.userIdFlow

    fun logout(){
        viewModelScope.launch {
            repository.logout()
        }
    }

    fun login(userId: Int, authToken: String){
        viewModelScope.launch {
            repository.login(userId = userId,authToken = authToken)
        }
    }
}

@Composable
fun FirstScreen(
    viewModel: LoginSystemViewModel,
    onNavigationToSecondScreen: () -> Unit
){

    val context = LocalContext.current

    var inputUserId by remember { mutableStateOf("") }
    var inputAuthToken by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login System",
            fontSize = 32.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = inputUserId,
            onValueChange = {inputUserId = it},
            label = {Text("User Id")},
            placeholder = {Text("Enter your user id")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = inputAuthToken,
            onValueChange = {inputAuthToken  = it},
            label = {Text("Auth Token")},
            placeholder = {Text("Enter your auth token")},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if(inputUserId.isNotBlank() && inputAuthToken.isNotBlank()){
                    viewModel.login(
                        userId = inputUserId.toIntOrNull() ?: 0,
                        authToken = inputAuthToken
                    )
                    Toast.makeText(context,"Login Successful",Toast.LENGTH_SHORT).show()
                    onNavigationToSecondScreen()
                }
                else {
                    Toast.makeText(context,"Please Enter all information",Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(Color.Blue.copy(alpha = 0.8f)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("LOGIN", fontWeight = FontWeight.Bold, fontFamily = Poppins, fontSize = 16.sp)
        }
    }
}

@Composable
fun SecondScreenLogin(
    viewModel: LoginSystemViewModel,
    onNavigationBackToFirstScreen: () -> Unit
    ){

    val authToken by viewModel.authToken.collectAsState("Loading...")
    val userid by viewModel.userId.collectAsState(initial = 0)

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
        elevation = CardDefaults.cardElevation(8.dp),
            shape = RoundedCornerShape(16.dp)
        ){
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                Text("Login Status Info", fontSize = 20.sp, fontFamily = Poppins, color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight.SemiBold)
                HorizontalDivider(thickness = 1.dp, color = DividerDefaults.color)
                Text("User Id: $userid")
                Text("Auth Token: $authToken")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.logout()
                Toast.makeText(context,"Logout Successful", Toast.LENGTH_SHORT).show()
                onNavigationBackToFirstScreen()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red.copy(alpha = 0.8f))
        ) {
            Text("LOGOUT")
        }
    }
}

@Serializable
sealed interface LoginSystemRoute{
    @Serializable
    data object FirstScreenRoute: LoginSystemRoute
    @Serializable
    data object SecondScreenRoute: LoginSystemRoute
}

@Composable
fun LoginSystemNavigation(viewModel: LoginSystemViewModel = viewModel()){
    val navController = rememberNavController()

    val isLoggedIn by viewModel.isLoggedIn.collectAsState(initial = null)
    if(isLoggedIn == null){

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
        startDestination = if(isLoggedIn == false) LoginSystemRoute.FirstScreenRoute else LoginSystemRoute.SecondScreenRoute
    ){
        composable<LoginSystemRoute.FirstScreenRoute> {
            FirstScreen(
                viewModel = viewModel,
                onNavigationToSecondScreen = {
                    navController.navigate(LoginSystemRoute.SecondScreenRoute){
                        popUpTo(LoginSystemRoute.FirstScreenRoute){inclusive = true}
                    }
                }
            )
        }
        composable<LoginSystemRoute.SecondScreenRoute>{
            SecondScreenLogin(
                viewModel = viewModel,
                onNavigationBackToFirstScreen = {
                    navController.navigate(LoginSystemRoute.FirstScreenRoute){
                        popUpTo(LoginSystemRoute.SecondScreenRoute){inclusive = true}
                    }
                }
            )
        }
    }
}

// Challenge 2 : Multi-Account Switcher

val Context.accountSwitch by preferencesDataStore("account_switch")

class AccountSwitchRepository(private val dataStore : DataStore<Preferences>){

    private val ACTIVE_NAME = stringPreferencesKey("active_name")
    private val ACTIVE_ROLE = stringPreferencesKey("active_role")

    val nameFlow : Flow<String> = dataStore.data
        .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
        .map { it[ACTIVE_NAME] ?: "Guest" }

    val roleFlow: Flow<String> = dataStore.data
        .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
        .map { it[ACTIVE_ROLE] ?: "Visitor" }

    suspend fun switchAccount(newName: String,newRole: String){
        dataStore.edit {
            it[ACTIVE_NAME] = newName
            it[ACTIVE_ROLE] = newRole
        }
    }
}

class AccountSwitchViewModel(application: Application) : AndroidViewModel(application){

    private val repository = AccountSwitchRepository(application.accountSwitch)

    val nameFlow = repository.nameFlow
    val roleFlow = repository.roleFlow

    fun switchProfile(name : String , role : String){
        viewModelScope.launch {
            repository.switchAccount(
                newName = name,
                newRole = role
            )
        }
    }
}

@Composable
fun AccountSwitch(viewModel: AccountSwitchViewModel = viewModel()){

    val name by viewModel.nameFlow.collectAsState(initial = "Loading...")
    val role by viewModel.roleFlow.collectAsState(initial = "Loading...")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = if(role == "Admin") { Color.Red} else if (role == "Developer") { Color.Blue} else {Color.Gray}
            )
        ){
            Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                Text("Account Status: Card", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface, fontFamily = Poppins)
                Divider(modifier = Modifier.padding(horizontal = 8.dp))
                Text("Name: $name, Role: $role", fontSize = 16.sp, fontFamily = Poppins, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.switchProfile(
                    name = "Abir",
                    role = "Admin"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan.copy(alpha = 0.8f))
        ) {
            Text("Switch to Abir (Admin)")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.switchProfile(
                    name = "Miravolabs",
                    role = "Developer"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue.copy(alpha = 0.8f))
        ) {
            Text("Switch to MiravoLabs (Developer)")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.switchProfile(
                    name = "Guest",
                    role = "Visitor"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray.copy(alpha = 0.8f))
        ) {
            Text("Switch to Guest (Visitor)")
        }
    }
}

// Challenge 4
// DataStore Practice Morning 12-11-2026

val Context.appOpenCounter by preferencesDataStore("app_open_count")

class AppOpenCounterRepository(private val dateStore: DataStore<Preferences>){

    private val OPEN_COUNT = intPreferencesKey("open_count")
    private val IS_RATED = booleanPreferencesKey("is_rated")

    val appOpenCountFlow : Flow<Int> = dateStore.data
        .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
        .map { it[OPEN_COUNT] ?: 0 }

    val appIsRatedFlow : Flow<Boolean> = dateStore.data
        .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
        .map { it[IS_RATED] ?: false }

    suspend fun openCount(){
        dateStore.edit {
            val currentCount = it[OPEN_COUNT] ?: 0
            it[OPEN_COUNT] = currentCount + 1
        }
    }

    suspend fun isRated(){
        dateStore.edit {
            it[IS_RATED] = true
        }
    }
}

class  AppOpenCountViewModel(application: Application): AndroidViewModel(application){

    private val repository = AppOpenCounterRepository(application.appOpenCounter)

    val countFlow = repository.appOpenCountFlow
    val isRatedFlow = repository.appIsRatedFlow

    init {
        incrementOpenCount()
    }

    fun incrementOpenCount(){
        viewModelScope.launch {
            repository.openCount()
        }
    }

    fun isRated(){
        viewModelScope.launch {
            repository.isRated()
        }
    }
}

@Composable
fun AppOpenCountAndRatedScreen(viewModel: AppOpenCountViewModel = viewModel()){

    val appOpenCount by viewModel.countFlow.collectAsState(initial = 0)
    val isRated by viewModel.isRatedFlow.collectAsState(initial = false)

    var isDismissed by remember { mutableStateOf(false) }
    var showDialog = appOpenCount >= 3 && !isRated && !isDismissed

    if(showDialog){
        androidx.compose.ui.window.Dialog(
            onDismissRequest = { isDismissed = true}
        ){
            Card(
                modifier = Modifier.fillMaxWidth()
            ){
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    Text("Rate App", fontSize = 24.sp, fontFamily = Poppins, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Rate this app naw")
                    Button(onClick ={
                        viewModel.isRated()
                        isDismissed = true
                    },
                        modifier = Modifier.fillMaxWidth()) {
                        Text("Rate App")
                    }
                    Button(
                        onClick = {isDismissed = true}
                    ) {
                        Text("Rate Later")
                    }
                }
            }
        }
    }

        Column(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ){
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    Text("App Info Dialog: ", fontFamily = Poppins, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("App Open: $appOpenCount Times")
                    Text("App Is Rated? : ${if(isRated) "Yes" else "No"}")
                }
            }
        }

}