package com.isabri.androidsuperpoderes.data.remote

interface RemoteDataSource {
    suspend fun getToken(): String
}