package com.etoolkit.thespace.domain.agencies.repository

import com.etoolkit.thespace.domain.agencies.model.ResultAgencies

interface AgenciesRepository {

    suspend fun getAgencies() : ResultAgencies

}