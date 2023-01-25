package com.isabri.androidsuperpoderes.data.local

import com.isabri.androidsuperpoderes.data.local.models.CharacterEntity
import com.isabri.androidsuperpoderes.data.local.models.ComicEntity
import com.isabri.androidsuperpoderes.data.local.models.SerieEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun countCharacters(): Int
    fun getCharactersFlow(): Flow<List<CharacterEntity>>
    fun getCharacter(characterId: String): Flow<List<CharacterEntity>>
    fun getFavoriteCharacters(): List<CharacterEntity>
    fun updateCharacter(characterEntity: CharacterEntity)
    fun insertCharacters(characterEntities: List<CharacterEntity>)
    fun getSeriesByCharacterId(characterId: String): List<SerieEntity>
    fun insertSeries(serieEntities: List<SerieEntity>)
    fun getComicsByCharacterId(characterId: String): List<ComicEntity>
    fun insertComics(comicEntities: List<ComicEntity>)
}