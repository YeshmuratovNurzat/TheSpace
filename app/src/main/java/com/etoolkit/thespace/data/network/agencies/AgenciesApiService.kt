package com.etoolkit.thespace.data.network.agencies

import com.etoolkit.thespace.domain.agencies.model.ResultAgencies
import retrofit2.http.GET

interface AgenciesApiService {

    @GET("agencies")
    suspend fun getAgencies() : ResultAgencies
}