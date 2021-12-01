package com.example.demo.usecase.task

import com.example.demo.domain.activityhistory.ActivityHistory
import com.example.demo.domain.activityhistory.ActivityHistoryRepository
import com.example.demo.domain.task.Task
import com.example.demo.domain.task.TaskRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CreateTaskUseCase(
    private val taskRepository: TaskRepository,
    private val activityHistoryRepository: ActivityHistoryRepository
) {
    @Transactional
    fun execute(taskName: String) {
        val task = Task(taskName)
        taskRepository.insert(task)

        val activityHistory = ActivityHistory.createFromTask(task)
        activityHistoryRepository.insert(activityHistory)
    }
}