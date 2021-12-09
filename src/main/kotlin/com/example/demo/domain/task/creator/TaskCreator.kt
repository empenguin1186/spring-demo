package com.example.demo.domain.task.creator

import com.example.demo.domain.activityhistory.ActivityHistory
import com.example.demo.domain.activityhistory.ActivityHistoryRepository
import com.example.demo.domain.exception.DataSourceRetryableException
import com.example.demo.domain.task.Task
import com.example.demo.domain.task.TaskRepository
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service

@Service
class TaskCreator(
    private val taskRepository: TaskRepository,
    private val activityHistoryRepository: ActivityHistoryRepository
) {
    fun create(taskName: String, assigned: String) {
        val task = Task.create(TaskCreateParameterImpl(taskName, assigned))
        taskRepository.insert(task)

        val activityHistory = ActivityHistory.createFromTask(task)
        activityHistoryRepository.insert(activityHistory)
    }

    @Retryable(interceptor = "dataSourceRetryInterceptor")
    @Throws(DataSourceRetryableException::class)
    fun createWithEventListener(taskName: String, assigned: String) {
        val task = Task.create(taskName, assigned)
        taskRepository.insert(task)
    }
}

sealed interface TaskCreateParameter{
    val taskName: String
    val assigned: String
}

private data class TaskCreateParameterImpl(override val taskName: String, override val assigned: String): TaskCreateParameter