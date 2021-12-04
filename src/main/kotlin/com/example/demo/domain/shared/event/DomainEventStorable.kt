package com.example.demo.domain.shared.event

abstract class DomainEventStorable {

    private val domainEvents: MutableList<DomainEvent> = mutableListOf()

    protected fun addDomainEvent(domainEvent: DomainEvent) {
        this.domainEvents.add(domainEvent)
    }

    fun getDomainEvents(): List<DomainEvent> = this.domainEvents

    fun clearDomainEvents() {
        this.domainEvents.clear()
    }
}