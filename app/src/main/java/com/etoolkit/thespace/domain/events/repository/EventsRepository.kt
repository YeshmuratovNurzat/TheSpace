package com.etoolkit.thespace.domain.events.repository

import com.etoolkit.thespace.domain.events.model.ResultEvents

interface EventsRepository {

    suspend fun getEvents() : ResultEvents
}