package com.isabri.androidsuperpoderes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.isabri.androidsuperpoderes.data.local.models.CharacterEntity
import com.isabri.androidsuperpoderes.data.local.models.SerieEntity

@Database(entities = [CharacterEntity::class, SerieEntity::class], version = 1)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun getDAO(): CharacterDAO
}