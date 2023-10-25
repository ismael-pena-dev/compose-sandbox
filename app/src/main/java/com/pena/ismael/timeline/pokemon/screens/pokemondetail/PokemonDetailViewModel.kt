package com.pena.ismael.timeline.pokemon.screens.pokemondetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pena.ismael.timeline.pokemon.model.Pokemon
import com.pena.ismael.timeline.pokemon.repository.PokemonRepositoryDelegator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepositoryDelegator,
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val id: Int = savedStateHandle["id"] ?: 1

    private val _pokemon = MutableStateFlow<Pokemon?>(null)
    val pokemon: StateFlow<Pokemon?>
        get() = _pokemon

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val fetchedPokemon = pokemonRepository.fetchPokemonDetails(id)
            _pokemon.value = fetchedPokemon
            // TODO: Error handling
        }
    }
}