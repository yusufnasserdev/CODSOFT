package com.yusufnasserdev.todolist.features.task.domain.util

sealed class TaskOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : TaskOrder(orderType)
    class Priority(orderType: OrderType) : TaskOrder(orderType)
    class TimeDue(orderType: OrderType) : TaskOrder(orderType)
    class TimeCreated(orderType: OrderType) : TaskOrder(orderType)
}
