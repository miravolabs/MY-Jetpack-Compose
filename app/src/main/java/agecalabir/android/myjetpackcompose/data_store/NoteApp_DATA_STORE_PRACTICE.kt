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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.edit
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