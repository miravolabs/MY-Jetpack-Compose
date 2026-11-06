package agecalabir.android.myjetpackcompose.todoapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task : TaskItem)

    @Update
    suspend fun updateTask(task : TaskItem)

    @Delete
    suspend fun deleteTask(task: TaskItem)

    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAllTasks(): Flow<List<TaskItem>>
}