package com.yusufnasserdev.todolist.features.task.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yusufnasserdev.todolist.features.task.domain.model.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

}