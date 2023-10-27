package com.pena.ismael.timeline.core.compose.preview

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@PhonePreview
@FoldablePreview
@TabletPreview
annotation class DevicePreview

@Preview(widthDp = 411, heightDp = 891, name = "Phone Portrait")
@Preview(widthDp = 891, heightDp = 411, name = "Phone Landscape")
annotation class PhonePreview

@Preview(widthDp = 673, heightDp = 841, name = "Foldable Portrait")
@Preview(widthDp = 841, heightDp = 673, name = "Foldable Landscape")
annotation class FoldablePreview

@Preview(widthDp = 800, heightDp = 1280, name = "Tablet Portrait")
@Preview(widthDp = 1280, heightDp = 800, name = "Tablet Landscape")
annotation class TabletPreview
