package com.pena.ismael.timeline.pokemon.screens.pokemonlist.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pena.ismael.timeline.R
import com.pena.ismael.timeline.core.compose.preview.DarkLightPreview
import com.pena.ismael.timeline.core.compose.preview.DarkLightThemeSurface
import com.pena.ismael.timeline.pokemon.model.Pokemon

@Composable
fun PokemonListItem(
    pokemonListItem: Pokemon,
    onClick: () -> Unit = {}
) {
    PokemonListItemRaw(
        pokemonName = pokemonListItem.name,
        imageUrl = pokemonListItem.imageUrl,
        onClick = {
            onClick()
        }
    )
}

@Composable
fun PokemonListItemRaw(
    pokemonName: String,
    imageUrl: String,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .align(Alignment.Center)
        )
        val modifier = remember {
            Modifier
                .size(128.dp)
                .align(Alignment.BottomCenter)
        }
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .placeholder(R.drawable.pidgeotto)
                .crossfade(true)
                .build(),
            contentDescription = pokemonName,
            contentScale = ContentScale.Fit,
            modifier = modifier
        )
        Text(
            text = pokemonName,
            Modifier.align(Alignment.BottomCenter)
        )
    }
}

@DarkLightPreview
@Composable
fun PreviewPokemonListItem() {
    DarkLightThemeSurface {
        val pokemon = Pokemon(id = 17, name = "Pidgeotto")
        PokemonListItem(pokemonListItem = pokemon)
    }
}


