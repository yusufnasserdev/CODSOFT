package com.yusufnasserdev.todolist.features.task.domain.repository

import com.yusufnasserdev.todolist.features.task.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasks(): Flow<List<Task>>

    suspend fun getTaskById(id: Int): Task?

    suspend fun insertTask(task: Task)

    suspend fun deleteTask(task: Task)
}