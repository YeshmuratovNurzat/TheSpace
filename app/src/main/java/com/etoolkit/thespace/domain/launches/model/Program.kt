package com.etoolkit.thespace.domain.launches.model

data class Program(
    val agencies: List<Agency>,
    val description: String,
    val end_date: String,
    val id: Int,
    val image_url: String,
    val info_url: String,
    val mission_patches: List<MissionPatche>,
    val name: String,
    val start_date: String,
    val url: String,
    val wiki_url: String
)