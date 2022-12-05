package com.etoolkit.data.network.agencies

import com.etoolkit.domain.agencies.model.ResultAgencies
import retrofit2.http.GET
import retrofit2.http.Query

interface AgenciesApiService {

    @GET("agencies?limit=100")
    suspend fun getAgencies() : ResultAgencies

    @GET("agencies")
    suspend fun getAgenciesSearch(@Query("search") search : String) : ResultAgencies
}