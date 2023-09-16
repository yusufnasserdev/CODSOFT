package com.yusufnasserdev.todolist.features.task.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    val title: String,
    val description: String,
    val priority: Priority,
    val creationTime: Long,
    val dueTime: Long,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val taskPriorities = listOf(Priority.LOW, Priority.MEDIUM, Priority.HIGH)
    }

}
