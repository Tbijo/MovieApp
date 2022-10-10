package com.example.movieapp.feat_movie.domain.model

import com.example.movieapp.feat_movie.data.local.model.Movie

data class MovieItemModel(
    val name: String?,
    val iconUri: String?,
    val manifestUri: String?,
    val drm: List<String?>?,
)

fun Movie.toMovieItemModel(): MovieItemModel {
    return MovieItemModel(
        name = name,
        iconUri = iconUri,
        manifestUri = manifestUri,
        drm = drm
    )
}