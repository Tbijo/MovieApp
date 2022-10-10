package com.example.movieapp.feat_movie.data.remote.dto


import com.google.gson.annotations.SerializedName

data class LicenseRequestHeaders(
    @SerializedName("X-AxDRM-Message")
    val xAxDRMMessage: String?
)