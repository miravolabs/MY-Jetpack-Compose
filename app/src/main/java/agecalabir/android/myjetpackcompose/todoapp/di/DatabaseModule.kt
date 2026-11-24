package agecalabir.android.myjetpackcompose.todoapp.di

/*
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

 */