package com.pena.ismael.timeline.pokemon.screens.pokemondetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pena.ismael.timeline.core.compose.preview.DarkLightPreview
import com.pena.ismael.timeline.core.compose.preview.DarkLightPreviewUI
import com.pena.ismael.timeline.core.compose.preview.DarkLightThemeSurface
import com.pena.ismael.timeline.pokemon.model.Element
import com.pena.ismael.timeline.pokemon.model.Pokemon
import com.pena.ismael.timeline.pokemon.model.toElement

@Composable
fun ElementRow(pokemon: Pokemon?) {
    val elements = listOfNotNull(
        pokemon?.elementOne.toElement(),
        pokemon?.elementTwo.toElement(),
    )
    ElementRow(elements = elements)
}

@Composable
fun ElementRow(elements: List<Element>) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        elements.forEach { element ->
            Text(
                text = element.toString(),
                modifier = Modifier
                    .background(color = Color(element.color), shape = RoundedCornerShape(8.dp))
                    .padding(vertical = 8.dp)
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

@DarkLightPreview
@Composable
fun PreviewElementRow() {
    DarkLightThemeSurface {
        Column(Modifier.padding(32.dp), verticalArrangement = Arrangement.spacedBy(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            ElementRow(elements = listOf(Element.NORMAL))
            ElementRow(elements = listOf(Element.FIRE))
            ElementRow(elements = listOf(Element.WATER, Element.GRASS))
            ElementRow(elements = listOf(Element.FLYING, Element.FIGHTING))
            ElementRow(elements = listOf(Element.POISON, Element.ELECTRIC))
            ElementRow(elements = listOf(Element.GROUND, Element.ROCK))
            ElementRow(elements = listOf(Element.PSYCHIC, Element.ICE))
            ElementRow(elements = listOf(Element.BUG, Element.GHOST))
            ElementRow(elements = listOf(Element.STEEL, Element.DRAGON))
            ElementRow(elements = listOf(Element.DARK, Element.FAIRY))
        }
    }
}