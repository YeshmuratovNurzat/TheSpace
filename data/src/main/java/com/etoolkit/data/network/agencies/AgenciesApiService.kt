package com.etoolkit.data.network.agencies

import com.etoolkit.domain.agencies.model.ResultAgencies
import retrofit2.http.GET

interface AgenciesApiService {

    @GET("agencies")
    suspend fun getAgencies() : ResultAgencies
}