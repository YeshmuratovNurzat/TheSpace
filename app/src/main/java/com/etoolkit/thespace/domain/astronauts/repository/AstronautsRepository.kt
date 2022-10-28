package com.etoolkit.thespace.domain.astronauts.repository

import com.etoolkit.thespace.domain.astronauts.model.ResultAstronauts

interface AstronautsRepository {

    suspend fun getAstronauts() : ResultAstronauts

    suspend fun getAstronautsOffset(offset : String) : ResultAstronauts

    suspend fun getAstronautsSearch(search : String) : ResultAstronauts

}