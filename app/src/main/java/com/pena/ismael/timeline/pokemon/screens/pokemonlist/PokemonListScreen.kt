@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package com.pena.ismael.timeline.pokemon.screens.pokemonlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import com.pena.ismael.timeline.core.compose.preview.DarkLightThemeSurface
import com.pena.ismael.timeline.core.compose.preview.DevicePreview
import com.pena.ismael.timeline.pokemon.model.Pokemon
import com.pena.ismael.timeline.pokemon.screens.PokemonContentType
import com.pena.ismael.timeline.pokemon.screens.PokemonContentType.Companion.toPokemonContentType
import com.pena.ismael.timeline.pokemon.screens.pokemondetail.PokemonDetailScreen
import com.pena.ismael.timeline.pokemon.screens.pokemondetail.PokemonDetailScreenRaw
import com.pena.ismael.timeline.pokemon.screens.pokemonlist.component.PokemonList

@Composable
fun PokemonListAndDetailScreen(
    navController: NavHostController,
    widthSizeClass: WindowWidthSizeClass,
    heightSizeClass: WindowHeightSizeClass,
) {
    val selectedPokemonId = remember {
        mutableStateOf<Int?>(null)
    }
    Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
        PokemonListScreen(
            navController = navController,
            widthSizeClass = widthSizeClass,
            heightSizeClass = heightSizeClass,
            onPokemonClick = { pokemon ->
                selectedPokemonId.value = pokemon.id
                val argument = NavArgument.Builder()
                    .setType(NavType.IntType)
                    .setDefaultValue(pokemon.id)
                    .build()
                navController.currentDestination?.addArgument("id", argument)
            },
            modifier = Modifier.fillMaxWidth(.5F),
        )
        PokemonDetailScreen(
            navController = navController,
            widthSizeClass = widthSizeClass,
            heightSizeClass = heightSizeClass,
            selectedPokemonId = selectedPokemonId.value,
        )
    }
}

@Composable
fun PokemonListScreen(
    navController: NavHostController,
    viewModel: PokemonListViewModel = hiltViewModel(),
    widthSizeClass: WindowWidthSizeClass,
    heightSizeClass: WindowHeightSizeClass,
    onPokemonClick: (Pokemon) -> Unit = { },
    modifier: Modifier = Modifier,
) {
    val pokemons = viewModel.pokemonList.collectAsState().value
    PokemonListScreenRaw(
        pokemons = pokemons,
        onPokemonClick = onPokemonClick,
        onFetchMore = {
            viewModel.fetchMore()
        },
        onClearCache = {
            viewModel.clearCache(navController.context)
        },
        modifier = modifier,
    )
}

@Composable
fun PokemonListScreenRaw(
    pokemons: List<Pokemon>,
    onPokemonClick: (Pokemon) -> Unit = {},
    onFetchMore: () -> Unit = {},
    onClearCache: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        PokemonList(pokemons,
            onPokemonClick = onPokemonClick,
            onFetchMore = onFetchMore,
            onClearCache = onClearCache
        )
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@DevicePreview
@Composable
fun PreviewPokemonListScreen() {
    DarkLightThemeSurface {
        val configuration = LocalConfiguration.current
        val windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(configuration.screenWidthDp.dp, configuration.screenHeightDp.dp))
        val type = windowSizeClass.widthSizeClass.toPokemonContentType()
        val pokemonList = listOf(
            Pokemon(id = 1, "Pidgeotto"),
            Pokemon(id = 1, "Pidgeotto"),
            Pokemon(id = 1, "Pidgeotto"),
            Pokemon(id = 1, "Pidgeotto"),
            Pokemon(id = 1, "Pidgeotto"),
            Pokemon(id = 1, "Pidgeotto"),
            Pokemon(id = 1, "Pidgeotto"),
            Pokemon(id = 1, "Pidgeotto"),
            Pokemon(id = 1, "Pidgeotto"),
        )
        when (type) {
            PokemonContentType.LIST_ONLY -> {
                PokemonListScreenRaw(
                    pokemons = pokemonList,
                    modifier = Modifier.fillMaxWidth(.5F),
                )
            }
            PokemonContentType.LIST_AND_DETAIL -> {
                Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    PokemonListScreenRaw(
                        pokemons = pokemonList,
                        modifier = Modifier.fillMaxWidth(.5F),
                    )
                    PokemonDetailScreenRaw(
                        pokemon = Pokemon(1, "Pidgeotto", "Normal", "Flying", 23, 2, 63, 60, 50, 55, 50, 71),
                        widthSizeClass = windowSizeClass.widthSizeClass,
                        heightSizeClass = windowSizeClass.heightSizeClass,
                    )
                }
            }
        }
    }
}

