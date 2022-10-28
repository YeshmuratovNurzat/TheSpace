package com.etoolkit.thespace.domain.astronauts.usecase

import com.etoolkit.thespace.domain.astronauts.model.ResultAstronauts
import com.etoolkit.thespace.domain.astronauts.repository.AstronautsRepository

class GetAstronautsSearchUseCase(private val repository: AstronautsRepository) {

    suspend operator fun invoke(search : String) : ResultAstronauts {
        return repository.getAstronautsSearch(search)
    }
}