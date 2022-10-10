package com.example.movieapp.feat_movie.domain.repository

import com.example.movieapp.feat_movie.data.local.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun insertMovies(movies: List<Movie>)

    suspend fun updateMovie(movieId: Long, filePath: String)

    fun getMovies(): Flow<List<Movie>>

    fun getMoviesByName(name: String): Flow<List<Movie>>
}