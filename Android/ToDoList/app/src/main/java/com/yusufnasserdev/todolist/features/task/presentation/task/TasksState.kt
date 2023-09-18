package com.yusufnasserdev.todolist.features.task.presentation.task

import com.yusufnasserdev.todolist.features.task.domain.model.Task
import com.yusufnasserdev.todolist.features.task.domain.util.OrderType
import com.yusufnasserdev.todolist.features.task.domain.util.TaskOrder

data class TasksState(
    val tasks: List<Task> = emptyList(),
    val taskOrder: TaskOrder = TaskOrder.Priority(OrderType.Descending),
    val isOrderOptionsVisible: Boolean = false
)
