package com.example.movieapp.presentation.util

sealed class Screens(val route: String) {
    object MovieListScreen: Screens("movie_list_screen")
    object MoviePlayerScreen: Screens("movie_player")
}