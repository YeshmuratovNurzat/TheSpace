package com.etoolkit.thespace.domain.events.model

data class Mission(
    val description: String,
    val id: Int,
    val launch_designator: Any,
    val name: String,
    val orbit: Orbit,
    val type: String
)