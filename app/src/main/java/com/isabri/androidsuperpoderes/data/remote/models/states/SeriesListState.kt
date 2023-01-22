package com.isabri.androidsuperpoderes.data.remote.models.states

import com.isabri.androidsuperpoderes.domain.models.Serie


sealed class SeriesListState {
    data class Success(val series: List<Serie>): SeriesListState()
    data class Failure(val error: String): SeriesListState()
}
