package com.pena.ismael.timeline.pokemon.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pena.ismael.timeline.pokemon.screens.PokemonContentType.Companion.toPokemonContentType
import com.pena.ismael.timeline.pokemon.screens.pokemondetail.PokemonDetailScreen
import com.pena.ismael.timeline.pokemon.screens.pokemonlist.PokemonListAndDetailScreen
import com.pena.ismael.timeline.pokemon.screens.pokemonlist.PokemonListScreen

@Composable
fun PokemonNavHost(
    navController: NavHostController,
    widthSizeClass: WindowWidthSizeClass,
    heightSizeClass: WindowHeightSizeClass,
) {
    val contentType = widthSizeClass.toPokemonContentType()
    when (contentType) {
        PokemonContentType.LIST_ONLY -> {
            SinglePanePokemonNavHost(
                navController = navController,
                widthSizeClass = widthSizeClass,
                heightSizeClass = heightSizeClass,
            )
        }
        PokemonContentType.LIST_AND_DETAIL -> {
            DoublePanePokemonNavHost(
                navController = navController,
                widthSizeClass = widthSizeClass,
                heightSizeClass = heightSizeClass,
            )
        }
    }

}

@Composable
fun SinglePanePokemonNavHost(
    navController: NavHostController,
    widthSizeClass: WindowWidthSizeClass,
    heightSizeClass: WindowHeightSizeClass,
) {
    val animationSpeed by remember {
        mutableIntStateOf(700)
    }
    NavHost(
        navController = navController,
        startDestination = "pokemon/",
    ) {
        composable(
            route = "pokemon/",
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
        ) { backStack ->
            PokemonListScreen(
                navController = navController,
                widthSizeClass = widthSizeClass,
                heightSizeClass = heightSizeClass,
                onPokemonClick = { pokemon ->
                    navController.navigate("pokemon/${pokemon.id}")
                },
                modifier = Modifier.fillMaxWidth(2F),
            )
        }
        composable(
            route = "pokemon/{id}",
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
        ) { backStack ->
            BackHandler {
                if (navController.previousBackStackEntry == null) {
                    navController.navigate(route = "pokemon/")
                } else {
                    navController.popBackStack()
                }
            }
            PokemonDetailScreen(
                navController = navController,
                widthSizeClass = widthSizeClass,
                heightSizeClass = heightSizeClass,
            )
        }
    }
}



@Composable
fun DoublePanePokemonNavHost(
    navController: NavHostController,
    widthSizeClass: WindowWidthSizeClass,
    heightSizeClass: WindowHeightSizeClass,
) {
    NavHost(
        navController = navController,
        startDestination = "pokemon/1",
        modifier = Modifier.fillMaxSize()
    ) {
        // Declares both "pokemon/" and "pokemon/{id}" to transition backstack when rotating device
        composable(
            route = "pokemon/",
        ) { backStack ->
            PokemonListAndDetailScreen(
                navController = navController,
                widthSizeClass = widthSizeClass,
                heightSizeClass = heightSizeClass
            )
        }
        composable(
            route = "pokemon/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStack ->
            PokemonListAndDetailScreen(
                navController = navController,
                widthSizeClass = widthSizeClass,
                heightSizeClass = heightSizeClass
            )
        }
    }
}

enum class PokemonContentType {
    LIST_ONLY, LIST_AND_DETAIL;

    companion object {
        fun WindowWidthSizeClass.toPokemonContentType(): PokemonContentType {
            return when (this@toPokemonContentType) {
                WindowWidthSizeClass.Expanded, WindowWidthSizeClass.Medium -> {
                    LIST_AND_DETAIL
                }
                else -> {
                    LIST_ONLY
                }
            }
        }
    }
}