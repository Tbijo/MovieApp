package com.example.movieapp.feat_movie.data.remote.repository

import com.example.movieapp.feat_movie.data.remote.dto.MovieApiDto
import com.example.movieapp.feat_movie.domain.repository.MovieApiRepository

class TestApiRepository: MovieApiRepository {

    // test data
    private val movieList = mutableListOf<MovieApiDto>(
        MovieApiDto(
            name = "Bunny",
            iconUri = "https://storage.googleapis.com/shaka-asset-icons/dark_truth.png",
            manifestUri = "https://storage.googleapis.com/shaka-demo-assets/bbb-dark-truths/dash.mpd"
        ),
        MovieApiDto(
            name = "Angel",
            iconUri = "https://storage.googleapis.com/shaka-asset-icons/angel_one.png",
            manifestUri = "https://storage.googleapis.com/shaka-demo-assets/angel-one/dash.mpd"
        )
    )

    override suspend fun getMovies(): List<MovieApiDto> {
        return movieList
    }
}