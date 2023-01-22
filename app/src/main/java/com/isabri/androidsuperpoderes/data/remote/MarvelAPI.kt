package com.isabri.androidsuperpoderes.data.remote

import com.isabri.androidsuperpoderes.data.remote.models.ComicDataWrapper
import com.isabri.androidsuperpoderes.data.remote.models.SeriesDataWrapper
import com.isabri.androidsuperpoderes.data.remote.models.character.CharactersDataWrapper
import com.isabri.androidsuperpoderes.utils.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelAPI {

    @GET(Constant.CHARACTERS_ENDPOINT)
    suspend fun getCharactersDataWrapper(): Response<CharactersDataWrapper>

    @GET(Constant.SERIES_ENDPOINT)
    suspend fun getSeriesDataWrapper(@Path(Constant.PATH_CHARACTER_ID) characterId: String): Response<SeriesDataWrapper>

    @GET(Constant.COMICS_ENDPOINT)
    suspend fun getComicsDataWrapper(@Path(Constant.PATH_CHARACTER_ID) characterId: String): Response<ComicDataWrapper>
}