package com.example.movieapp.feat_movie.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.movieapp.feat_movie.presentation.movie_list.MovieListScreen
import com.example.movieapp.feat_movie.presentation.movie_player.MoviePlayerScreen
import com.example.movieapp.feat_movie.presentation.util.Screens

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun SetUpNavGraph(navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = Screens.MovieListScreen.route
    ) {
        composable(
            route = Screens.MovieListScreen.route
        ) {
            MovieListScreen(navController = navHostController)
        }

        composable(
            route = Screens.MoviePlayerScreen.route + "?movieUri={movieUri}",
            arguments = listOf(
                navArgument(name = "movieUri") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val uri = it.arguments?.getString("movieUri") ?: ""
            MoviePlayerScreen(uri)
        }
    }
}