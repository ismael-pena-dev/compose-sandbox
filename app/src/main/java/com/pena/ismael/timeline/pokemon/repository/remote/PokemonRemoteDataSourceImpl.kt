package com.pena.ismael.timeline.pokemon.repository.remote

import com.pena.ismael.timeline.core.network.NetworkResponse
import com.pena.ismael.timeline.pokemon.model.Pokemon
import com.pena.ismael.timeline.pokemon.repository.remote.PokemonDtoMapper.toPokemon
import com.pena.ismael.timeline.pokemon.repository.remote.PokemonDtoMapper.toPokemonList
import retrofit2.HttpException
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(
    private val pokemonApi: PokemonApi
) : PokemonRemoteDataSource {
    override suspend fun fetchNext(
        startIndexInclusive: Int,
        amount: Int,
    ): NetworkResponse<List<Pokemon>> {
        return try {
            val pokemonList = pokemonApi.fetchNext(
                startIndexExclusive = startIndexInclusive - 1,
                amount = amount
            )
            NetworkResponse.Success(pokemonList.toPokemonList(startIndexInclusive))
        } catch (e: HttpException) {
            NetworkResponse.Error(message = e.message())
        } catch (e: Exception) {
            NetworkResponse.Error(message = e.message)
        }
    }

    override suspend fun fetchPokemonDetails(
        id: Int,
    ): NetworkResponse<Pokemon> {
        return try {
            val pokemonDetails = pokemonApi.fetchPokemonDetails(id)
            NetworkResponse.Success(pokemonDetails.toPokemon())
        } catch (e: HttpException) {
            NetworkResponse.Error(message = e.message())
        } catch (e: Exception) {
            NetworkResponse.Error(message = e.message)
        }
    }
}