package com.yusufnasserdev.todolist.features.task.domain.util

sealed class TaskOrder(val orderType: OrderType) {
    class Priority(orderType: OrderType) : TaskOrder(orderType)
    class TimeDue(orderType: OrderType) : TaskOrder(orderType)
    class TimeCreated(orderType: OrderType) : TaskOrder(orderType)

    fun copy(orderType: OrderType): TaskOrder {
        return when (this) {
            is Priority -> Priority(orderType)
            is TimeDue -> TimeDue(orderType)
            is TimeCreated -> TimeCreated(orderType)
        }
    }
}
