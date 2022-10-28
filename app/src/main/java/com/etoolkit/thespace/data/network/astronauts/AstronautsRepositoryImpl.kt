package com.etoolkit.thespace.data.network.astronauts

import com.etoolkit.thespace.domain.astronauts.model.ResultAstronauts
import com.etoolkit.thespace.domain.astronauts.repository.AstronautsRepository

class AstronautsRepositoryImpl(private var service: AstronautsApiService) : AstronautsRepository {

    override suspend fun getAstronauts(): ResultAstronauts {
        return service.getAstronauts()
    }

    override suspend fun getAstronautsOffset(offset: String): ResultAstronauts {
        return service.getAstronautsOffset(offset)
    }

    override suspend fun getAstronautsSearch(search: String): ResultAstronauts {
        return service.getAstronautsSearch(search)
    }
}