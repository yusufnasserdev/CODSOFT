package com.yusufnasserdev.todolist.features.task.presentation.task.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yusufnasserdev.todolist.features.task.domain.model.Priority
import com.yusufnasserdev.todolist.features.task.domain.model.Task
import java.util.Date


@Composable
fun TaskItem(
    task: Task,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 16.dp)
                .height(76.dp),
            shape = RoundedCornerShape(cornerRadius),
            backgroundColor = MaterialTheme.colors.primaryVariant,
            contentColor = MaterialTheme.colors.onPrimary,
            elevation = 10.dp,
            border = BorderStroke(2.dp, MaterialTheme.colors.error)
        ) {
            Column(
                modifier = Modifier
                    .clickable { onEdit() }
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = Date(task.dueTime).toString(),
            style = MaterialTheme.typography.h5
        )

        Spacer(modifier = Modifier.width(16.dp))

        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete Task"
            )
        }

    }
}

@Preview
@Composable
fun TaskItemPrev() {
    TaskItem(
        task = Task(
            title = "Valorant Training",
            description = "Aim Training for one hour",
            priority = Priority.MEDIUM,
            creationTime = 3L,
            dueTime = 6L,
            complete = false,
            id = 1
        ),
        onEdit = {},
        onDelete = {}
    )
}