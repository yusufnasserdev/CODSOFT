package com.yusufnasserdev.todolist.features.task.presentation.task.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yusufnasserdev.todolist.features.task.domain.util.OrderType
import com.yusufnasserdev.todolist.features.task.domain.util.TaskOrder

@Composable
fun OrderOptions(
    modifier: Modifier = Modifier,
    taskOrder: TaskOrder = TaskOrder.Priority(OrderType.Descending),
    onOrderChange: (TaskOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {

            DefaultRadioButton(
                text = "Priority",
                selected = taskOrder is TaskOrder.Priority,
                onSelect = { onOrderChange(TaskOrder.Priority(taskOrder.orderType)) }
            )

            Spacer(modifier = modifier.width(8.dp))

            DefaultRadioButton(
                text = "Time Due",
                selected = taskOrder is TaskOrder.TimeDue,
                onSelect = { onOrderChange(TaskOrder.TimeDue(taskOrder.orderType)) }
            )

            Spacer(modifier = modifier.width(8.dp))

            DefaultRadioButton(
                text = "Time Created",
                selected = taskOrder is TaskOrder.TimeCreated,
                onSelect = { onOrderChange(TaskOrder.TimeCreated(taskOrder.orderType)) }
            )
        }

        Spacer(modifier = modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Ascending",
                selected = taskOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(
                        taskOrder.copy(OrderType.Ascending)
                    )
                }
            )

            Spacer(modifier = modifier.width(8.dp))

            DefaultRadioButton(
                text = "Descending",
                selected = taskOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(
                        taskOrder.copy(OrderType.Descending)
                    )
                }
            )
        }

    }
}

@Preview
@Composable
fun OrderOptionsPrev() {
    OrderOptions(
        modifier = Modifier,
        taskOrder = TaskOrder.Priority(OrderType.Descending),
        onOrderChange = {}
    )
}