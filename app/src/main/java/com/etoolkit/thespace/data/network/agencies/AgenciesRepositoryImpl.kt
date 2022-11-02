package com.etoolkit.thespace.data.network.agencies

import com.etoolkit.thespace.domain.agencies.model.ResultAgencies
import com.etoolkit.thespace.domain.agencies.repository.AgenciesRepository

class AgenciesRepositoryImpl(private val service: AgenciesApiService) : AgenciesRepository {

    override suspend fun getAgencies(): ResultAgencies {
        return service.getAgencies()
    }

}