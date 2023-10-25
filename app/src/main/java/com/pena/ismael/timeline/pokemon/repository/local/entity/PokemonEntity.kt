package com.pena.ismael.timeline.pokemon.repository.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pena.ismael.timeline.pokemon.repository.local.room.PokemonDatabase

@Entity(tableName = PokemonDatabase.POKEMON_DATABASE)
data class PokemonEntity(
    @PrimaryKey val id: Int,
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
)