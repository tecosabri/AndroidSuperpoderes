package com.isabri.androidsuperpoderes.data.remote.models

import com.squareup.moshi.Json

data class ThumbnailRemote(
    @Json(name = "path") val path: String?,
    @Json(name = "extension") val extension: String?
)
