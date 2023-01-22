package com.isabri.androidsuperpoderes.data

import android.util.Log
import com.isabri.androidsuperpoderes.data.remote.RemoteDataSource
import com.isabri.androidsuperpoderes.data.remote.models.states.CharactersListState
import com.isabri.androidsuperpoderes.data.remote.models.states.ComicsListState
import com.isabri.androidsuperpoderes.data.remote.models.states.SeriesListState
import com.isabri.androidsuperpoderes.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource): Repository {
    override suspend fun getCharacters(): CharactersListState {
        return remoteDataSource.getCharacters()
    }

    override suspend fun getSeries(characterId: String): SeriesListState {
        return remoteDataSource.getSeries(characterId)
    }

    override suspend fun getComics(characterId: String): ComicsListState {
        return remoteDataSource.getComics(characterId)
    }

}