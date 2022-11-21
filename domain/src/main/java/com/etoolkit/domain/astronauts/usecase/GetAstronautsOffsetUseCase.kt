package com.etoolkit.domain.astronauts.usecase

import com.etoolkit.domain.astronauts.model.ResultAstronauts
import com.etoolkit.domain.astronauts.repository.AstronautsRepository

class GetAstronautsOffsetUseCase(private val repository: AstronautsRepository){

    suspend operator fun invoke(offset : String) : ResultAstronauts{
        return repository.getAstronautsOffset(offset)
    }
}