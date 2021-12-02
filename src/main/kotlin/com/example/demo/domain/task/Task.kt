package com.example.demo.domain.task

import com.example.demo.domain.task.creator.TaskCreateParameter

/** タスク */
class Task private constructor(val taskName: String) {
    companion object {
        fun create(taskCreateParameter: TaskCreateParameter) = Task(taskCreateParameter.taskName)
    }
}

interface TaskRepository {
    fun insert(task: Task)
}