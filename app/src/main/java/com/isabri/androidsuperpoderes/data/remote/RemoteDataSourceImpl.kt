package com.isabri.androidsuperpoderes.data.remote

import android.util.Log
import okhttp3.Credentials
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: DragonBallAPI): RemoteDataSource {

    override suspend fun getToken(): String {
        val result = api.getToken()
        Log.d("TOKEN", result)
        return result
    }

    override suspend fun getCharacters(): String {
        Log.d("LOG", api.getCharacters().toString())
        return ""
    }


}