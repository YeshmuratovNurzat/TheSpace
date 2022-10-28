package com.etoolkit.thespace.domain.launches.model

data class Mission(
    val description: String,
    val id: Int,
    val launch_designator: String,
    val name: String,
    val orbit: Orbit,
    val type: String
)