package com.isabri.androidsuperpoderes.data.local

import androidx.annotation.WorkerThread
import androidx.room.*
import com.isabri.androidsuperpoderes.data.local.models.CharacterEntity
import com.isabri.androidsuperpoderes.data.local.models.SerieEntity
import com.isabri.androidsuperpoderes.data.local.models.relations.CharacterWithSeries
import com.isabri.androidsuperpoderes.utils.Constant

@Dao
interface CharacterDAO {
    @WorkerThread
    @Query("SELECT * FROM ${Constant.DB_CHARACTERS}")
    fun getAllCharacters(): List<CharacterEntity>

    @WorkerThread
    @Query("SELECT * FROM ${Constant.DB_CHARACTERS} WHERE favorite = 1")
    fun getAllFavoriteCharacters(): List<CharacterEntity>

    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characters: List<CharacterEntity>)

    @Transaction
    @Query("SELECT * FROM ${Constant.DB_CHARACTERS} WHERE id = :characterId")
    fun getCharactersWithSeries(characterId: String): List<CharacterWithSeries>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(series: SerieEntity)
}