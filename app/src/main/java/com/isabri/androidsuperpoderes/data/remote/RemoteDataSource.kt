package com.isabri.androidsuperpoderes.data.remote

import com.isabri.androidsuperpoderes.data.remote.models.states.CharactersListState
import com.isabri.androidsuperpoderes.data.remote.models.states.ComicsListState
import com.isabri.androidsuperpoderes.data.remote.models.states.SeriesListState
import com.isabri.androidsuperpoderes.domain.models.Character

interface RemoteDataSource {
    suspend fun getCharacters(): CharactersListState
    suspend fun getSeries(characterId: String): SeriesListState
    suspend fun getComics(characterId: String): ComicsListState
}