package com.pena.ismael.timeline.pokemon.repository

import com.pena.ismael.timeline.core.network.NetworkResponse
import com.pena.ismael.timeline.pokemon.FetchStatus
import com.pena.ismael.timeline.pokemon.model.Pokemon
import com.pena.ismael.timeline.pokemon.repository.local.PokemonLocalDataSource
import com.pena.ismael.timeline.pokemon.repository.remote.PokemonRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonRepositoryDelegator @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val localDataSource: PokemonLocalDataSource,
) {
    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    private val cacheSize: Int
        get() = _pokemonList.value.size
    val pokemonList: StateFlow<List<Pokemon>>
        get() = _pokemonList

    private val _fetchStatus = MutableStateFlow<FetchStatus>(FetchStatus.Idle)
    val fetchStatus: StateFlow<FetchStatus>
        get() = _fetchStatus

    fun getInitial(coroutineScope: CoroutineScope, dispatcher: CoroutineDispatcher) {
        coroutineScope.launch(dispatcher) {
            val localCacheFlow = localDataSource.getCached()
            localCacheFlow.collectLatest {
                _pokemonList.value = it
            }
        }
    }

    fun fetchNext(amount: Int, coroutineScope: CoroutineScope, dispatcher: CoroutineDispatcher) {
        coroutineScope.launch(dispatcher) {
            _fetchStatus.value = FetchStatus.Loading
            val resp = remoteDataSource.fetchNext(startIndexInclusive = cacheSize + 1, amount,)
            when (resp) {
                is NetworkResponse.Error -> {
                    // TODO: Error handling
                    _fetchStatus.value = FetchStatus.Idle
                }
                is NetworkResponse.Success -> {
                    _fetchStatus.value = FetchStatus.Idle
                    val respList = resp.data
                    if (!respList.isNullOrEmpty())
                        localDataSource.insert(respList)
                }
            }
        }
    }

    suspend fun fetchPokemonDetails(id: Int): Pokemon? {
        _fetchStatus.value = FetchStatus.Loading
        val pokemonEntry = _pokemonList.value.find { it.id == id }
        val isCached = pokemonEntry?.weightKg != null
        if (isCached) {
            _fetchStatus.value = FetchStatus.Idle
            return pokemonEntry
        } else {
            return when (val resp = remoteDataSource.fetchPokemonDetails(id)) {
                is NetworkResponse.Error -> {
                    _fetchStatus.value = FetchStatus.Idle
                    null // TODO: Error handling
                }
                is NetworkResponse.Success -> {
                    _fetchStatus.value = FetchStatus.Idle
                    val fetchedPokemon = resp.data
                    if (fetchedPokemon != null) {
                        localDataSource.update(fetchedPokemon)
                    }
                    fetchedPokemon
                }
            }
        }
    }

    fun clearCache(coroutineScope: CoroutineScope, dispatcher: CoroutineDispatcher) {
        coroutineScope.launch(dispatcher) {
            localDataSource.deleteAll()
        }
    }

    fun closeDb() {
        localDataSource.closeDb()
    }
}