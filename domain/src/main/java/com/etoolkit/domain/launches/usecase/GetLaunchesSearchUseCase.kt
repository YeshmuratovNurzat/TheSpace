package com.etoolkit.domain.launches.usecase

import com.etoolkit.domain.launches.model.ResultLaunches
import com.etoolkit.domain.launches.repository.LaunchesRepository

class GetLaunchesSearchUseCase(private val repository: LaunchesRepository) {

    suspend fun invoke(search : String) : ResultLaunches {
        return repository.getLaunchesSearch(search)
    }
}