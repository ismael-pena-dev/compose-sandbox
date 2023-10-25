package com.pena.ismael.timeline.pokemon.repository.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pena.ismael.timeline.pokemon.repository.local.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("SELECT * FROM ${PokemonDatabase.POKEMON_DATABASE}")
    fun getCached(): Flow<List<PokemonEntity>>

    @Insert
    suspend fun insert(pokemonList: List<PokemonEntity>)

    @Query("DELETE FROM ${PokemonDatabase.POKEMON_DATABASE}")
    suspend fun deleteAll()
    @Update
    suspend fun update(pokemon: PokemonEntity)
}