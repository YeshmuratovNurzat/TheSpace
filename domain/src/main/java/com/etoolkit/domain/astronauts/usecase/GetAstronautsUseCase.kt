package com.etoolkit.domain.astronauts.usecase

import com.etoolkit.domain.astronauts.model.ResultAstronauts
import com.etoolkit.domain.astronauts.repository.AstronautsRepository

class GetAstronautsUseCase(private var repository: AstronautsRepository) {

    suspend operator fun invoke() : ResultAstronauts {
        return repository.getAstronauts()
    }
}