package com.etoolkit.thespace.domain.agencies.usecase

import com.etoolkit.thespace.domain.agencies.model.ResultAgencies
import com.etoolkit.thespace.domain.agencies.repository.AgenciesRepository

class GetAgenciesUseCase(private val repository: AgenciesRepository) {

    suspend operator fun invoke() : ResultAgencies {
        return repository.getAgencies()
    }

}