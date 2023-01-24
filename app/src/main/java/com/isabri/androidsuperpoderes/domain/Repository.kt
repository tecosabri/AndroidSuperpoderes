package com.isabri.androidsuperpoderes.domain


import com.isabri.androidsuperpoderes.data.local.models.CharacterEntity
import com.isabri.androidsuperpoderes.domain.models.Character
import com.isabri.androidsuperpoderes.data.remote.models.states.CharactersListState
import com.isabri.androidsuperpoderes.data.remote.models.states.ComicsListState
import com.isabri.androidsuperpoderes.data.remote.models.states.SeriesListState
import kotlinx.coroutines.flow.Flow


interface Repository {
    suspend fun getCharacters(): CharactersListState
    fun getCharacter(characterId: String): Flow<List<Character>>
    fun updateCharacter(character: Character)
    suspend fun getSeries(characterId: String): SeriesListState
    suspend fun getComics(characterId: String): ComicsListState
}