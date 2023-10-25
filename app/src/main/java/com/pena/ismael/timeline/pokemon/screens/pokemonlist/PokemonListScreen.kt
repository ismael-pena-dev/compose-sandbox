package com.pena.ismael.timeline.pokemon.screens.pokemonlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.pena.ismael.timeline.pokemon.screens.pokemonlist.component.PokemonList
import com.pena.ismael.timeline.ui.theme.TimelineTheme

@Composable
fun PokemonListScreen(
    navController: NavHostController,
    viewModel: PokemonListViewModel = hiltViewModel(),
) {
    Column(Modifier.fillMaxSize()) {
        val pokemonList = viewModel.pokemonList.collectAsState().value
        PokemonList(pokemonList,
            onPokemonClick = {
                navController.navigate("pokemon/${it.id}")
            },
            onFetchMore = {
                viewModel.fetchMore()
            }, onClearCache = {
                viewModel.clearCache(navController.context)
            })
    }
}