package com.pena.ismael.timeline.pokemon.model

enum class Element(val color: Long) {
    NORMAL(0xFFA8A77A),
    FIRE(0xFFEE8130),
    WATER(0xFF6390F0),
    GRASS(0xFF7AC74C),
    FLYING(0xFFA98FF3),
    FIGHTING(0xFFC22E28),
    POISON(0xFFA33EA1),
    ELECTRIC(0xFFF7D02C),
    GROUND(0xFFE2BF65),
    ROCK(0xFFB6A136),
    PSYCHIC(0xFFF95587),
    ICE(0xFF96D9D6),
    BUG(0xFFA6B91A),
    GHOST(0xFF735797),
    STEEL(0xFFB7B7CE),
    DRAGON(0xFF6F35FC),
    DARK(0xFF705746),
    FAIRY(0xFFD685AD);

    override fun toString(): String {
        return super
            .toString()
            .lowercase()
            .replaceFirstChar { it.uppercaseChar() }
    }
}

fun String?.toElement(): Element? {
    val elements = Element.values()
    return elements.firstOrNull {
        it.name.equals(this, ignoreCase = true)
    }
}