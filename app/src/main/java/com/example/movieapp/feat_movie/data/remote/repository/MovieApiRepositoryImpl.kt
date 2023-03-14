package com.example.movieapp.feat_movie.data.remote.repository

import com.example.movieapp.feat_movie.data.remote.MovieApi
import com.example.movieapp.feat_movie.data.remote.dto.MovieApiDto
import com.example.movieapp.feat_movie.domain.repository.MovieApiRepository

class MovieApiRepositoryImpl(
    private val api: MovieApi
): MovieApiRepository {

    override suspend fun getMovies(): List<MovieApiDto> {
        return api.getMovies()
    }
}