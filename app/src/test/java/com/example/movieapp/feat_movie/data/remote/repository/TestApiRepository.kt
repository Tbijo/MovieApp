package com.example.movieapp.feat_movie.data.remote.repository

import com.example.movieapp.feat_movie.data.local.model.Movie
import com.example.movieapp.feat_movie.data.remote.dto.MovieApiDto
import com.example.movieapp.feat_movie.domain.repository.MovieApiRepository

class TestApiRepository: MovieApiRepository {

    // test data
    private val movieList = mutableListOf<Movie>()

    override suspend fun getMovies(): List<MovieApiDto> {
        TODO("Not yet implemented")
    }
}