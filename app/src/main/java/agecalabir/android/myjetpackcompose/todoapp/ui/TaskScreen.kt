package agecalabir.android.myjetpackcompose.todoapp.ui

/*

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

 */