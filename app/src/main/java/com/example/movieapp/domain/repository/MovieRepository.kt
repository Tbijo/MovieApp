package com.example.movieapp.domain.repository

import com.example.movieapp.data.local.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun insertMovies(movies: List<Movie>)

    fun getMovies(): Flow<List<Movie>>
}