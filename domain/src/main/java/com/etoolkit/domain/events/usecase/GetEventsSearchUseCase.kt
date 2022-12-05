package com.etoolkit.domain.events.usecase

import com.etoolkit.domain.events.model.ResultEvents
import com.etoolkit.domain.events.repository.EventsRepository

class GetEventsSearchUseCase(private val repository: EventsRepository) {

    suspend fun invoke(search : String) : ResultEvents {
        return repository.getEventsSearch(search)
    }
}