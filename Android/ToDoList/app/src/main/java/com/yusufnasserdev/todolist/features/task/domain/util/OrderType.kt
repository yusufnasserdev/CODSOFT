package com.yusufnasserdev.todolist.features.task.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
