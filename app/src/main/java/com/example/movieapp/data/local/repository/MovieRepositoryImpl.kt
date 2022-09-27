package com.example.movieapp.data.local.repository

import com.example.movieapp.data.local.model.Movie
import com.example.movieapp.data.local.MovieDao
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val movieDao: MovieDao
): MovieRepository {

    override suspend fun insertMovies(movies: List<Movie>) {
        movieDao.insertMovies(movies)
    }

    override fun getMovies(): Flow<List<Movie>> {
        return movieDao.getMovies()
    }

    override fun getMoviesByName(name: String): Flow<List<Movie>> {
        return movieDao.getMoviesByName(name)
    }

}