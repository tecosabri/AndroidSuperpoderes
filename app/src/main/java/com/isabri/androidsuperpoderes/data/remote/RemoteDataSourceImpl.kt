package com.isabri.androidsuperpoderes.data.remote

import com.isabri.androidsuperpoderes.data.mappers.CharacterMapper
import com.isabri.androidsuperpoderes.data.mappers.ComicMapper
import com.isabri.androidsuperpoderes.data.mappers.SerieMapper
import com.isabri.androidsuperpoderes.data.remote.models.states.CharactersListState
import com.isabri.androidsuperpoderes.data.remote.models.states.ComicsListState
import com.isabri.androidsuperpoderes.data.remote.models.states.SeriesListState
import com.isabri.androidsuperpoderes.domain.models.Comic
import com.isabri.androidsuperpoderes.utils.Constant
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: MarvelAPI): RemoteDataSource {

    override suspend fun getCharacters(): CharactersListState {
        val charactersDataWrapper= api.getCharactersDataWrapper()

        if (charactersDataWrapper.isSuccessful) {
            val remoteCharacters = charactersDataWrapper.body()?.data?.results
            remoteCharacters?.apply {
                return CharactersListState.Success(CharacterMapper.mapRemoteCharactersToCharacters(this))
            }
        }
        return CharactersListState.Failure(Constant.ERR_CHARACTERS_FETCHING)
    }

    override suspend fun getSeries(characterId: String): SeriesListState {
        val seriesDataWrapper = api.getSeriesDataWrapper(characterId)

        if (seriesDataWrapper.isSuccessful) {
            val remoteSeries = seriesDataWrapper.body()?.data?.results
            remoteSeries?.apply {
                return SeriesListState.Success(SerieMapper.mapSeriesRemoteToSeries(remoteSeries))
            }
        }
        return SeriesListState.Failure(Constant.ERR_SERIES_FETCHING)
    }

    override suspend fun getComics(characterId: String): ComicsListState {
        val comicsDataWrapper = api.getComicsDataWrapper(characterId)

        if(comicsDataWrapper.isSuccessful) {
            val remoteComics = comicsDataWrapper.body()?.data?.results
            remoteComics?.apply {
                return ComicsListState.Success(ComicMapper.mapRemoteComicsToComics(remoteComics))
            }
        }
        return ComicsListState.Failure(Constant.ERR_COMICS_FETCHING)
    }
}