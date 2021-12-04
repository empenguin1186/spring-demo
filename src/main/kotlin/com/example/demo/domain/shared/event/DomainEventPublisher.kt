package com.example.demo.domain.shared.event

interface DomainEventPublisher {
    fun publish(event: DomainEvent)
}