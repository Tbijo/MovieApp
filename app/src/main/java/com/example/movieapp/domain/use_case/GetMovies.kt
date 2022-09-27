package com.example.movieapp.domain.use_case

import com.example.movieapp.domain.model.MovieItemModel
import com.example.movieapp.domain.model.toMovieItemModel
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovies(
    private val movieRepository: MovieRepository
) {

    operator fun invoke(): Flow<List<MovieItemModel>> {
        return movieRepository.getMovies().map { list->
            list.map { it.toMovieItemModel() }
        }
    }
}