package com.isabri.androidsuperpoderes.data.remote

import android.util.Log
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: MarvelAPI): RemoteDataSource {

    override suspend fun getToken(): String {
        val result = api.getToken()
        Log.d("TOKEN", result)
        return result
    }

    override suspend fun getCharacters(): String {
        val characters = api.getCharactersDataWrapper().data?.results
        Log.d("LOG", characters?.get(0)?.name.toString())
        return ""
    }


}