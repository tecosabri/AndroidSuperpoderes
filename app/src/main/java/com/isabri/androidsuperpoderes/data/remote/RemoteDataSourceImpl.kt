package com.isabri.androidsuperpoderes.data.remote

import com.isabri.androidsuperpoderes.data.mappers.CharacterMapper
import com.isabri.androidsuperpoderes.data.remote.models.states.CharactersListState
import com.isabri.androidsuperpoderes.utils.Constant
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: MarvelAPI): RemoteDataSource {

    override suspend fun getCharacters(): CharactersListState {
        val charactersDataWrapper= api.getCharactersDataWrapper()

        if (charactersDataWrapper.isSuccessful) {
            val remoteCharacters = charactersDataWrapper.body()?.data?.results
            remoteCharacters?.apply {
                return CharactersListState.Success(CharacterMapper.mapRemoteCharactersToCharacters(this))
            }
        }
        return CharactersListState.Failure(Constant.ERR_CHARACTERS_FETCHING)
    }
}