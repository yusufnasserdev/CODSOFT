package com.yusufnasserdev.todolist.features.task.presentation.task

import com.yusufnasserdev.todolist.features.task.domain.model.Task
import com.yusufnasserdev.todolist.features.task.domain.util.TaskOrder

sealed class TasksEvent {
    data class Order(val tasksOrder: TaskOrder) : TasksEvent()
    data class DeleteTask(val task: Task) : TasksEvent()
    data object RestoreTask : TasksEvent()
    data object ToggleOrderOptions : TasksEvent()
}
