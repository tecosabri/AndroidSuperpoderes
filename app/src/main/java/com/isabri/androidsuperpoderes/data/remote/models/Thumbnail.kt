package com.isabri.androidsuperpoderes.data.remote.models

import com.squareup.moshi.Json

data class Thumbnail(
    @Json(name = "path") val path: String?,
    @Json(name = "extension") val extension: String?,
    val completePath: String = "$path.$extension"
)
