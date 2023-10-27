@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class,
    ExperimentalMaterial3WindowSizeClassApi::class
)

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pena.ismael.timeline.R
import com.pena.ismael.timeline.core.compose.preview.DarkLightThemeSurface
import com.pena.ismael.timeline.core.compose.preview.PhonePreview
import com.pena.ismael.timeline.pokemon.model.Pokemon
import com.pena.ismael.timeline.pokemon.screens.PokemonContentType
import com.pena.ismael.timeline.pokemon.screens.PokemonContentType.Companion.toPokemonContentType
import com.pena.ismael.timeline.pokemon.screens.pokemondetail.component.ElementRow
import com.pena.ismael.timeline.pokemon.screens.pokemondetail.component.StatSection

@Composable
fun PokemonDetailScreen(
    navController: NavHostController,
    viewModel: PokemonDetailViewModel = hiltViewModel(),
    selectedPokemonId: Int? = null, // For detail + list combined view only
    widthSizeClass: WindowWidthSizeClass,
    heightSizeClass: WindowHeightSizeClass,
) {
    selectedPokemonId?.let {
        viewModel.fetchPokemonDetails(it)
    }
    val pokemonState = viewModel.pokemon.collectAsStateWithLifecycle()
    PokemonDetailScreenRaw(
        pokemon = pokemonState.value,
        widthSizeClass = widthSizeClass,
        heightSizeClass = heightSizeClass
    )
}

@Composable
fun PokemonDetailScreenRaw(
    pokemon: Pokemon?,
    widthSizeClass: WindowWidthSizeClass,
    heightSizeClass: WindowHeightSizeClass,
) {
    Surface(
        Modifier.verticalScroll(rememberScrollState())
    ) {
        val type = widthSizeClass.toPokemonContentType()
        when (type) {
            PokemonContentType.LIST_ONLY -> {
                BoxWithConstraints(
                    Modifier
                        .fillMaxSize()
                        .padding(vertical = 32.dp)) {
                    if (maxWidth < 600.dp) {
                        PortraitView(pokemon = pokemon)
                    } else {
                        LandscapeView(pokemon = pokemon)
                    }
                }
            }
            PokemonContentType.LIST_AND_DETAIL -> {
                PortraitView(pokemon = pokemon)
            }
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
                .placeholder(R.drawable.pidgeotto)
                .build(),
            contentDescription = pokemon?.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        ElementRow(pokemon = pokemon)
    }
}

@PhonePreview
@Composable
fun PreviewPokemonDetailScreen() {
    val configuration = LocalConfiguration.current
    val windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(configuration.screenWidthDp.dp, configuration.screenHeightDp.dp))
    DarkLightThemeSurface {
        PokemonDetailScreenRaw(
            pokemon = Pokemon(1, "Pidgeotto", "Normal", "Flying", 23, 2, 63, 60, 50, 55, 50, 71),
            widthSizeClass = windowSizeClass.widthSizeClass,
            heightSizeClass = windowSizeClass.heightSizeClass,
        )
    }
}