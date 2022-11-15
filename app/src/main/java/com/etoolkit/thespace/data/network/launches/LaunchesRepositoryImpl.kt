package com.etoolkit.thespace.data.network.launches

import com.etoolkit.thespace.domain.launches.model.ResultLaunches
import com.etoolkit.thespace.domain.launches.repository.LaunchesRepository

class LaunchesRepositoryImpl(private val service: LaunchesApiService) : LaunchesRepository {

    override suspend fun getLaunches(): ResultLaunches {
        return service.getLaunches()
    }

    override suspend fun getLaunchesUpcoming(): ResultLaunches {
        return service.getLaunchesUpcoming()
    }
}