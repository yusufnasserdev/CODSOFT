package com.yusufnasserdev.todolist.features.task.domain.usecase

import com.yusufnasserdev.todolist.features.task.domain.model.Task
import com.yusufnasserdev.todolist.features.task.domain.repository.TaskRepository

class GetTask(
    private val repo: TaskRepository
) {
    suspend operator fun invoke(id: Int): Task? {
        return repo.getTaskById(id)
    }
}