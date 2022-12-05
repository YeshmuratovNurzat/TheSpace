package com.etoolkit.data.network.launches

import com.etoolkit.domain.launches.model.ResultLaunches
import retrofit2.http.GET
import retrofit2.http.Query

interface LaunchesApiService {

    @GET("launch?limit=100")
    suspend fun getLaunchesPast() : ResultLaunches

    @GET("launch/upcoming")
    suspend fun getLaunchesUpcoming() : ResultLaunches

    @GET("launch")
    suspend fun getLaunchesSearch(@Query("search") search : String) : ResultLaunches

}