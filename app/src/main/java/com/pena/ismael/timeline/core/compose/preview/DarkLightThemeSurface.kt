package com.pena.ismael.timeline.core.compose.preview

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.pena.ismael.timeline.ui.theme.TimelineTheme

@Composable
fun DarkLightThemeSurface(
    content: @Composable () -> Unit = {},
) {
    TimelineTheme {
        Surface {
            content()
        }
    }
}