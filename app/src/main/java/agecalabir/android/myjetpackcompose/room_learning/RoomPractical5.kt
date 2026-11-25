package agecalabir.android.myjetpackcompose.room_learning

import agecalabir.android.myjetpackcompose.ui.theme.Poppins
import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// Room Practice 5 // 25/11/2026


//=======================================
//Database layer (Entity , Dao , Database)
//========================================

@Entity(tableName = "dev_tools_table")
data class DevToolItem(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String,
    val category: String,
    val isFavorite : Boolean = false
)

@Dao
interface DevToolDao {
    @Query("SELECT * FROM dev_tools_table ORDER  BY isFavorite DESC , id DESC")
    fun getAllTools() : Flow<List<DevToolItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTool(tool : DevToolItem)

    @Update
    suspend fun updateTool(tool : DevToolItem)

    @Delete
    suspend fun deleteTool(tool : DevToolItem)
}

@Database(entities = [DevToolItem::class] , version = 1, exportSchema = false)
abstract class DevToolDatabase : RoomDatabase() {
    abstract val dao : DevToolDao

    companion object{
        @Volatile
        private var INSTANCE : DevToolDatabase? = null

        fun getDatabase(context : Context) : DevToolDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    DevToolDatabase::class.java,
                    "dev_tool_vault.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}

//===================================
// ViewModel Layer
//===================================

class DevToolViewModel(application: Application) : AndroidViewModel(application){

    private val dao = DevToolDatabase.getDatabase(application).dao

    val toolsList: StateFlow<List<DevToolItem>> = dao.getAllTools()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insertTool(name : String, category: String){
        viewModelScope.launch {
            val newTool = DevToolItem(
                name = name,
                category = category,
                isFavorite = false
            )
            dao.insertTool(newTool)
        }
    }

    fun toggleFavorite(item : DevToolItem){
        viewModelScope.launch {
            val updated = item.copy(isFavorite = !item.isFavorite)
            dao.updateTool(updated)
        }
    }

    fun deleteTool(item: DevToolItem){
        viewModelScope.launch {
            dao.deleteTool(item)
        }
    }
}


//=======================================
// UI Layer
//=======================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevVaultApp(
    viewModel: DevToolViewModel = viewModel()
){
    val context = LocalContext.current
    val toolsList by viewModel.toolsList.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var inputName by remember { mutableStateOf("") }
    var inputCategory by remember { mutableStateOf("") }

    if(showDialog){
        Dialog(
            onDismissRequest = {showDialog = false}
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = null,
                        modifier = Modifier.size(42.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Add Dev Library / Tool",
                        fontSize = 18.sp,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedTextField(
                        value = inputName,
                        onValueChange = {inputName = it},
                        label = { Text("Tool / Library Name") },
                        placeholder = { Text("Enter tool name") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = inputCategory,
                        onValueChange = {inputCategory = it},
                        label = { Text("Category") },
                        placeholder = { Text("Enter category name") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            if(inputCategory.isNotBlank() && inputName.isNotBlank()){
                                viewModel.insertTool(inputName,inputCategory)
                                inputCategory = ""
                                inputName = ""
                                showDialog = false
                            } else {
                                Toast.makeText(context,"Fill all fields",Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB))
                    ){
                        Text("Save to Vault", fontWeight = FontWeight.Bold, fontFamily = Poppins)
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            Surface(
                shadowElevation = 2.dp,
                color = MaterialTheme.colorScheme.background
            ) {
                TopAppBar(
                    title = { Text("Dev Tool Vault 🛠️", fontWeight = FontWeight.Bold) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        titleContentColor = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {showDialog = true },
                containerColor = Color(0xFF2563EB),
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Tool"
                )
            }
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            if(toolsList.isNotEmpty()){
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(vertical = 6.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    items(items = toolsList, key = { it.id }){
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        ){
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = it.name,
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Badge(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                                    ) {
                                        Text(
                                            text = it.category,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                            fontSize = 11.sp
                                        )
                                    }
                                }
                               Row(verticalAlignment = Alignment.CenterVertically) {
                                   IconButton(onClick = {viewModel.toggleFavorite(it)}) {
                                       Icon(
                                           imageVector = if(it.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                           contentDescription = "Favorite",
                                           tint = if(it.isFavorite) Color(0xFFE11D48) else MaterialTheme.colorScheme.onSurfaceVariant
                                       )
                                   }
                                   IconButton(onClick = {viewModel.deleteTool(it)}) {
                                       Icon(
                                           imageVector = Icons.Default.DeleteForever,
                                           contentDescription = "Delete",
                                           tint = MaterialTheme.colorScheme.error
                                       )
                                   }
                               }
                            }
                        }
                    }
                }
            } else {
                EmptyNotesView()
            }
        }
    }
}

@Composable
fun EmptyNotesView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = null,
            modifier = Modifier.size(70.dp),
            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "No tools yet",
            fontFamily = Poppins,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Tap + to add your first tools",
            fontFamily = Poppins,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}