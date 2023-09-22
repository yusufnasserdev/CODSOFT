package com.yusufnasserdev.todolist.features.task.presentation.task.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yusufnasserdev.todolist.features.task.presentation.task.TasksEvent
import com.yusufnasserdev.todolist.features.task.presentation.task.TasksViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TasksScreen(
    navController: NavController, viewModel: TasksViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    /*TODO*/
                }, backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Task")
            }
        }, scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Tasks", style = MaterialTheme.typography.h4
                )
                IconButton(onClick = { viewModel.onEvent(TasksEvent.ToggleOrderOptions) }) {
                    Icon(
                        imageVector = Icons.Default.Sort, contentDescription = "Sort Tasks"
                    )
                }

            }
            AnimatedVisibility(
                visible = state.isOrderOptionsVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderOptions(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    taskOrder = state.taskOrder,
                    onOrderChange = {
                        viewModel.onEvent(TasksEvent.Order(it))
                    }
                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.tasks) {
                    TaskItem(
                        task = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            },
                        onDelete = {
                            viewModel.onEvent(TasksEvent.DeleteTask(it))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Task Removed",
                                    actionLabel = "Undo"
                                )

                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(TasksEvent.RestoreTask)
                                }
                            }
                        },
                        onEdit = {}
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

        }

    }

}