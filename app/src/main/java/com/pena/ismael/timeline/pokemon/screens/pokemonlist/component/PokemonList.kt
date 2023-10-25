package com.pena.ismael.timeline.pokemon.screens.pokemonlist.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pena.ismael.timeline.R
import com.pena.ismael.timeline.core.compose.preview.DarkLightPreview
import com.pena.ismael.timeline.core.compose.preview.DarkLightPreviewUI
import com.pena.ismael.timeline.core.compose.preview.DarkLightThemeSurface
import com.pena.ismael.timeline.pokemon.model.Pokemon

@Composable
fun PokemonList(
    pokemonList: List<Pokemon>,
    onPokemonClick: (pokemon: Pokemon) -> Unit,
    onFetchMore: () -> Unit,
    onClearCache: () -> Unit
) {
    PokemonListRaw(
        pokemonList = pokemonList,
        onFetchMore = onFetchMore,
        onClearCache = onClearCache,
        pokemonListItemComposable = { pokemonListItem ->
            PokemonListItem(
                pokemonListItem = pokemonListItem,
                onClick = {
                    onPokemonClick(pokemonListItem)
                }
            )
        }
    )
}

@Composable
fun <T> PokemonListRaw(
    pokemonList: List<T>,
    onFetchMore: () -> Unit,
    onClearCache: () -> Unit,
    pokemonListItemComposable: @Composable (pokemonListItem: T) -> Unit,
) {
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
        items(pokemonList) { pokemonListItem ->
            pokemonListItemComposable(pokemonListItem)
        }
        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
            Spacer(modifier = Modifier.height(1.dp))
        }
        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
            Button(onClick = onFetchMore, Modifier.padding(horizontal = 8.dp)) {
                Text(text = "Load more")
            }
        }
        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
            Button(onClick = onClearCache, Modifier.padding(horizontal = 8.dp)) {
                Text(text = "Clear cache")
            }
        }
    }
}

@DarkLightPreviewUI()
@Preview(device = Devices.TABLET, showBackground = true, showSystemUi = true)
@Composable
fun PreviewPokemonLis() {
    DarkLightThemeSurface {
        val pokemonList = mutableListOf<String>()
        repeat(10) {
            pokemonList.add("Pidgeotto")
        }
        PokemonListRaw(
            pokemonList = pokemonList,
            onFetchMore = { },
            onClearCache = { },
            pokemonListItemComposable = { pokemon ->
                PokemonListItemRaw(pokemonName = pokemon, image = { modifier ->
                    Image(painterResource(id = R.drawable.pidgeotto), contentDescription = pokemon, modifier = modifier)
                })
            }
        )
    }
}