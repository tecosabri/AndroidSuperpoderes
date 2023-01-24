package com.isabri.androidsuperpoderes.data.local

import com.isabri.androidsuperpoderes.data.local.models.CharacterEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val characterDAO: CharacterDAO): LocalDataSource {
    override fun getCharacters(): List<CharacterEntity> {
        return characterDAO.getAllCharacters()
    }

    override fun getFavoriteCharacters(): List<CharacterEntity> {
        return characterDAO.getAllFavoriteCharacters()
    }

    override fun insertCharacters(characterEntities: List<CharacterEntity>) {
        characterDAO.insertAll(characterEntities)
    }
}