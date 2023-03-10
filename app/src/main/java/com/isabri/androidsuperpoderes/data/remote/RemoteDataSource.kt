package com.isabri.androidsuperpoderes.data.remote

import com.isabri.androidsuperpoderes.data.remote.models.character.CharacterRemote
import com.isabri.androidsuperpoderes.data.remote.models.states.ComicsListState
import com.isabri.androidsuperpoderes.data.remote.models.states.SeriesListState
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getCharactersFlow(): Flow<List<CharacterRemote>>
    suspend fun getSeries(characterId: String): SeriesListState
    suspend fun getComics(characterId: String): ComicsListState
}