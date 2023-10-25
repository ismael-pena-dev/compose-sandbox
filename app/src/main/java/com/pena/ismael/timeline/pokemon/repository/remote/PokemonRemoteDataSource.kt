package com.pena.ismael.timeline.pokemon.repository.remote

import com.pena.ismael.timeline.core.network.NetworkResponse
import com.pena.ismael.timeline.pokemon.model.Pokemon

interface PokemonRemoteDataSource {
    suspend fun fetchNext(
        startIndexInclusive: Int,
        amount: Int,
    ): NetworkResponse<List<Pokemon>>

    suspend fun fetchPokemonDetails(
        id: Int,
    ): NetworkResponse<Pokemon>
}