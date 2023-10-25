package com.pena.ismael.timeline.pokemon.screens

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pena.ismael.timeline.pokemon.screens.pokemondetail.PokemonDetailScreen
import com.pena.ismael.timeline.pokemon.screens.pokemonlist.PokemonListScreen

@Composable
fun PokemonNavHost(navController: NavHostController) {
    val animationSpeed by remember {
        mutableIntStateOf(700)
    }
    NavHost(
        navController = navController,
        startDestination = "pokemonlist",
    ) {
        composable(
            route = "pokemonlist",
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = animationSpeed)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = animationSpeed)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = animationSpeed)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = animationSpeed)
                )
            },
        ) {
            PokemonListScreen(
                navController = navController,
            )
        }
        composable(route = "pokemon/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            ),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = animationSpeed)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = animationSpeed)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = animationSpeed)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = animationSpeed)
                )
            },
        ) {
            PokemonDetailScreen(
                navController = navController
            )
        }
    }
}