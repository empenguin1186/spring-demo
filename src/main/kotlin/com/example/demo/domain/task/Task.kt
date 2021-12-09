package com.example.demo.domain.task

import com.example.demo.domain.shared.event.DomainEventStorable
import com.example.demo.domain.task.creator.TaskCreateParameter

/** タスク */
class Task private constructor(
    val taskName: String,
    val assigned: String
) : DomainEventStorable() {
    companion object {
        fun create(taskCreateParameter: TaskCreateParameter) = Task(taskCreateParameter.taskName, taskCreateParameter.assigned)

        fun create(taskName: String, assigned: String): Task {
            val task = Task(taskName = taskName, assigned = assigned)
            task.addDomainEvent(TaskCreatedEvent(taskName, assigned))
            return task
        }
    }
}

interface TaskRepository {
    fun insert(task: Task)
}