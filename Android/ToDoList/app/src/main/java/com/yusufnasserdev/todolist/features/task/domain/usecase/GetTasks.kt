package com.yusufnasserdev.todolist.features.task.domain.usecase

import com.yusufnasserdev.todolist.features.task.domain.model.Task
import com.yusufnasserdev.todolist.features.task.domain.repository.TaskRepository
import com.yusufnasserdev.todolist.features.task.domain.util.OrderType
import com.yusufnasserdev.todolist.features.task.domain.util.TaskOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTasks(
    private val repository: TaskRepository
) {
    operator fun invoke(
        taskOrder: TaskOrder = TaskOrder.Priority(OrderType.Descending)
    ): Flow<List<Task>> {
        return repository.getTasks().map { tasks ->
            when (taskOrder.orderType) {
                is OrderType.Ascending -> {
                    when (taskOrder) {
                        is TaskOrder.Title -> tasks.sortedBy { it.title.lowercase() }
                        is TaskOrder.Priority -> tasks.sortedBy { it.priority }
                        is TaskOrder.TimeDue -> tasks.sortedBy { it.dueTime }
                        is TaskOrder.TimeCreated -> tasks.sortedBy { it.creationTime }
                    }
                }
                is OrderType.Descending -> {
                    when (taskOrder) {
                        is TaskOrder.Title -> tasks.sortedByDescending { it.title.lowercase() }
                        is TaskOrder.Priority -> tasks.sortedByDescending { it.priority }
                        is TaskOrder.TimeDue -> tasks.sortedByDescending { it.dueTime }
                        is TaskOrder.TimeCreated -> tasks.sortedByDescending { it.creationTime }
                    }
                }
            }
        }
    }
}