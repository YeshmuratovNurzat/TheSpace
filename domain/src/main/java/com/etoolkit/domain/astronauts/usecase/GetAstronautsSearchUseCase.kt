package com.etoolkit.domain.astronauts.usecase

import com.etoolkit.domain.astronauts.model.ResultAstronauts
import com.etoolkit.domain.astronauts.repository.AstronautsRepository

class GetAstronautsSearchUseCase(private val repository: AstronautsRepository) {

    suspend operator fun invoke(search : String) : ResultAstronauts {
        return repository.getAstronautsSearch(search)
    }
}