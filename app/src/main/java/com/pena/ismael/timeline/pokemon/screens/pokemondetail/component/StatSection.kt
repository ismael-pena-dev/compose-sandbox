package com.pena.ismael.timeline.pokemon.screens.pokemondetail.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pena.ismael.timeline.core.compose.preview.DarkLightPreview
import com.pena.ismael.timeline.core.compose.preview.DarkLightThemeSurface
import com.pena.ismael.timeline.pokemon.model.Pokemon

@Composable
fun StatSection(pokemon: Pokemon?) {
    StatSection(
        hp = pokemon?.hp ?: 0,
        atk = pokemon?.atk ?: 0,
        def = pokemon?.def ?: 0,
        spAtk = pokemon?.spAtk ?: 0,
        spDef = pokemon?.spDef ?: 0,
        spd = pokemon?.spd ?: 0,
    )
}

@Composable
fun StatSection(hp: Int, atk: Int, def: Int, spAtk: Int, spDef: Int, spd: Int) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        StatRow("HP: $hp", hp, MaterialTheme.colorScheme.primary)
        StatRow("ATK: $atk", atk, MaterialTheme.colorScheme.primary)
        StatRow("SP ATK: $spAtk", spAtk, MaterialTheme.colorScheme.primary)
        StatRow("DEF: $def", def, MaterialTheme.colorScheme.primary)
        StatRow("SP DEF: $spDef", spDef, MaterialTheme.colorScheme.primary)
        StatRow("SPD: $spd", spd, MaterialTheme.colorScheme.primary)
    }
}

@Composable
fun StatRow(label: String, value: Int, color: Color) {
    val maxStat: Int by remember {
        mutableIntStateOf(255)
    }
    val animateFill = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = label) {
        val targetFill = (value.toFloat() / maxStat)
        animateFill.animateTo(
            targetValue = targetFill,
            animationSpec = tween(700, 200)
        )
    }
    StatRowRaw(label = label, color = color, fillPercentage = animateFill.value)
}

@Composable
fun StatRowRaw(label: String, color: Color, fillPercentage: Float) {
    Column {
        Text(text = label)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))) {
                Box(modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(24.dp)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant
                    )
                )
                Box(modifier = Modifier
                    .fillMaxWidth(fillPercentage)
                    .height(24.dp)
                    .background(
                        color
                    ),
                )
            }
        }
    }
}

@DarkLightPreview
@Composable
fun PreviewStatSection() {
    DarkLightThemeSurface {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            StatRowRaw("HP: 100", MaterialTheme.colorScheme.primary, 100F/255)
            StatRowRaw("ATK: 150", MaterialTheme.colorScheme.primary, 150F/255)
            StatRowRaw("SP ATK: 70", MaterialTheme.colorScheme.primary, 70F/255)
            StatRowRaw("DEF: 40", MaterialTheme.colorScheme.primary, 40F/255)
            StatRowRaw("SP DEF: 90", MaterialTheme.colorScheme.primary, 90F/255)
            StatRowRaw("SPD: 120", MaterialTheme.colorScheme.primary, 120F/255)
        }
    }
}