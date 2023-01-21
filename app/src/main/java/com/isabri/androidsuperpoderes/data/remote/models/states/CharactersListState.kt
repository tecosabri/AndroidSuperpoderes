package com.isabri.androidsuperpoderes.data.remote.models.states


import com.isabri.androidsuperpoderes.domain.models.Character

sealed class CharactersListState {
    data class Success(val characters: List<Character>): CharactersListState()
    data class Failure(val error: String): CharactersListState()
}
