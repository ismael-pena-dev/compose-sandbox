package com.pena.ismael.timeline.pokemon.screens.pokemonlist.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
    onPokemonClick: (pokemon: Pokemon) -> Unit = {},
    onFetchMore: () -> Unit = {},
    onClearCache: () -> Unit = {},
) {
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
        items(pokemonList) { pokemonListItem ->
            PokemonListItem(pokemonListItem = pokemonListItem, onClick = {
                onPokemonClick(pokemonListItem)
            })
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

@DarkLightPreviewUI
@Composable
fun PreviewPokemonList() {
    DarkLightThemeSurface {
        val pokemonList = mutableListOf<Pokemon>()
        repeat(10) {
            pokemonList.add(Pokemon(id = (1..100).random(), name = "Pidgeotto"))
        }
        PokemonList(pokemonList = pokemonList)
    }
}