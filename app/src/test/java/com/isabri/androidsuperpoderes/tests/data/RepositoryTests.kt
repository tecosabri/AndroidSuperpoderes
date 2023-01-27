package com.isabri.androidsuperpoderes.tests.data

import com.google.common.truth.Truth
import com.isabri.androidsuperpoderes.data.RepositoryImpl
import com.isabri.androidsuperpoderes.data.local.LocalDataSource
import com.isabri.androidsuperpoderes.data.remote.models.character.SeriesList
import com.isabri.androidsuperpoderes.data.remote.models.states.ComicsListState
import com.isabri.androidsuperpoderes.data.remote.models.states.SeriesListState
import com.isabri.androidsuperpoderes.domain.Repository
import com.isabri.androidsuperpoderes.testUtils.FakeData.FakeCharacterData
import com.isabri.androidsuperpoderes.testUtils.fakes.FakeLocalDataSource
import com.isabri.androidsuperpoderes.testUtils.fakes.FakeRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryTests {

    lateinit var fakeLocalDataSource: FakeLocalDataSource
    lateinit var fakeRemoteDataSource: FakeRemoteDataSource
    lateinit var sut: Repository

    @Before
    fun setUp() {
        fakeLocalDataSource = FakeLocalDataSource()
        fakeRemoteDataSource = FakeRemoteDataSource()
        sut = RepositoryImpl(fakeRemoteDataSource, fakeLocalDataSource)
    }

    @Test
    fun `GIVEN empty local characters table WHEN getCharacters() THEN returns character list flow`() = runTest() {
        // GIVEN
        fakeLocalDataSource.setFlagValue(false) // getCharactersFlow(): Flow<List<CharacterEntity>> returns Flow of empty list
        fakeRemoteDataSource.setFlagValue(true) // getCharactersFlow(): Flow<List<CharacterRemote>> returns a Flow with a list of characters remote
        // WHEN
        val charactersList = sut.getCharacters().toList()
        // THEN
        Truth.assertThat(charactersList.get(0).get(0).name).containsMatch("fakeName")
    }

    @Test
    fun `GIVEN a list of characters locally stored WHEN getFavoriteCharacters() THEN returns favorites list flow`() = runTest() {
        // GIVEN
        // WHEN
        val favoriteCharactersList = sut.getFavoriteCharacters().toList()
        // THEN
        favoriteCharactersList.forEach {
            it.forEach { character -> Truth.assertThat(character.favorite).isTrue() }
        }
    }

    @Test
    fun `GIVEN a list of characters locally stored WHEN getCharacter with id THEN returns list flow containing only that character`() = runTest() {
        // GIVEN
        // WHEN
        val characterWithID0 = sut.getCharacter("0").toList()
        // THEN
        Truth.assertThat(characterWithID0.get(0).size).isEqualTo(1)
        Truth.assertThat(characterWithID0.get(0).get(0).id).isEqualTo(0)
    }

    @Test
    fun `GIVEN a list of characters locally stored WHEN updateCharacter() THEN character is stored locally`() = runTest() {
        // GIVEN
        val characterToUpdate = FakeCharacterData.getFakeCharacter(0)
        // WHEN
        sut.updateCharacter(characterToUpdate)
        // THEN
        Truth.assertThat(fakeLocalDataSource.fakeLocalCharacterEntities.get(0).id).isEqualTo(0)
    }

    @Test
    fun `GIVEN a list of series locally stored WHEN getSeries() THEN return these series`() = runTest() {
        // GIVEN
        val characterId = 0
        fakeLocalDataSource.setFlagValue(true)
        // WHEN
        val seriesListState = sut.getSeries(characterId.toString())
        // THEN
        Truth.assertThat((seriesListState as SeriesListState.Success).series.get(0).id).isEqualTo(0)
    }

    @Test
    fun `GIVEN an empty local database WHEN getSeries() THEN insert series locally`() = runTest() {
        // GIVEN
        val characterId = 0
        // WHEN
        val seriesListState = sut.getSeries(characterId.toString())
        // THEN
        Truth.assertThat(seriesListState).isInstanceOf(SeriesListState.Success(emptyList()).javaClass)
        Truth.assertThat(fakeLocalDataSource.fakeLocalSerieEntities).isNotEmpty()
    }

    @Test
    fun `GIVEN a list of comics locally stored WHEN getComics() THEN return these comics`() = runTest() {
        // GIVEN
        val characterId = 0
        fakeLocalDataSource.setFlagValue(true)
        // WHEN
        val comicsListState = sut.getComics(characterId.toString())
        // THEN
        Truth.assertThat((comicsListState as ComicsListState.Success).comics.get(0).id).isEqualTo(0)
    }


    @Test
    fun `GIVEN an empty local database WHEN getComics() THEN insert comics locally`() = runTest() {
        // GIVEN
        val characterId = 0
        // WHEN
        val comicsListState = sut.getComics(characterId.toString())
        // THEN
        Truth.assertThat(comicsListState).isInstanceOf(ComicsListState.Success(emptyList()).javaClass)
        Truth.assertThat(fakeLocalDataSource.fakeLocalComicEntities).isNotEmpty()
    }

}