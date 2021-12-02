package com.example.demo.domain.task

import com.example.demo.domain.task.creator.TaskCreateParameter

/** タスク */
class Task(
    val taskCreateParameter: TaskCreateParameter
) {
    companion object {
        fun create(taskCreateParameter: TaskCreateParameter) = Task(taskCreateParameter)
    }
}

interface TaskRepository {
    fun insert(task: Task)
}