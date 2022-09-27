package com.example.movieapp.domain.use_case

data class MovieUseCases(
    val getMoviesApi: GetMoviesApi,
    val addMovies: AddMovies,
    val getMovies: GetMovies,
    val getMoviesByName: GetMoviesByName
)