package com.pena.ismael.timeline.pokemon.repository.remote

import com.pena.ismael.timeline.pokemon.repository.remote.dto.detail.PokemonDetailDto
import com.pena.ismael.timeline.pokemon.repository.remote.dto.list.PokemonListDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon/")
    suspend fun fetchNext(
        @Query("offset") startIndexExclusive: Int,
        @Query("limit") amount: Int
    ): PokemonListDto

    @GET("pokemon/{id}")
    suspend fun fetchPokemonDetails(
        @Path("id") id: Int
    ): PokemonDetailDto

    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"
        fun buildRetrofit(): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        }
    }
}