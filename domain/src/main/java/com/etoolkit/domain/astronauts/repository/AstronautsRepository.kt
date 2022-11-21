package com.etoolkit.domain.astronauts.repository

import com.etoolkit.domain.astronauts.model.ResultAstronauts

interface AstronautsRepository {

    suspend fun getAstronauts() : ResultAstronauts

    suspend fun getAstronautsOffset(offset : String) : ResultAstronauts

    suspend fun getAstronautsSearch(search : String) : ResultAstronauts

}