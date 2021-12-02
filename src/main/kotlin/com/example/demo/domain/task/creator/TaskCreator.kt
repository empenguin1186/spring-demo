package com.example.demo.domain.task.creator

import com.example.demo.domain.activityhistory.ActivityHistory
import com.example.demo.domain.activityhistory.ActivityHistoryRepository
import com.example.demo.domain.task.Task
import com.example.demo.domain.task.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskCreator(
    private val taskRepository: TaskRepository,
    private val activityHistoryRepository: ActivityHistoryRepository
) {
    fun create(taskName: String) {
        val task = Task.create(TaskCreateParameterImpl(taskName))
        taskRepository.insert(task)

        val activityHistory = ActivityHistory.createFromTask(task)
        activityHistoryRepository.insert(activityHistory)
    }
}

sealed interface TaskCreateParameter{
    val taskName: String
}

private data class TaskCreateParameterImpl(override val taskName: String): TaskCreateParameter