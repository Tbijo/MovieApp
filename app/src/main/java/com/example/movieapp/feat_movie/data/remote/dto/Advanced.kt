package com.example.movieapp.feat_movie.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Advanced(
    @SerializedName("com.widevine.alpha")
    val comWidevineAlpha: ComWidevineAlpha?
)