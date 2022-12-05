package com.etoolkit.data.network.launches

import com.etoolkit.domain.launches.model.ResultLaunches
import com.etoolkit.domain.launches.repository.LaunchesRepository

class LaunchesRepositoryImpl(private val service: LaunchesApiService) : LaunchesRepository {


    override suspend fun getLaunchesPast(): ResultLaunches {
        return service.getLaunchesPast()
    }

    override suspend fun getLaunchesUpcoming(): ResultLaunches {
        return service.getLaunchesUpcoming()
    }

    override suspend fun getLaunchesSearch(search : String): ResultLaunches {
        return service.getLaunchesSearch(search)
    }

}