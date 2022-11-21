package com.etoolkit.domain.agencies.usecase

import com.etoolkit.domain.agencies.model.ResultAgencies
import com.etoolkit.domain.agencies.repository.AgenciesRepository

class GetAgenciesUseCase(private val repository: AgenciesRepository) {

    suspend operator fun invoke() : ResultAgencies {
        return repository.getAgencies()
    }

}