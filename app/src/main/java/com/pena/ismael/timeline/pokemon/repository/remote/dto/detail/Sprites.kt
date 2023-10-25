package com.pena.ismael.timeline.pokemon.repository.remote.dto.detail

data class Sprites(
    val back_default: String,
    val back_female: Any,
    val back_shiny: String,
    val back_shiny_female: Any,
    val front_default: String,
    val front_female: Any,
    val front_shiny: String,
    val front_shiny_female: Any,
    val other: com.pena.ismael.timeline.pokemon.repository.remote.dto.detail.Other,
    val versions: com.pena.ismael.timeline.pokemon.repository.remote.dto.detail.Versions
)