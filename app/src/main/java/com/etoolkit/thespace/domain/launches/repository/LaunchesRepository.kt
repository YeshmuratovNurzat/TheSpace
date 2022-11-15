package com.etoolkit.thespace.domain.launches.repository

import com.etoolkit.thespace.domain.launches.model.ResultLaunches

interface LaunchesRepository {

    suspend fun getLaunches() : ResultLaunches

    suspend fun getLaunchesUpcoming() : ResultLaunches
}