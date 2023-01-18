package com.isabri.androidsuperpoderes.data.remote

import retrofit2.http.POST

interface DragonBallAPI {

    @POST("api/auth/login")
    suspend fun getToken(): String
}