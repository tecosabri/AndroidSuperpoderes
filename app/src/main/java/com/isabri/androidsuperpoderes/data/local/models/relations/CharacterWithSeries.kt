package com.isabri.androidsuperpoderes.data.local.models.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.isabri.androidsuperpoderes.data.local.models.CharacterEntity
import com.isabri.androidsuperpoderes.data.local.models.SerieEntity

data class CharacterWithSeries(
    @Embedded val characterEntity: CharacterEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "characterId"
    )
    val series: List<SerieEntity>
)
