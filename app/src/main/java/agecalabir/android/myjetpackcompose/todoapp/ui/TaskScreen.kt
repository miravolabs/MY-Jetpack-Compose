package agecalabir.android.myjetpackcompose.todoapp.ui

import agecalabir.android.myjetpackcompose.todoapp.data.TaskItem
import agecalabir.android.myjetpackcompose.todoapp.viewmodel.TaskViewModel
import agecalabir.android.myjetpackcompose.ui.theme.Poppins
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    viewModel: TaskViewModel = hiltViewModel()
){
    val tasks by viewModel.allTasks.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var taskToEdit by remember { mutableStateOf<TaskItem?>(null) }
    var inputTaskName by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    taskToEdit = null
                    inputTaskName = ""
                    showDialog = true
                },
                containerColor = Color(0xFF212529),
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon( Icons.Default.Add,  null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("New Task", fontFamily = Poppins)
            }
        }
    ) { innerPadding->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = 16.dp, horizontal = 20.dp)
        ) {
            Text(
                text = "My Tasks",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = Color(0xFF212529)
            )
            val remainingTasks = tasks.count{!it.isDone}
            Text(
                text = "$remainingTasks remaining today",
                fontSize = 14.sp,
                color = Color.Gray,
                fontFamily = Poppins
            )
            Spacer(modifier = Modifier.height(16.dp))

            if(tasks.isEmpty()){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "No tasks yet. Tap + to add one!",
                        fontFamily = Poppins,
                        color = Color.Gray
                    )
                }
            } else{
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        tasks, key = {it.id}
                    ){task ->
                        ToDoItem(
                            item = task,
                            onCheckChange = {viewModel.toggleTaskStatus(task)},
                            onEditCheck = {
                                taskToEdit = task
                                inputTaskName = task.taskName
                                showDialog = true
                            },
                            onDeleteCheck = {viewModel.deleteTask(task)}
                        )
                    }
                }
            }
        }
    }
    if(showDialog){
        AlertDialog(
            onDismissRequest = {showDialog = false},
            title = {
                Text(
                    text = if(taskToEdit == null) "Create New task" else "Updata task",
                    fontWeight = FontWeight.Bold,
                    fontFamily = Poppins
                )
            },
            containerColor = Color.White,
            text = {
                OutlinedTextField(
                    value = inputTaskName,
                    onValueChange = {inputTaskName = it},
                    label = {Text("Task Description", fontFamily = Poppins)},
                    placeholder = {Text("What needs to be done?", fontFamily = Poppins)},
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if(inputTaskName.isNotBlank()){
                            if(taskToEdit == null){
                                viewModel.addTask(inputTaskName)
                            }
                        }else{
                            viewModel.updateTask(taskToEdit!!,inputTaskName)
                        }
                        showDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF212529))
                ) {
                    Text(
                        text = if ( taskToEdit == null ) "Save Task" else "Update",
                        fontFamily = Poppins
                    )
                }
            },
            dismissButton = {
                Button(onClick = {showDialog = false}) {
                    Text(
                        text = "Cancel",
                        fontFamily = Poppins,
                        color = Color.Gray
                    )
                }
            }
        )
    }
}