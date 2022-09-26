package com.example.movieapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class LicenseServers(
    @SerializedName("com.microsoft.playready")
    val comMicrosoftPlayready: String?,
    @SerializedName("com.widevine.alpha")
    val comWidevineAlpha: String?,
    @SerializedName("org.w3.clearkey")
    val orgW3Clearkey: String?
)