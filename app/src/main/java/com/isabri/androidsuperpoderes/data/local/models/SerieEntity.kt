package com.isabri.androidsuperpoderes.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "series")
data class SerieEntity(
    @PrimaryKey val id: Int?,
    val characterId: Int?,
    val title: String?,
//    @ColumnInfo(name = "thumbnail") val thumbnail: Thumbnail?,
    val description: String?
)
