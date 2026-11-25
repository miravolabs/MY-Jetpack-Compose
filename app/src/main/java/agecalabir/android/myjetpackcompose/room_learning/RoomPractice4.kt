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
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
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

// Room practice 4 // 24/11/2026

// Entity
@Entity(tableName = "task_table")
data class TaskItem(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val task : String,
    val isDone : Boolean
)

// DAO

@Dao
interface TaskDao{

    @Query("SELECT * FROM task_table ORDER BY id DESC")
    fun getAllTasks() : Flow<List<TaskItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task : TaskItem)

    @Update
    suspend fun updateTask(task: TaskItem)

    @Delete
    suspend fun deleteTask(task: TaskItem)

}

// Database
@Database(entities = [TaskItem::class] , version = 1 , exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract val dao : TaskDao

    companion object {
        @Volatile
        private var INSTANCE : TaskDatabase? = null

        fun getDatabase(context : Context) : TaskDatabase {
            return INSTANCE?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}

// ViewModel

class TaskViewModel(application: Application) : AndroidViewModel(application){

    private val dao = TaskDatabase.getDatabase(application).dao

    val taskList : StateFlow<List<TaskItem>> = dao.getAllTasks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insertTask(title : String){
        viewModelScope.launch {
            dao.insertTask(TaskItem(task = title , isDone = false))
        }
    }

    fun updateTask(task: TaskItem){
        viewModelScope.launch {
            dao.updateTask(task)
        }
    }

    fun deleteTask(task : TaskItem){
        viewModelScope.launch {
            dao.deleteTask(task)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskApp(
    viewModel: TaskViewModel = viewModel()
){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var inputTaskName by remember { mutableStateOf("") }

    var showNewTaskDialog by remember { mutableStateOf(false) }

    val tasksList by viewModel.taskList.collectAsState()

    if(showNewTaskDialog){
        Dialog(
            onDismissRequest = {showNewTaskDialog = false}
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = null,
                        modifier = Modifier.size(50.dp),
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "New Task",
                        fontSize = 16.sp,
                        fontFamily = Poppins,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    OutlinedTextField(
                        value = inputTaskName,
                        onValueChange = {inputTaskName = it},
                        placeholder = {Text("Enter your new task name")},
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {
                            if(inputTaskName.isNotBlank()){

                               viewModel.insertTask(inputTaskName)

                                showNewTaskDialog = false
                            } else Toast.makeText(context,"Please Enter your task",Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Add New Task")
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Tasks")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showNewTaskDialog = true },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(16.dp)
            ){
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Task"
                )
            }
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(horizontal = 12.dp)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(items = tasksList, key = {it.id}){
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ){
                        Column(modifier = Modifier.padding(16.dp)){
                            Row(modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Text(
                                    text = it.task,
                                    fontSize = 16.sp,
                                    textDecoration = if(it.isDone) TextDecoration.LineThrough else TextDecoration.None,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                IconButton(
                                    onClick = {
                                       viewModel.deleteTask(it)
                                    },
                                    colors = IconButtonDefaults.iconButtonColors(
                                        contentColor = Color.Red,
                                        containerColor = Color.Red.copy(alpha = 0.2f)
                                    )
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.DeleteForever,
                                        contentDescription = "Delete Task"
                                    )
                                }
                            }
                            Row(modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Text(
                                    text = if(it.isDone) "Done" else "Remaining",
                                    fontSize = 14.sp,
                                    color = if(it.isDone) Color.Green.copy(alpha = 0.8f) else Color.Red.copy(alpha = 0.8f)
                                )
                                Checkbox(
                                    checked = it.isDone,
                                    onCheckedChange = {isChecked ->
                                        viewModel.updateTask(it.copy(isDone = isChecked))
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}