package com.pena.ismael.timeline.pokemon.model

data class Pokemon(
    val id: Int,
    val name: String,
    val elementOne: String? = null,
    val elementTwo: String? = null,
    val weightKg: Int? = null,
    val heightM: Int? = null,
    val hp: Int? = null,
    val atk: Int? = null,
    val def: Int? = null,
    val spAtk: Int? = null,
    val spDef: Int? = null,
    val spd: Int? = null,
) {
    val imageUrl: String
        get() ="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
}