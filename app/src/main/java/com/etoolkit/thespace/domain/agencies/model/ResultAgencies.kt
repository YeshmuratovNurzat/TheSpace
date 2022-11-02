package com.etoolkit.thespace.domain.agencies.model

data class ResultAgencies(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Agencies>
)