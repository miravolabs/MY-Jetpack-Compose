package agecalabir.android.myjetpackcompose.todoapp.viewmodel

/*
@HiltViewModel
class TaskViewModel @Inject constructor(private var repository: TaskRepository) : ViewModel()
{


    val allTasks : StateFlow<List<TaskItem>> = repository.allTasks.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun addTask(taskName : String){
        viewModelScope.launch {
            repository.insert(TaskItem(taskName = taskName))
        }
    }

    fun toggleTaskStatus(task : TaskItem){
        viewModelScope.launch {
            repository.update(task.copy(isDone = !task.isDone))
        }
    }

    fun updateTask(task: TaskItem,newName: String){
        viewModelScope.launch {
            repository.update(task.copy(taskName =  newName))
        }
    }

    fun deleteTask(task: TaskItem){
        viewModelScope.launch {
            repository.delete(task)
        }
    }
}

 */