package com.example.demo.domain.activityhistory

import com.example.demo.domain.task.Task
import com.example.demo.domain.task.TaskCreatedEvent

class ActivityHistory private constructor(
    val detail: String
){
    companion object {
        fun createFromTask(task: Task) = ActivityHistory("${task.taskName} が作成されました. 担当者は ${task.assigned} です")

        fun createFromTaskCreatedEvent(event: TaskCreatedEvent) = ActivityHistory("${event.taskName} が作成されました. 担当者は ${event.assigned} です")
    }
}

interface ActivityHistoryRepository {
    fun insert(activityHistory: ActivityHistory)
}