package com.etoolkit.thespace.data.network.events

import com.etoolkit.thespace.domain.events.model.ResultEvents
import retrofit2.http.GET

interface EventsApiService {

    @GET("event")
    suspend fun getEvents() : ResultEvents
}