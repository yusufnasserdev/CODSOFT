package com.yusufnasserdev.todolist.di

import android.app.Application
import androidx.room.Room
import com.yusufnasserdev.todolist.features.task.data.repository.TaskRepositoryImpl
import com.yusufnasserdev.todolist.features.task.data.source.TaskDatabase
import com.yusufnasserdev.todolist.features.task.domain.repository.TaskRepository
import com.yusufnasserdev.todolist.features.task.domain.usecase.AddTask
import com.yusufnasserdev.todolist.features.task.domain.usecase.DeleteTask
import com.yusufnasserdev.todolist.features.task.domain.usecase.GetTasks
import com.yusufnasserdev.todolist.features.task.domain.usecase.TaskUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskDatabase(app: Application): TaskDatabase {
        return Room.databaseBuilder(
            app,
            TaskDatabase::class.java,
            TaskDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(db: TaskDatabase): TaskRepository {
        return TaskRepositoryImpl(db.taskDao)
    }

    @Provides
    @Singleton
    fun provideTaskUseCases(repo: TaskRepository): TaskUseCases {
        return TaskUseCases(
            getTasks = GetTasks(repo),
            deleteTask = DeleteTask(repo),
            addTask = AddTask(repo)
        )
    }

}