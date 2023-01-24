package com.isabri.androidsuperpoderes.domain.models

import com.isabri.androidsuperpoderes.data.remote.models.Thumbnail
import com.isabri.androidsuperpoderes.data.remote.models.character.SeriesList
import com.squareup.moshi.Json

data class Character (
    val id: Int?,
    val name: String?,
    val description: String?,
    val thumbnail: Thumbnail?,
    val series: SeriesList?,
    var favorite: Boolean // Only local
)