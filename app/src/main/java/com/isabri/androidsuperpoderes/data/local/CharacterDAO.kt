package com.isabri.androidsuperpoderes.data.local

import androidx.annotation.WorkerThread
import androidx.room.*
import com.isabri.androidsuperpoderes.data.local.models.CharacterEntity
import com.isabri.androidsuperpoderes.data.local.models.ComicEntity
import com.isabri.androidsuperpoderes.data.local.models.SerieEntity
import com.isabri.androidsuperpoderes.data.local.models.relations.CharacterWithComics
import com.isabri.androidsuperpoderes.data.local.models.relations.CharacterWithSeries
import com.isabri.androidsuperpoderes.utils.Constant

@Dao
interface CharacterDAO {
    @WorkerThread
    @Query("SELECT * FROM ${Constant.DB_CHARACTERS}")
    fun getAllCharacters(): List<CharacterEntity>

    @WorkerThread
    @Query("SELECT * FROM ${Constant.DB_CHARACTERS} WHERE id = :characterId")
    fun getCharacterById(characterId: String): List<CharacterEntity>

    @Update(entity = CharacterEntity::class)
    fun updateCharacter(characterEntity: CharacterEntity)

    @WorkerThread
    @Query("SELECT * FROM ${Constant.DB_CHARACTERS} WHERE favorite = 1")
    fun getAllFavoriteCharacters(): List<CharacterEntity>

    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCharacters(characters: List<CharacterEntity>)

    @Transaction
    @Query("SELECT * FROM ${Constant.DB_CHARACTERS} WHERE id = :characterId")
    fun getCharactersWithSeries(characterId: String): List<CharacterWithSeries>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllSeries(series: List<SerieEntity>)

    @Transaction
    @Query("SELECT * FROM ${Constant.DB_CHARACTERS} WHERE id = :characterId")
    fun getCharactersWithComics(characterId: String): List<CharacterWithComics>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllComics(comics: List<ComicEntity>)
}