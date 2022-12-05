package com.etoolkit.domain.launches.usecase

import com.etoolkit.domain.launches.model.ResultLaunches
import com.etoolkit.domain.launches.repository.LaunchesRepository

class GetLaunchesPastUseCase(private val repository: LaunchesRepository) {

    suspend operator fun invoke() : ResultLaunches {
        return repository.getLaunchesPast()
    }
}