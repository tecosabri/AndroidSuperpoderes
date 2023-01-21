package com.isabri.androidsuperpoderes.data.remote

import com.isabri.androidsuperpoderes.data.remote.models.states.CharactersListState
import com.isabri.androidsuperpoderes.domain.models.Character

interface RemoteDataSource {
    suspend fun getCharacters(): CharactersListState
}