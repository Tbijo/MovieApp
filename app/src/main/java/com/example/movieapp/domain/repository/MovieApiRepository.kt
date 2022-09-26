package com.example.movieapp.domain.repository

import com.example.movieapp.data.remote.dto.MovieApiDto

interface MovieApiRepository {

    suspend fun getMovies(): List<MovieApiDto>
}