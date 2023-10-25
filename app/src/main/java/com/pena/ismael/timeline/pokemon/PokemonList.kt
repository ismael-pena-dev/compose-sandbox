package com.pena.ismael.timeline.pokemon

sealed class FetchStatus {
    data object Loading: FetchStatus()
    data object Idle: FetchStatus()
}


