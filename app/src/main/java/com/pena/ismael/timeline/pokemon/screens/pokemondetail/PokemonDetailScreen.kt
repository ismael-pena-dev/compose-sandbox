@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package com.pena.ismael.timeline.pokemon.screens.pokemondetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pena.ismael.timeline.pokemon.model.Pokemon
import com.pena.ismael.timeline.pokemon.screens.pokemondetail.component.ElementRow
import com.pena.ismael.timeline.pokemon.screens.pokemondetail.component.StatSection

@Composable
fun PokemonDetailScreen(
    navController: NavHostController,
    viewModel: PokemonDetailViewModel = hiltViewModel(),
    windowSizeClass: WindowSizeClass
) {
    val pokemonState = viewModel.pokemon.collectAsStateWithLifecycle()
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            PortraitView(pokemon = pokemonState.value)
        }
        WindowWidthSizeClass.Medium -> {
            LandscapeView(pokemon = pokemonState.value)
        }
        WindowWidthSizeClass.Expanded -> {
            LandscapeView(pokemon = pokemonState.value)
        }
    }
    BoxWithConstraints(Modifier.fillMaxSize()) {
        if (maxWidth < 600.dp) {
            PortraitView(pokemonState.value)
        } else {
            LandscapeView(pokemon = pokemonState.value)
        }
    }

}

@Composable
fun PortraitView(pokemon: Pokemon?) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
    ) {
        DetailHeader(pokemon = pokemon)
        Spacer(modifier = Modifier.height(16.dp))
        StatSection(pokemon = pokemon)
    }
}

@Composable
fun LandscapeView(pokemon: Pokemon?) {
    Row(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        DetailHeader(pokemon = pokemon)
        StatSection(pokemon = pokemon)
    }
}

@Composable
fun DetailHeader(pokemon: Pokemon?, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = pokemon?.name.toString().replaceFirstChar { char: Char -> char.uppercaseChar() },
            style = MaterialTheme.typography.headlineMedium,
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon?.id}.png")
                .crossfade(true)
                .build(),            contentDescription = pokemon?.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        ElementRow(pokemon = pokemon)
    }
}