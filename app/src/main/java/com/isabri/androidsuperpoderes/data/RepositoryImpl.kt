package com.isabri.androidsuperpoderes.data

import android.util.Log
import com.isabri.androidsuperpoderes.data.remote.RemoteDataSource
import com.isabri.androidsuperpoderes.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource): Repository {
    override suspend fun getToken(): String {
        return remoteDataSource.getToken()
    }
}