package com.example.movieapp.feat_movie.domain.use_case

import com.example.movieapp.feat_movie.data.local.model.toMovie
import com.example.movieapp.feat_movie.data.remote.dto.MovieApiDto
import com.example.movieapp.feat_movie.domain.repository.MovieRepository

class AddMovies(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(movies: List<MovieApiDto>) {
        movieRepository.insertMovies(movies.map { it.toMovie() })
    }
}