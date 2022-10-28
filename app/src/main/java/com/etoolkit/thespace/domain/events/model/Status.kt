package com.etoolkit.thespace.domain.events.model

data class Status(
    val abbrev: String,
    val description: String,
    val id: Int,
    val name: String
)