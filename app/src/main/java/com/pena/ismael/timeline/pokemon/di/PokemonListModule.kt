package com.pena.ismael.timeline.pokemon.di

import android.content.Context
import com.pena.ismael.timeline.pokemon.repository.local.PokemonLocalDataSource
import com.pena.ismael.timeline.pokemon.repository.local.PokemonLocalDataSourceRoomImpl
import com.pena.ismael.timeline.pokemon.repository.local.room.PokemonDao
import com.pena.ismael.timeline.pokemon.repository.local.room.PokemonDatabase
import com.pena.ismael.timeline.pokemon.repository.remote.PokemonApi
import com.pena.ismael.timeline.pokemon.repository.remote.PokemonRemoteDataSource
import com.pena.ismael.timeline.pokemon.repository.remote.PokemonRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
abstract class PokemonListModule {

    @Binds
    abstract fun bindsPokemonRemoteDataSource(
        impl: PokemonRemoteDataSourceImpl
    ): PokemonRemoteDataSource

    @Binds
    abstract fun bindsPokemonLocalDataSource(
        impl: PokemonLocalDataSourceRoomImpl
    ): PokemonLocalDataSource

    companion object {
        @Provides
        @Named("pokemon")
        fun provideRetrofit(): Retrofit {
            return PokemonApi.buildRetrofit()
        }

        @Provides
        fun providePokemonApi(
            @Named("pokemon") retrofit: Retrofit
        ): PokemonApi {
            return retrofit.create(PokemonApi::class.java)
        }

        @Provides
        fun providePokemonDatabase(
            @ApplicationContext context: Context
        ): PokemonDatabase {
            return PokemonDatabase.build(context)
        }

        @Provides
        fun providePokemonDao(
            pokemonDatabase: PokemonDatabase
        ): PokemonDao {
            return pokemonDatabase.pokemonDao()
        }
    }
}