package com.etoolkit.thespace.data.network.launches

import com.etoolkit.thespace.domain.launches.model.ResultLaunches
import retrofit2.http.GET

interface LaunchesApiService {

    @GET("launch/limit=100")
    suspend fun getLaunches() : ResultLaunches

    @GET("launch/upcoming")
    suspend fun getLaunchesUpcoming() : ResultLaunches

}