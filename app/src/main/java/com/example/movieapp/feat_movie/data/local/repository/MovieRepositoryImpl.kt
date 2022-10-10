package com.example.movieapp.feat_movie.data.local.repository

import com.example.movieapp.feat_movie.data.local.model.Movie
import com.example.movieapp.feat_movie.data.local.MovieDao
import com.example.movieapp.feat_movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val movieDao: MovieDao
): MovieRepository {

    override suspend fun insertMovies(movies: List<Movie>) {
        movieDao.insertMovies(movies)
    }

    override suspend fun updateMovie(movieId: Long, filePath: String) {
        movieDao.updateMovie(movieId, filePath)
    }

    override fun getMovies(): Flow<List<Movie>> {
        return movieDao.getMovies()
    }

    override fun getMoviesByName(name: String): Flow<List<Movie>> {
        return movieDao.getMoviesByName(name)
    }

}