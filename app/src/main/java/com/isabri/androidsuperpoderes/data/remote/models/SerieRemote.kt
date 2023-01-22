package com.isabri.androidsuperpoderes.data.remote.models

import com.squareup.moshi.Json

data class SerieRemote(
    @Json(name = "id") val id: Int?,
    @Json(name = "title") val title: String?,
    @Json(name = "thumbnail") val thumbnail: Thumbnail?,
    @Json(name = "description") val description: String?
)

data class SeriesDataWrapper(
    @Json(name = "data") val data: SeriesDataContainer?,
)

data class SeriesDataContainer(
    @Json(name = "results") val results: List<SerieRemote>?,
)
