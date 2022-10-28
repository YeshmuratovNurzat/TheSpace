package com.etoolkit.thespace.domain.events.usecase

import com.etoolkit.thespace.domain.events.model.ResultEvents
import com.etoolkit.thespace.domain.events.repository.EventsRepository

class GetEventsUseCase(private val repository: EventsRepository) {

    suspend operator fun invoke() : ResultEvents {
        return repository.getEvents()
    }
}