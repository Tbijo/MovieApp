package com.example.movieapp.feat_movie.domain.use_case

import com.example.movieapp.feat_movie.domain.repository.MovieRepository

class UpdateMovie(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(movieId: Long, filePath: String) {
        movieRepository.updateMovie(movieId, filePath)
    }
}