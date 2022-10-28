package com.etoolkit.thespace.domain.astronauts.usecase

import com.etoolkit.thespace.domain.astronauts.model.ResultAstronauts
import com.etoolkit.thespace.domain.astronauts.repository.AstronautsRepository

class GetAstronautsUseCase(private var repository: AstronautsRepository) {

    suspend operator fun invoke() : ResultAstronauts {
        return repository.getAstronauts()
    }
}