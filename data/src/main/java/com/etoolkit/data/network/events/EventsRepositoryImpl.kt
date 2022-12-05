package com.etoolkit.data.network.events

import com.etoolkit.domain.events.model.ResultEvents
import com.etoolkit.domain.events.repository.EventsRepository


class EventsRepositoryImpl(private val service: EventsApiService) : EventsRepository {

    override suspend fun getEvents(): ResultEvents {
        return service.getEvents()
    }

    override suspend fun getEventsSearch(search: String): ResultEvents {
        return service.getEventsSearch(search)
    }
}