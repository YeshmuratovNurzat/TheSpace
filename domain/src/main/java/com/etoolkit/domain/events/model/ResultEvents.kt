package com.etoolkit.domain.events.model

data class ResultEvents(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Event>
)