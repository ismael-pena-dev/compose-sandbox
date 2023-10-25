package com.pena.ismael.timeline.pokemon.repository.remote.dto.detail

data class Move(
    val move: com.pena.ismael.timeline.pokemon.repository.remote.dto.detail.MoveX,
    val version_group_details: List<com.pena.ismael.timeline.pokemon.repository.remote.dto.detail.VersionGroupDetail>
)