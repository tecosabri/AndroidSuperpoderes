package com.isabri.androidsuperpoderes.data.remote.models.states

import com.isabri.androidsuperpoderes.domain.models.Comic

sealed class ComicsListState {
    data class Success(val comics: List<Comic>): ComicsListState()
    data class Failure(val error: String): ComicsListState()
}