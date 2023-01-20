package com.isabri.androidsuperpoderes.data.remote.models

import com.squareup.moshi.Json

data class CharacterRemote(
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "thumbnail") val thumbnail: ThumbnailRemote?,
    @Json(name = "series") val series: SeriesList
    )

data class SeriesList(
    @Json(name = "items") val items: List<SeriesSummary>
    )

data class SeriesSummary(
    @Json(name = "resourceURI") val resourceURI: String?,
    @Json(name = "name") val name: String?
    )

data class CharactersDataWrapper(
    @Json(name = "data") val data: CharactersDataContainer?
)

data class CharactersDataContainer(
    @Json(name = "results") val results: List<CharacterRemote>?
)
