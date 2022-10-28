package com.etoolkit.thespace.data.network.events

import com.etoolkit.thespace.domain.events.model.ResultEvents
import com.etoolkit.thespace.domain.events.repository.EventsRepository

class EventsRepositoryImpl(private val service: EventsApiService) : EventsRepository {

    override suspend fun getEvents(): ResultEvents {
        return service.getEvents()
    }
}