package com.isabri.androidsuperpoderes.domain.models

import com.isabri.androidsuperpoderes.data.remote.models.Thumbnail

data class Comic(
    val id: Int?,
    val title: String?,
    val thumbnail: Thumbnail?,
    val description: String?
)