package com.etoolkit.thespace.data.network.astronauts

import com.etoolkit.thespace.domain.astronauts.model.ResultAstronauts
import retrofit2.http.GET
import retrofit2.http.Query

interface AstronautsApiService {

    @GET("astronaut/?limit=100")
    suspend fun getAstronauts() : ResultAstronauts

    @GET("astronaut/?limit=100")
    suspend fun getAstronautsOffset(@Query("offset") offset : String) : ResultAstronauts

    @GET("astronaut")
    suspend fun getAstronautsSearch(@Query("search") search : String) : ResultAstronauts

}