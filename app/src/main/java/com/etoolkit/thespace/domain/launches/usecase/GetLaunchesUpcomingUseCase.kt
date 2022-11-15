package com.etoolkit.thespace.domain.launches.usecase

import com.etoolkit.thespace.domain.launches.model.ResultLaunches
import com.etoolkit.thespace.domain.launches.repository.LaunchesRepository

class GetLaunchesUpcomingUseCase(private val repository: LaunchesRepository) {

    suspend operator fun invoke() : ResultLaunches {
        return repository.getLaunchesUpcoming()
    }
}