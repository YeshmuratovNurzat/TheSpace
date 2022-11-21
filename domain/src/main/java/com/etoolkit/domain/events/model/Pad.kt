package com.etoolkit.domain.events.model

data class Pad(
    val agency_id: Int,
    val id: Int,
    val info_url: Any,
    val latitude: String,
    val location: Location,
    val longitude: String,
    val map_image: String,
    val map_url: String,
    val name: String,
    val orbital_launch_attempt_count: Int,
    val total_launch_count: Int,
    val url: String,
    val wiki_url: String
)