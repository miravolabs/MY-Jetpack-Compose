package agecalabir.android.myjetpackcompose.room_learning

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

// Room Learning 1 ///// 24/11/2026

// Entity
@Entity(tableName = "tasks_table")
data class TaskItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val taskTitle : String
)

// DAO ///// Sqp quary

@Dao
interface TaskDao{

    // receive data using flow
    @Query("SELECT * FROM tasks_table ORDER BY id DESC")
    fun getAllTasks() : Flow<List<TaskItem>>

    // create task
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task : TaskItem)

    // Delete Spacifip task
    @Delete
    suspend fun deleteTask(task: TaskItem)
}


@Database(entities = [TaskItem::class] , version = 1 , exportSchema = false)
abstract class SimpleTaskDatabase : RoomDatabase(){
    abstract val dao : TaskDao
}

@Composable
fun SimpleRoomTaskScreen(){

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val db = remember {
        Room.databaseBuilder(
            context.applicationContext,
            SimpleTaskDatabase::class.java,
            "simple_tasks.db"
        ).build()
    }

    val taskList by db.dao.getAllTasks().collectAsState(initial = emptyList())

    var taskInputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .navigationBarsPadding()
            .statusBarsPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Room Tasks",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Pure SQLite without ViewModel layer",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                value = taskInputText,
                onValueChange = {taskInputText = it},
                label = {Text("Task")},
                placeholder = {Text("Enter a task name")},
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Button(
                onClick = {
                    if(taskInputText.isNotBlank()){
                        val newTask = TaskItem(taskTitle = taskInputText)
                        scope.launch {
                            db.dao.insertTask(newTask)
                        }
                        taskInputText = ""
                    }
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ){
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = taskList , key = {it.id}){
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = it.taskTitle,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.weight(1f)
                        )

                        IconButton(
                            onClick = {
                                scope.launch {
                                    db.dao.deleteTask(it)
                                }
                            },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.Red.copy(alpha = 0.2f)
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Delete,
                                contentDescription = "Delete Task",
                                tint = Color.Red
                            )
                        }
                    }
                }
            }
        }
    }
}