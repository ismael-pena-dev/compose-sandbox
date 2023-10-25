package com.pena.ismael.timeline.pokemon.repository.local

import com.pena.ismael.timeline.pokemon.model.Pokemon
import com.pena.ismael.timeline.pokemon.repository.local.PokemonEntityMapper.toEntity
import com.pena.ismael.timeline.pokemon.repository.local.PokemonEntityMapper.toPokemon
import com.pena.ismael.timeline.pokemon.repository.local.room.PokemonDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class PokemonLocalDataSourceRoomImpl @Inject constructor(
    private val db: PokemonDatabase
) : PokemonLocalDataSource {
    private val dao
        get() = db.pokemonDao()

    override fun getCached(): Flow<List<Pokemon>> {
        return dao.getCached().transform { entityList ->
            emit(
                entityList.map { entity ->
                    entity.toPokemon()
                }
            )
        }
    }

    override suspend fun insert(pokemonList: List<Pokemon>) {
        val pokemonEntities = pokemonList.map { it.toEntity() }
        dao.insert(pokemonEntities)
    }

    override suspend fun update(pokemon: Pokemon) {
        dao.update(pokemon.toEntity())
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override fun closeDb() {
        db.close()
    }
}