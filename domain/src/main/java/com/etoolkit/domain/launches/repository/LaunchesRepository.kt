package com.etoolkit.domain.launches.repository

import com.etoolkit.domain.launches.model.ResultLaunches

interface LaunchesRepository {

    suspend fun getLaunchesPast() : ResultLaunches

    suspend fun getLaunchesUpcoming() : ResultLaunches

    suspend fun getLaunchesSearch(search : String) : ResultLaunches
}