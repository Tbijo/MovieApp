package com.example.movieapp.data.remote.dto


data class StoredContent(
    val appMetadata: AppMetadata?,
    val duration: Double?,
    val expiration: Any?,
    val isIncomplete: Boolean?,
    val offlineUri: String?,
    val originalManifestUri: String?,
    val size: Int?,
    val tracks: List<Track?>?
)