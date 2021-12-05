package com.example.demo.domain.activityhistory

import com.example.demo.domain.task.TaskCreatedEvent
import org.springframework.context.event.EventListener

class ActivityHistoryEventListener(
    private val activityHistoryRepository: ActivityHistoryRepository
) {

    @EventListener
    internal fun createActivityHistory(event: TaskCreatedEvent) {
        val activityHistory = ActivityHistory.createFromTaskCreatedEvent(event)
        activityHistoryRepository.insert(activityHistory)
    }
}