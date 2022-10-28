package com.etoolkit.thespace.domain.events.model

data class Configuration(
    val family: String,
    val full_name: String,
    val id: Int,
    val name: String,
    val url: String,
    val variant: String
)