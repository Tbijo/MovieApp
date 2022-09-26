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
