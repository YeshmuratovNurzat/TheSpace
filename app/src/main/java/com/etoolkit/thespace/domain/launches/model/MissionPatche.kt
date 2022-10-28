package com.etoolkit.thespace.domain.launches.model

data class MissionPatche(
    val agency: Agency,
    val id: Int,
    val image_url: String,
    val name: String,
    val priority: Int
)