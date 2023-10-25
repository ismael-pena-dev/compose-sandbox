package com.pena.ismael.timeline.pokemon.repository.remote

import com.pena.ismael.timeline.pokemon.model.Pokemon
import com.pena.ismael.timeline.pokemon.repository.remote.dto.detail.PokemonDetailDto
import com.pena.ismael.timeline.pokemon.repository.remote.dto.list.PokemonListDto

object PokemonDtoMapper {
    fun PokemonListDto.toPokemonList(startIndexInclusive: Int): List<Pokemon> {
        return this.results.mapIndexed { index, result ->
            Pokemon(id = index + startIndexInclusive, name = result.name.replaceFirstChar { it.uppercaseChar() })
        }
    }

    fun PokemonDetailDto.toPokemon(): Pokemon {
        return Pokemon(
            id = id,
            name = name,
            elementOne = types.getOrNull(0)?.type?.name,
            elementTwo = types.getOrNull(1)?.type?.name,
            weightKg = weight,
            heightM = height,
            hp = stats.find { it.stat.name == "hp" }?.base_stat,
            atk = stats.find { it.stat.name == "attack" }?.base_stat,
            def = stats.find { it.stat.name == "defense" }?.base_stat,
            spAtk = stats.find { it.stat.name == "special-attack" }?.base_stat,
            spDef = stats.find { it.stat.name == "special-defense" }?.base_stat,
            spd = stats.find { it.stat.name == "speed" }?.base_stat,
        )
    }
}

