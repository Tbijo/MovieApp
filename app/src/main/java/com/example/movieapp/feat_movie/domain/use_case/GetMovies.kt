package com.example.movieapp.feat_movie.domain.use_case

import com.example.movieapp.feat_movie.domain.model.MovieItemModel
import com.example.movieapp.feat_movie.domain.model.toMovieItemModel
import com.example.movieapp.feat_movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMovies(
    private val movieRepository: MovieRepository
) {

    operator fun invoke(): Flow<List<MovieItemModel>> {
        return movieRepository.getMovies().map { list->
            list.map { it.toMovieItemModel() }
        }
    }
}