package com.example.movieapp.domain.use_case

import com.example.movieapp.data.local.model.toMovie
import com.example.movieapp.data.remote.dto.MovieApiDto
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class AddMovies(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(movies: List<MovieApiDto>) {
        movieRepository.insertMovies(movies.map { it.toMovie() })
    }
}