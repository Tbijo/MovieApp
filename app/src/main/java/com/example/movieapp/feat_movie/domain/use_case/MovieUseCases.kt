package com.example.movieapp.feat_movie.domain.use_case

data class MovieUseCases(
    val getMoviesApi: GetMoviesApi,
    val addMovies: AddMovies,
    val updateMovie: UpdateMovie,
    val getMovies: GetMovies,
    val getMoviesByName: GetMoviesByName
)