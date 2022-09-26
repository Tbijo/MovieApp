package com.example.movieapp.data.remote.dto

import java.util.*

data class MovieApiDto(
    val adTagUri: String?,
    val certificateUri: Objects?,
    val clearKeys: Objects?,
    val description: String?,
    val disabled: Boolean?,
    val drm: List<String?>?,
    val extraConfig: ExtraConfig?,
    val extraText: List<ExtraText?>?,
    val features: List<String?>?,
    val focus: Boolean?,
    val iconUri: String?,
    val imaAssetKey: String?,
    val imaContentSrcId: Int?,
    val imaVideoId: String?,
    val isFeatured: Boolean?,
    val licenseRequestHeaders: LicenseRequestHeaders?,
    val licenseServers: LicenseServers?,
    val manifestUri: String?,
    val mediaPlaylistFullMimeType: String?,
    val mimeType: Objects?,
    val name: String?,
    val requestFilter: Objects?,
    val responseFilter: Objects?,
    val shortName: String?,
    val source: String?,
    val storedContent: StoredContent?,
    val storedProgress: Int?
)