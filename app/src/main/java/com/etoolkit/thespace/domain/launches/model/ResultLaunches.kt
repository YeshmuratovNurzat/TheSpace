package com.etoolkit.thespace.domain.launches.model

data class ResultLaunches(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Launch>
)