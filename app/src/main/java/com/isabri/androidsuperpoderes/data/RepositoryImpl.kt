package com.isabri.androidsuperpoderes.data

import android.util.Log
import com.isabri.androidsuperpoderes.data.local.LocalDataSource
import com.isabri.androidsuperpoderes.data.local.models.CharacterEntity
import com.isabri.androidsuperpoderes.data.mappers.CharacterMapper
import com.isabri.androidsuperpoderes.data.mappers.ComicMapper
import com.isabri.androidsuperpoderes.data.mappers.SerieMapper
import com.isabri.androidsuperpoderes.data.remote.RemoteDataSource
import com.isabri.androidsuperpoderes.data.remote.models.states.CharactersListState
import com.isabri.androidsuperpoderes.data.remote.models.states.ComicsListState
import com.isabri.androidsuperpoderes.data.remote.models.states.SeriesListState
import com.isabri.androidsuperpoderes.domain.Repository
import com.isabri.androidsuperpoderes.domain.models.Character
import com.isabri.androidsuperpoderes.domain.models.Serie
import com.isabri.androidsuperpoderes.utils.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
    ): Repository {

    override suspend fun getCharacters(): CharactersListState {
        var localCharacters = localDataSource.getCharacters()
        // Characters already locally stored
        if(localCharacters.isNotEmpty()) return CharactersListState.Success(CharacterMapper.mapCharacterEntitiesToCharacters(localCharacters))
        // Characters not locally stored -> store them
        val charactersListState = remoteDataSource.getCharacters()
        when(charactersListState) {
            is CharactersListState.Failure -> return CharactersListState.Failure(Constant.ERR_CHARACTERS_FETCHING)
            is CharactersListState.Success -> {
                localCharacters = CharacterMapper.mapCharactersToCharacterEntities(charactersListState.characters)
                localDataSource.insertCharacters(localCharacters)
                return charactersListState
            }
        }
    }

    override fun getCharacter(characterId: String): Flow<List<Character>> {
        // At this point, the character should have been stored
        return localDataSource.getCharacter(characterId).map { CharacterMapper.mapCharacterEntitiesToCharacters(it) }
    }

    override fun updateCharacter(character: Character) {
        localDataSource.updateCharacter(CharacterMapper.mapCharacterToCharacterEntity(character))
    }

    override suspend fun getSeries(characterId: String): SeriesListState {
        var localSeries = localDataSource.getSeriesByCharacterId(characterId)
        //Series already locally stored
        if(localSeries.isNotEmpty()) return SeriesListState.Success(SerieMapper.mapSerieEntitiesToSeries(localSeries))
        // Series not locally stored -> store them
        val seriesListState = remoteDataSource.getSeries(characterId)
        when(seriesListState) {
            is SeriesListState.Failure -> return SeriesListState.Failure(Constant.ERR_SERIES_FETCHING)
            is SeriesListState.Success -> {
                localSeries = SerieMapper.mapSeriesToSerieEntities(seriesListState.series, characterId)
                localDataSource.insertSeries(localSeries)
                return seriesListState
            }
        }
    }

    override suspend fun getComics(characterId: String): ComicsListState {
        var localComics = localDataSource.getComicsByCharacterId(characterId)
        // Comics already locally stored
        if(localComics.isNotEmpty()) return ComicsListState.Success(ComicMapper.mapComicEntitiesToComics(localComics))
        // Comics not locally stored -> store them
        val comicsListState = remoteDataSource.getComics(characterId)
        when(comicsListState){
            is ComicsListState.Failure -> return ComicsListState.Failure(Constant.ERR_COMICS_FETCHING)
            is ComicsListState.Success -> {
                localComics = ComicMapper.mapComicsToComicEntities(comicsListState.comics, characterId)
                localDataSource.insertComics(localComics)
                return  comicsListState
            }
        }
    }

}