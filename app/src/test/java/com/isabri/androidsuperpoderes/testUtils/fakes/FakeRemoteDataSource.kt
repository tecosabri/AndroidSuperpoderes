package com.isabri.androidsuperpoderes.testUtils.fakes

import com.isabri.androidsuperpoderes.data.remote.RemoteDataSource
import com.isabri.androidsuperpoderes.data.remote.models.character.CharacterRemote
import com.isabri.androidsuperpoderes.data.remote.models.states.ComicsListState
import com.isabri.androidsuperpoderes.data.remote.models.states.SeriesListState
import com.isabri.androidsuperpoderes.testUtils.FakeData.FakeCharacterData
import com.isabri.androidsuperpoderes.testUtils.FakeData.FakeComicData
import com.isabri.androidsuperpoderes.testUtils.FakeData.FakeSerieData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRemoteDataSource(): RemoteDataSource {

    var flag = false

    fun getFlagValue(): Boolean {
        return flag
    }
    fun setFlagValue(flag: Boolean) {
        this.flag = flag
    }

    override fun getCharactersFlow(): Flow<List<CharacterRemote>> {
        return if(flag) flow {
            val remoteCharacters = FakeCharacterData.getFakeRemoteCharacters()
            emit(remoteCharacters)
        } else {
            flow { emit(emptyList())}
        }
    }

    override suspend fun getSeries(characterId: String): SeriesListState {
        return SeriesListState.Success(FakeSerieData.getFakeSeries())
    }

    override suspend fun getComics(characterId: String): ComicsListState {
        return ComicsListState.Success(FakeComicData.getFakeeComics())
    }
}