package com.example.movieapp.data.remote.repository

import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.data.remote.dto.MovieApiDto
import com.example.movieapp.domain.repository.MovieApiRepository
import javax.inject.Inject

class MovieApiRepositoryImpl @Inject constructor(
    private val api: MovieApi
): MovieApiRepository {

    override suspend fun getMovies(): List<MovieApiDto> {
        return api.getMovies()
    }
}