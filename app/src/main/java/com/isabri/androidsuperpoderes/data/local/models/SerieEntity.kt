package com.isabri.androidsuperpoderes.data.local.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.isabri.androidsuperpoderes.data.remote.models.Thumbnail

@Entity(tableName = "series")
data class SerieEntity(
    @PrimaryKey val id: Int?,
    val characterId: Int?,
    val title: String?,
    @Embedded val thumbnail: Thumbnail?,
    val description: String?
)
