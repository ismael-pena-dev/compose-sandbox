package com.pena.ismael.timeline.pokemon.repository.remote.dto.detail

data class VersionGroupDetail(
    val level_learned_at: Int,
    val move_learn_method: com.pena.ismael.timeline.pokemon.repository.remote.dto.detail.MoveLearnMethod,
    val version_group: com.pena.ismael.timeline.pokemon.repository.remote.dto.detail.VersionGroup
)