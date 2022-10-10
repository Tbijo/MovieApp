package com.example.movieapp.feat_movie.data.local.repository

import com.example.movieapp.feat_movie.data.local.model.Movie
import com.example.movieapp.feat_movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestRepository: MovieRepository {

    // test data
    private val movieList = mutableListOf<Movie>()

    override suspend fun insertMovies(movies: List<Movie>) {
        movieList.addAll(movies)
    }

    override fun getMovies(): Flow<List<Movie>> {
        return flow {
            emit(movieList)
        }
    }

    override fun getMoviesByName(name: String): Flow<List<Movie>> {
        return flow {
            val filteredMovies = movieList.filter { it.name?.contains(name) ?: false }
            emit(filteredMovies)
        }
    }
}