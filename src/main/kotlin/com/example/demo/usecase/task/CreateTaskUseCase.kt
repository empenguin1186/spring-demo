package com.example.demo.usecase.task

import com.example.demo.domain.task.creator.TaskCreator
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CreateTaskUseCase(
    private val taskCreator: TaskCreator
) {
    @Transactional
    fun execute(taskName: String) {
        taskCreator.createWithEventListener(taskName)
    }
}