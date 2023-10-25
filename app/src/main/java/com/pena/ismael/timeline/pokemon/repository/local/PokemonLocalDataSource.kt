package com.pena.ismael.timeline.pokemon.repository.local

import com.pena.ismael.timeline.pokemon.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonLocalDataSource {
    fun getCached(): Flow<List<Pokemon>>

    suspend fun insert(pokemonList: List<Pokemon>)

    suspend fun update(pokemon: Pokemon)

    suspend fun deleteAll()
    fun closeDb()
}