package com.isabri.androidsuperpoderes.data.local.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.isabri.androidsuperpoderes.data.remote.models.Thumbnail
import com.isabri.androidsuperpoderes.utils.Constant

@Entity(tableName = Constant.DB_CHARACTERS)
data class CharacterEntity (
    @PrimaryKey val id: Int?,
    val name: String?,
    val description: String?,
    @Embedded val thumbnail: Thumbnail?,
    var favorite: Boolean // Only local
)

