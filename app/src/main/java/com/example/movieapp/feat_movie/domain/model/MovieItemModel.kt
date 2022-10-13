package com.example.movieapp.feat_movie.domain.model

import com.example.movieapp.feat_movie.data.local.model.Movie

data class MovieItemModel(
    val id: Long,
    val name: String?,
    val iconUri: String?,
    val manifestUri: String?,
    val drm: List<String?>?,
    val filePath: String?
)

fun Movie.toMovieItemModel(): MovieItemModel {
    return MovieItemModel(
        id = id ?: 0,
        name = name,
        iconUri = iconUri,
        manifestUri = manifestUri,
        drm = drm,
        filePath = filePath
    )
}