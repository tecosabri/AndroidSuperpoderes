package com.isabri.androidsuperpoderes.domain

import com.isabri.androidsuperpoderes.domain.models.Character
import com.isabri.androidsuperpoderes.data.remote.models.states.CharactersListState
import com.isabri.androidsuperpoderes.data.remote.models.states.ComicsListState
import com.isabri.androidsuperpoderes.data.remote.models.states.SeriesListState


interface Repository {
    suspend fun getCharacters(): CharactersListState
    suspend fun getCharacter(characterId: String): CharactersListState
    fun updateCharacter(character: Character)
    suspend fun getSeries(characterId: String): SeriesListState
    suspend fun getComics(characterId: String): ComicsListState
}