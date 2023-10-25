package com.pena.ismael.timeline.pokemon.repository.local

import com.pena.ismael.timeline.pokemon.model.Pokemon
import com.pena.ismael.timeline.pokemon.repository.local.entity.PokemonEntity

object PokemonEntityMapper {
    fun Pokemon.toEntity(): PokemonEntity {
        return PokemonEntity(id, name, elementOne, elementTwo, weightKg, heightM, hp, atk, def, spAtk, spDef, spd)
    }

    fun PokemonEntity.toPokemon(): Pokemon {
        return Pokemon(id, name, elementOne, elementTwo, weightKg, heightM, hp, atk, def, spAtk, spDef, spd)
    }
}