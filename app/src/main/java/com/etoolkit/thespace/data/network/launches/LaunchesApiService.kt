package com.etoolkit.thespace.data.network.launches

import com.etoolkit.thespace.domain.launches.model.ResultLaunches
import retrofit2.http.GET

interface LaunchesApiService {

    @GET("launch")
    suspend fun getLaunches() : ResultLaunches

}