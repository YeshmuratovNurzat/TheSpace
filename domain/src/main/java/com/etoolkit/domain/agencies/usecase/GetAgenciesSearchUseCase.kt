package com.etoolkit.domain.agencies.usecase

import com.etoolkit.domain.agencies.model.ResultAgencies
import com.etoolkit.domain.agencies.repository.AgenciesRepository

class GetAgenciesSearchUseCase(private val repository: AgenciesRepository) {

    suspend fun invoke(search : String) : ResultAgencies {
        return repository.getAgenciesSearch(search)
    }

}