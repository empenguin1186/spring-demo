package com.example.demo.infra.repository

import com.example.demo.domain.task.Task
import com.example.demo.domain.task.TaskRepository
import org.springframework.stereotype.Repository

@Repository
class TaskRepositoryImpl: TaskRepository {

    override fun insert(task: Task) {
        throw Exception()
    }
}