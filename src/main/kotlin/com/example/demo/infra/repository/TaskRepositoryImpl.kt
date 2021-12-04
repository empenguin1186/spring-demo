package com.example.demo.infra.repository

import com.example.demo.domain.shared.event.DomainEventPublisher
import com.example.demo.domain.task.Task
import com.example.demo.domain.task.TaskRepository
import com.example.demo.infra.repository.mapper.TaskMapper
import org.springframework.stereotype.Repository

@Repository
class TaskRepositoryImpl(
    private val taskMapper: TaskMapper,
    private val domainEventPublisher: DomainEventPublisher
): TaskRepository {

    override fun insert(task: Task) {
        taskMapper.insert(task)
        task.getDomainEvents().forEach(domainEventPublisher::publish)
        task.clearDomainEvents()
    }
}