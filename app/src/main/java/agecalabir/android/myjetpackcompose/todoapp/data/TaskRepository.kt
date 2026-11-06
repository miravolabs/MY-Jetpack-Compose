package agecalabir.android.myjetpackcompose.todoapp.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val taskDao : TaskDao){
    val allTasks : Flow<List<TaskItem>> = taskDao.getAllTasks()

    suspend fun insert(task: TaskItem){
        taskDao.insertTask(task)
    }

    suspend fun update(task: TaskItem){
        taskDao.updateTask(task)
    }

    suspend fun delete(task: TaskItem){
        taskDao.deleteTask(task)
    }
}