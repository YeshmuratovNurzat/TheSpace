package com.etoolkit.thespace.domain.events.model

data class LaunchServiceProvider(
    val id: Int,
    val name: String,
    val type: String,
    val url: String
)