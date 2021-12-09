package com.example.demo.domain.task

import com.example.demo.domain.shared.event.DomainEvent

class TaskCreatedEvent(val taskName: String, val assigned: String): DomainEvent