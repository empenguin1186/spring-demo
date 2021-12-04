package com.example.demo.domain.task

import com.example.demo.domain.shared.event.DomainEventStorable
import com.example.demo.domain.task.creator.TaskCreateParameter

/** タスク */
class Task private constructor(
    val taskName: String
) : DomainEventStorable() {
    companion object {
        fun create(taskCreateParameter: TaskCreateParameter) = Task(taskCreateParameter.taskName)

        fun create(taskName: String): Task {
            val task = Task(taskName = taskName)
            task.addDomainEvent(TaskCreatedEvent(taskName))
            return task
        }
    }
}

interface TaskRepository {
    fun insert(task: Task)
}