package com.isabri.androidsuperpoderes.data.local

import com.isabri.androidsuperpoderes.data.local.models.CharacterEntity
import com.isabri.androidsuperpoderes.data.local.models.ComicEntity
import com.isabri.androidsuperpoderes.data.local.models.SerieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val characterDAO: CharacterDAO): LocalDataSource {

    override fun countCharacters(): Int {
        return characterDAO.countCharacters()
    }

    override fun getCharactersFlow(): Flow<List<CharacterEntity>> {
        return characterDAO.getAllCharactersFlow()
    }

    override fun getCharacter(characterId: String): Flow<List<CharacterEntity>> {
        return characterDAO.getCharacterById(characterId)
    }

    override fun getFavoriteCharacters(): Flow<List<CharacterEntity>> {
        return characterDAO.getAllFavoriteCharactersFlow()
    }

    override fun updateCharacter(characterEntity: CharacterEntity) {
        characterDAO.updateCharacter(characterEntity)
    }

    override fun insertCharacters(characterEntities: List<CharacterEntity>) {
        characterDAO.insertAllCharacters(characterEntities)
    }

    override fun getSeriesByCharacterId(characterId: String): List<SerieEntity> {
        val characterWithSeries = characterDAO.getCharactersWithSeries(characterId)
        if(characterWithSeries.isNotEmpty()) return characterWithSeries[0].series
        return emptyList()
    }

    override fun insertSeries(serieEntities: List<SerieEntity>) {
        characterDAO.insertAllSeries(serieEntities)
    }

    override fun getComicsByCharacterId(characterId: String): List<ComicEntity> {
        val charactersWithComics = characterDAO.getCharactersWithComics(characterId)
        if(charactersWithComics.isNotEmpty()) return charactersWithComics[0].comics
        return emptyList()
    }

    override fun insertComics(comicEntities: List<ComicEntity>) {
        characterDAO.insertAllComics(comicEntities)
    }
}