package com.pena.ismael.timeline.pokemon.screens.pokemonlist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.imageLoader
import com.pena.ismael.timeline.pokemon.FetchStatus
import com.pena.ismael.timeline.pokemon.model.Pokemon
import com.pena.ismael.timeline.pokemon.repository.PokemonRepositoryDelegator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepositoryDelegator
): ViewModel() {
    val pokemonList: StateFlow<List<Pokemon>>
        get() = pokemonRepository.pokemonList

    val fetchStatus: StateFlow<FetchStatus>
        get() = pokemonRepository.fetchStatus

    init {
        getInitial()
    }

    private fun getInitial() {
        pokemonRepository.getInitial(viewModelScope, Dispatchers.IO)
    }

    fun fetchMore() {
        pokemonRepository.fetchNext(20, viewModelScope, Dispatchers.IO)
    }

    fun clearCache(context: Context) {
        context.imageLoader.diskCache?.clear()
        pokemonRepository.clearCache(viewModelScope, Dispatchers.IO)
    }
}