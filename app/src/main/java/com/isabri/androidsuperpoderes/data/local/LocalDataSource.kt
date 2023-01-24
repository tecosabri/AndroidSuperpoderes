package com.isabri.androidsuperpoderes.data.local

import com.isabri.androidsuperpoderes.data.local.models.CharacterEntity

interface LocalDataSource {
    fun getCharacters(): List<CharacterEntity>
    fun getFavoriteCharacters(): List<CharacterEntity>
    fun insertCharacters(characterEntities: List<CharacterEntity>)
}