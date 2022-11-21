package com.etoolkit.domain.astronauts.model

class ResultAstronauts (
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Astronaut>)