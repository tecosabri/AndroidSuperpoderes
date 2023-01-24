package com.isabri.androidsuperpoderes.di

import com.isabri.androidsuperpoderes.data.RepositoryImpl
import com.isabri.androidsuperpoderes.data.local.LocalDataSource
import com.isabri.androidsuperpoderes.data.local.LocalDataSourceImpl
import com.isabri.androidsuperpoderes.data.remote.RemoteDataSource
import com.isabri.androidsuperpoderes.data.remote.RemoteDataSourceImpl
import com.isabri.androidsuperpoderes.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(localDataSource: LocalDataSourceImpl): LocalDataSource
}