package com.pena.ismael.timeline.pokemon.repository.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pena.ismael.timeline.pokemon.repository.local.entity.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = true)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    companion object {
        const val POKEMON_DATABASE = "pokemondb"
        fun build(context: Context): PokemonDatabase {
            return Room.databaseBuilder(context, PokemonDatabase::class.java, POKEMON_DATABASE).build()
        }
    }
}