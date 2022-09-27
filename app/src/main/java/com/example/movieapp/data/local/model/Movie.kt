package com.example.movieapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.data.remote.dto.*

@Entity
data class Movie(
    @PrimaryKey val id: Int? = null,
    val adTagUri: String?,
    val certificateUri: Object?,
    val clearKeys: Object?,
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
    val mimeType: Object?,
    val name: String?,
    val requestFilter: Object?,
    val responseFilter: Object?,
    val shortName: String?,
    val source: String?,
    val storedContent: StoredContent?,
    val storedProgress: Int?
)

fun MovieApiDto.toMovie(): Movie {
    return Movie(
        adTagUri = adTagUri,
        certificateUri = certificateUri,
        clearKeys = clearKeys,
        description = description,
        disabled = disabled,
        drm = drm,
        extraConfig = extraConfig,
        extraText = extraText,
        features = features,
        focus = focus,
        iconUri = iconUri,
        imaAssetKey = imaAssetKey,
        imaContentSrcId = imaContentSrcId,
        imaVideoId = imaVideoId,
        isFeatured = isFeatured,
        licenseRequestHeaders = licenseRequestHeaders,
        licenseServers = licenseServers,
        manifestUri = manifestUri,
        mediaPlaylistFullMimeType = mediaPlaylistFullMimeType,
        mimeType = mimeType,
        name = name,
        requestFilter = requestFilter,
        responseFilter = responseFilter,
        shortName = shortName,
        source = source,
        storedContent = storedContent,
        storedProgress = storedProgress
    )
}