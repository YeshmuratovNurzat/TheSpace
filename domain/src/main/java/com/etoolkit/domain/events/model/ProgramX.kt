package com.etoolkit.domain.events.model

data class ProgramX(
    val agencies: List<Agency>,
    val description: String,
    val end_date: String,
    val id: Int,
    val image_url: String,
    val info_url: String,
    val mission_patches: List<Any>,
    val name: String,
    val start_date: String,
    val url: String,
    val wiki_url: String
){
    override fun hashCode(): Int {
        var result = name.hashCode()
        if(name.isEmpty()){
            result = 31 * result + url.hashCode()
        }
        return result
    }
}