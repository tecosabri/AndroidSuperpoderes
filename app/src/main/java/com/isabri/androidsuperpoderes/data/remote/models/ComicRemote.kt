package com.isabri.androidsuperpoderes.data.remote.models

import com.squareup.moshi.Json

data class ComicRemote(
    @Json(name = "id") val id: Int?,
    @Json(name = "title") val title: String?,
    @Json(name = "thumbnail") val thumbnail: Thumbnail?,
    @Json(name = "description") val description: String?
)

data class ComicDataWrapper(
    @Json(name = "data") val data: ComicDataContainer?,
)

data class ComicDataContainer(
    @Json(name = "results") val results: List<ComicRemote>?,
)
