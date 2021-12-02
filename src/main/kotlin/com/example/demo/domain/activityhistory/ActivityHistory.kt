package com.example.demo.domain.activityhistory

import com.example.demo.domain.task.Task

class ActivityHistory private constructor(
    val detail: String
){
    companion object {
        fun createFromTask(task: Task) = ActivityHistory("${task.taskCreateParameter.taskName} が作成されました")
    }
}

interface ActivityHistoryRepository {
    fun insert(activityHistory: ActivityHistory)
}