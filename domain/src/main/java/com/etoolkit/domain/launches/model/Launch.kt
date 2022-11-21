package com.etoolkit.domain.launches.model

import java.io.Serializable

data class Launch(

    val agency_launch_attempt_count: Int,
    val agency_launch_attempt_count_year: Int,
    val failreason: String,
    val hashtag: String,
    val holdreason: String,
    val id: String,
    val image: String,
    val infographic: String,
    val last_updated: String,
    val launch_service_provider: LaunchServiceProvider,
    val location_launch_attempt_count: Int,
    val location_launch_attempt_count_year: Int,
    val mission: Mission,
    val name: String? = null,
    val net: String,
    val orbital_launch_attempt_count: Int,
    val orbital_launch_attempt_count_year: Int,
    val pad: Pad,
    val pad_launch_attempt_count: Int,
    val pad_launch_attempt_count_year: Int,
    val probability: Int,
    val program: List<Program>,
    val rocket: Rocket,
    val slug: String,
    val status: Status,
    val url: String,
    val webcast_live: Boolean,
    val window_end: String,
    val window_start: String) : Serializable {

    override fun hashCode(): Int {
        var result = name.hashCode()
        if(name.isNullOrEmpty()){
            result = 31 * result + url.hashCode()
        }
        return result
    }

}
