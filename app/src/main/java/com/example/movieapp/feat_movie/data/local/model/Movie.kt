package com.example.movieapp.feat_movie.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.feat_movie.data.remote.dto.*

@Entity
data class Movie(
    @PrimaryKey val id: Long? = null,
    val filePath: String? = null,
    val adTagUri: String? = null,
    val certificateUri: Object? = null,
    val clearKeys: Object? = null,
    val description: String? = null,
    val disabled: Boolean? = null,
    val drm: List<String?>? = null,
    val extraConfig: ExtraConfig? = null,
    val extraText: List<ExtraText?>? = null,
    val features: List<String?>? = null,
    val focus: Boolean? = null,
    val iconUri: String? = null,
    val imaAssetKey: String? = null,
    val imaContentSrcId: Int? = null,
    val imaVideoId: String? = null,
    val isFeatured: Boolean? = null,
    val licenseRequestHeaders: LicenseRequestHeaders? = null,
    val licenseServers: LicenseServers? = null,
    val manifestUri: String? = null,
    val mediaPlaylistFullMimeType: String? = null,
    val mimeType: Object? = null,
    val name: String? = null,
    val requestFilter: Object? = null,
    val responseFilter: Object? = null,
    val shortName: String? = null,
    val source: String? = null,
    val storedContent: StoredContent? = null,
    val storedProgress: Int? = null
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