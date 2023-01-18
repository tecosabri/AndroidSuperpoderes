package com.isabri.androidsuperpoderes.domain


interface Repository {
    suspend fun getToken(): String
}