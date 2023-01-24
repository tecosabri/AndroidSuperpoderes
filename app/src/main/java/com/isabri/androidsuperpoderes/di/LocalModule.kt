package com.isabri.androidsuperpoderes.di

import android.content.Context
import androidx.room.Room
import com.isabri.androidsuperpoderes.data.local.CharacterDAO
import com.isabri.androidsuperpoderes.data.local.CharacterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CharacterDatabase {
        return Room.databaseBuilder(
            context,
            CharacterDatabase::class.java, "Character-database"
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideDao(heroDatabase: CharacterDatabase): CharacterDAO {
        return heroDatabase.getDAO()
    }
}