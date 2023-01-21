package com.isabri.androidsuperpoderes.data.remote

import com.isabri.androidsuperpoderes.data.remote.models.character.CharactersDataWrapper
import com.isabri.androidsuperpoderes.utils.Constant
import retrofit2.Response
import retrofit2.http.GET

interface MarvelAPI {

    @GET(Constant.CHARACTERS_ENDPOINT)
    suspend fun getCharactersDataWrapper(): Response<CharactersDataWrapper>
}