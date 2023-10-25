package com.pena.ismael.timeline.pokemon.repository.remote.dto.detail

data class PokemonDetailDto(
    val abilities: List<com.pena.ismael.timeline.pokemon.repository.remote.dto.detail.Ability>,
    val base_experience: Int,
    val forms: List<com.pena.ismael.timeline.pokemon.repository.remote.dto.detail.Form>,
    val game_indices: List<com.pena.ismael.timeline.pokemon.repository.remote.dto.detail.GameIndice>,
    val height: Int,
    val held_items: List<Any>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<com.pena.ismael.timeline.pokemon.repository.remote.dto.detail.Move>,
    val name: String,
    val order: Int,
    val past_abilities: List<Any>,
    val past_types: List<Any>,
    val species: com.pena.ismael.timeline.pokemon.repository.remote.dto.detail.Species,
    val sprites: com.pena.ismael.timeline.pokemon.repository.remote.dto.detail.Sprites,
    val stats: List<com.pena.ismael.timeline.pokemon.repository.remote.dto.detail.Stat>,
    val types: List<com.pena.ismael.timeline.pokemon.repository.remote.dto.detail.Type>,
    val weight: Int
)