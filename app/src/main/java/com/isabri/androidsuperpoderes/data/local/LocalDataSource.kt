package com.isabri.androidsuperpoderes.data.local

import com.isabri.androidsuperpoderes.data.local.models.CharacterEntity
import com.isabri.androidsuperpoderes.data.local.models.ComicEntity
import com.isabri.androidsuperpoderes.data.local.models.SerieEntity

interface LocalDataSource {
    fun getCharacters(): List<CharacterEntity>
    fun getCharacter(characterId: String): List<CharacterEntity>
    fun getFavoriteCharacters(): List<CharacterEntity>
    fun insertCharacters(characterEntities: List<CharacterEntity>)
    fun getSeriesByCharacterId(characterId: String): List<SerieEntity>
    fun insertSeries(serieEntities: List<SerieEntity>)
    fun getComicsByCharacterId(characterId: String): List<ComicEntity>
    fun insertComics(comicEntities: List<ComicEntity>)
}