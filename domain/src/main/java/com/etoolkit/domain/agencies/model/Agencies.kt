package com.etoolkit.domain.agencies.model

import java.io.Serializable

data class Agencies(
    val abbrev: String,
    val administrator: String,
    val country_code: String,
    val description: String,
    val featured: Boolean,
    val founding_year: String,
    val id: Int,
    val image_url: String,
    val launchers: String,
    val logo_url: String,
    val name: String,
    val parent: Any,
    val spacecraft: String,
    val type: String,
    val url: String) : Serializable {

    override fun hashCode(): Int {
        var result = name.hashCode()
        if(name.isEmpty()){
            result = 31 * result + url.hashCode()
        }
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Agencies

        if (abbrev != other.abbrev) return false
        if (administrator != other.administrator) return false
        if (country_code != other.country_code) return false
        if (description != other.description) return false
        if (featured != other.featured) return false
        if (founding_year != other.founding_year) return false
        if (id != other.id) return false
        if (image_url != other.image_url) return false
        if (launchers != other.launchers) return false
        if (logo_url != other.logo_url) return false
        if (name != other.name) return false
        if (parent != other.parent) return false
        if (spacecraft != other.spacecraft) return false
        if (type != other.type) return false
        if (url != other.url) return false

        return true
    }

}