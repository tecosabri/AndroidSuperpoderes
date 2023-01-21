package com.isabri.androidsuperpoderes.domain

import com.isabri.androidsuperpoderes.data.remote.models.states.CharactersListState


interface Repository {
    suspend fun getCharacters(): CharactersListState
}