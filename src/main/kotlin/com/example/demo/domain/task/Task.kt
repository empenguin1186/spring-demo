package com.example.demo.domain.task

import com.example.demo.domain.shared.event.DomainEventStorable
import com.example.demo.domain.task.creator.TaskCreateParameter

/** タスク */
data class Task private constructor(
    val taskName: String,
    val assignee: String
) : DomainEventStorable() {
    companion object {
        fun create(taskCreateParameter: TaskCreateParameter) = Task(taskCreateParameter.taskName, taskCreateParameter.assignee)

        fun create(taskName: String, assignee: String): Task {
            val task = Task(taskName = taskName, assignee = assignee)
            task.addDomainEvent(TaskCreatedEvent(taskName, assignee))
            return task
        }
    }
}

interface TaskRepository {
    fun insert(task: Task)
}