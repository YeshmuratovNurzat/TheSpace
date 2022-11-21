package com.etoolkit.domain.events.model


import java.io.Serializable

data class Event(
    val date: String, //
    val description: String, //
    val expeditions: List<Any>,
    val feature_image: String, //
    val id: Int, //
    val launches: List<Launche>,
    val location: String, //
    val name: String, //
    val news_url: String, //
    val program: List<ProgramX>,
    val slug: String, //
    val spacestations: List<Any>,
    val type: Type,
    val updates: List<Any>,
    val url: String, //
    val video_url: String,
    val webcast_live: Boolean //
) : Serializable{

    override fun hashCode(): Int {
        var result = name.hashCode()
        if(name.isEmpty()){
            result = 31 * result + url.hashCode()
        }
        return result
    }
}