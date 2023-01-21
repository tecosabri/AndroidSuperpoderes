package com.isabri.androidsuperpoderes.domain.models

import com.isabri.androidsuperpoderes.data.remote.models.ThumbnailRemote
import com.isabri.androidsuperpoderes.data.remote.models.character.SeriesList
import com.squareup.moshi.Json

data class Character (
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "thumbnail") val thumbnail: ThumbnailRemote?,
    @Json(name = "series") val series: SeriesList
)