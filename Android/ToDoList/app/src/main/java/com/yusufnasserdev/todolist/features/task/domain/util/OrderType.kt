package com.yusufnasserdev.todolist.features.task.domain.util

sealed class OrderType {
    data object Ascending: OrderType()
    data object Descending: OrderType()
}
