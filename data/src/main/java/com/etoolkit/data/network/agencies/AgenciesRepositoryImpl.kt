package com.etoolkit.data.network.agencies

import com.etoolkit.domain.agencies.model.ResultAgencies
import com.etoolkit.domain.agencies.repository.AgenciesRepository

class AgenciesRepositoryImpl(private val service: AgenciesApiService) : AgenciesRepository {

    override suspend fun getAgencies(): ResultAgencies {
        return service.getAgencies()
    }

}