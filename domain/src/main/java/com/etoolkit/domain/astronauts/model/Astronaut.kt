package com.etoolkit.domain.astronauts.model

import java.io.Serializable

class Astronaut(
    var age: Int,
    val agency: Agency,
    val bio: String,
    val date_of_birth: String,
    val date_of_death: Any,
    val first_flight: String,
    val flights_count: Int,
    val id: Int,
    val instagram: String,
    val landings_count: Int,
    val last_flight: String,
    val name: String,
    val nationality: String,
    val profile_image: String,
    val profile_image_thumbnail: String,
    val status: Status,
    val twitter: String,
    val type: Type,
    val url: String,
    val wiki: String
) : Serializable