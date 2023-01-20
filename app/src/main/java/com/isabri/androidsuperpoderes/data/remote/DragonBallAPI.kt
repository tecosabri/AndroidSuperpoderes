package com.isabri.androidsuperpoderes.data.remote

import com.isabri.androidsuperpoderes.utils.Constant
import retrofit2.http.GET
import retrofit2.http.POST

interface DragonBallAPI {

    @POST("api/auth/login")
    suspend fun getToken(): String

    @GET(Constant.CHARACTERS_ENDPOINT)
    suspend fun getCharacters(): String
}