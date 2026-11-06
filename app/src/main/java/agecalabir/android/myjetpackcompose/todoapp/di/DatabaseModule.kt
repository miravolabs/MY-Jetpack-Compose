package agecalabir.android.myjetpackcompose.todoapp.di

import agecalabir.android.myjetpackcompose.todoapp.data.TaskDao
import agecalabir.android.myjetpackcompose.todoapp.data.TaskDatabase
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideTaskDatabase(
        @ApplicationContext context: Context
    ) : TaskDatabase{
        return Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            "task_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun providedTaskDao(database : TaskDatabase) : TaskDao{
        return database.taskDao()
    }
}