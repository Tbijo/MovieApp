package com.example.movieapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Advanced(
    @SerializedName("com.widevine.alpha")
    val comWidevineAlpha: ComWidevineAlpha?
)