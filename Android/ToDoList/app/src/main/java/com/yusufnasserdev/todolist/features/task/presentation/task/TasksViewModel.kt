package com.yusufnasserdev.todolist.features.task.presentation.task

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufnasserdev.todolist.features.task.domain.model.Task
import com.yusufnasserdev.todolist.features.task.domain.usecase.TaskUseCases
import com.yusufnasserdev.todolist.features.task.domain.util.OrderType
import com.yusufnasserdev.todolist.features.task.domain.util.TaskOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel

class TasksViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
) : ViewModel() {

    private val _state = mutableStateOf(TasksState())
    val state: State<TasksState> = _state

    private var deletedTask: Task? = null

    private var getTasksJob: Job? = null

    init {
        getTasks(TaskOrder.Priority(OrderType.Descending))
    }

    fun onEvent(event: TasksEvent) {
        when (event) {
            is TasksEvent.Order -> {
                if (state.value.taskOrder::class == event.tasksOrder::class &&
                    state.value.taskOrder.orderType == event.tasksOrder.orderType) {
                    return
                }
                
                getTasks(event.tasksOrder)
            }

            is TasksEvent.ToggleOrderOptions -> {
                _state.value = state.value.copy(
                    isOrderOptionsVisible = !state.value.isOrderOptionsVisible
                )
            }

            is TasksEvent.DeleteTask -> {
                viewModelScope.launch {
                    taskUseCases.deleteTask(event.task)
                    deletedTask = event.task
                }
            }

            is TasksEvent.RestoreTask -> {
                viewModelScope.launch {
                    taskUseCases.addTask(deletedTask ?: return@launch)
                    deletedTask = null
                }
            }
        }
    }

    private fun getTasks(taskOrder: TaskOrder) {
        getTasksJob?.cancel()
        getTasksJob = taskUseCases.getTasks(taskOrder).onEach { tasks ->
            _state.value = state.value.copy(
                tasks = tasks, taskOrder = taskOrder
            )
        }.launchIn(viewModelScope)
    }

}