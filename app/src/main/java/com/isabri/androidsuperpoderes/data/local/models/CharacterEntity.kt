package com.isabri.androidsuperpoderes.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.isabri.androidsuperpoderes.data.remote.models.Thumbnail
import com.isabri.androidsuperpoderes.data.remote.models.character.SeriesList
import com.isabri.androidsuperpoderes.data.remote.models.character.SeriesSummary
import com.isabri.androidsuperpoderes.domain.models.Serie
import com.isabri.androidsuperpoderes.utils.Constant
import com.squareup.moshi.Json

@Entity(tableName = Constant.DB_CHARACTERS)
data class CharacterEntity (
    @PrimaryKey val id: Int?,
    val name: String?,
    val description: String?,
    @Embedded val thumbnail: Thumbnail?,
//    @Embedded val series: SeriesListEntity?,
    var favorite: Boolean // Only local
)

