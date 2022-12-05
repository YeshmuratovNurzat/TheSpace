package com.etoolkit.domain.events.repository

import com.etoolkit.domain.events.model.ResultEvents

interface EventsRepository {

    suspend fun getEvents() : ResultEvents

    suspend fun getEventsSearch(search : String) : ResultEvents

}