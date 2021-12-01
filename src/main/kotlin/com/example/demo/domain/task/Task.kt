package com.example.demo.domain.task

/** タスク */
class Task(
    val taskName: String
)

interface TaskRepository {
    fun insert(task: Task)
}