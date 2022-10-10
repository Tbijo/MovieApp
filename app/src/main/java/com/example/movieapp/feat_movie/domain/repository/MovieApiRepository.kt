package com.example.movieapp.feat_movie.domain.repository

import com.example.movieapp.feat_movie.data.remote.dto.MovieApiDto

interface MovieApiRepository {

    suspend fun getMovies(): List<MovieApiDto>
}