package com.etoolkit.domain.agencies.repository

import com.etoolkit.domain.agencies.model.ResultAgencies

interface AgenciesRepository {

    suspend fun getAgencies() : ResultAgencies

    suspend fun getAgenciesSearch(search : String) : ResultAgencies

}