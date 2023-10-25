package com.pena.ismael.timeline.pokemon.repository.remote.dto.list

data class PokemonListDto(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<com.pena.ismael.timeline.pokemon.repository.remote.dto.list.Result>
)