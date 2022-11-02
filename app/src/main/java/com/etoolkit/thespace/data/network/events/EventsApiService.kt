package com.etoolkit.thespace.data.network.events

import com.etoolkit.thespace.domain.events.model.ResultEvents
import retrofit2.http.GET
import retrofit2.http.Query

interface EventsApiService {

    @GET("event?limit=100")
    suspend fun getEvents() : ResultEvents

    @GET("event")
    suspend fun getSearchEvents(@Query("search")search : String) : ResultEvents
}