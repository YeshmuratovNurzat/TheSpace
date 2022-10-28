package com.etoolkit.thespace.domain.astronauts.usecase

import com.etoolkit.thespace.domain.astronauts.model.ResultAstronauts
import com.etoolkit.thespace.domain.astronauts.repository.AstronautsRepository

class GetAstronautsOffsetUseCase(private val repository: AstronautsRepository){

    suspend operator fun invoke(offset : String) : ResultAstronauts{
        return repository.getAstronautsOffset(offset)
    }
}