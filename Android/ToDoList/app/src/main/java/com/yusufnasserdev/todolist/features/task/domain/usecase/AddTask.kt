package com.yusufnasserdev.todolist.features.task.domain.usecase

import com.yusufnasserdev.todolist.features.task.domain.model.InvalidTaskException
import com.yusufnasserdev.todolist.features.task.domain.model.Task
import com.yusufnasserdev.todolist.features.task.domain.repository.TaskRepository

class AddTask(
    private val repository: TaskRepository
) {
    @Throws(InvalidTaskException::class)
    suspend operator fun invoke(task: Task) {
        if (task.title.isBlank()) {
            throw InvalidTaskException("The title of the task can't be empty.")
        }

        if (task.description.isBlank()) {
            throw InvalidTaskException("The description of the task can't be empty.")
        }

        repository.insertTask(task)
    }
}