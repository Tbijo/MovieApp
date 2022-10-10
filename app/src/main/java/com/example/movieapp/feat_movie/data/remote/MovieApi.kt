package com.example.movieapp.feat_movie.data.remote

import com.example.movieapp.feat_movie.data.remote.dto.MovieApiDto
import retrofit2.http.GET

interface MovieApi {

    @GET("/nextsux/f6e0327857c88caedd2dab13affb72c1/raw/04441487d90a0a05831835413f5942d58026d321/videos.json")
    suspend fun getMovies(): List<MovieApiDto>

}