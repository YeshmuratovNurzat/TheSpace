package com.etoolkit.domain.events.usecase

import com.etoolkit.domain.events.model.ResultEvents
import com.etoolkit.domain.events.repository.EventsRepository

class GetEventsUseCase(private val repository: EventsRepository) {

    suspend operator fun invoke() : ResultEvents {
        return repository.getEvents()
    }
}