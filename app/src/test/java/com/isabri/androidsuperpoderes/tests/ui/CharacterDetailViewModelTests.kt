package com.isabri.androidsuperpoderes.tests.ui

import com.google.common.truth.Truth
import com.isabri.androidsuperpoderes.data.RepositoryImpl
import com.isabri.androidsuperpoderes.data.local.models.CharacterEntity
import com.isabri.androidsuperpoderes.data.remote.models.states.SeriesListState
import com.isabri.androidsuperpoderes.domain.Repository
import com.isabri.androidsuperpoderes.domain.models.Character
import com.isabri.androidsuperpoderes.testUtils.FakeData.FakeCharacterData
import com.isabri.androidsuperpoderes.testUtils.FakeData.FakeSerieData
import com.isabri.androidsuperpoderes.testUtils.fakes.FakeLocalDataSource
import com.isabri.androidsuperpoderes.testUtils.fakes.FakeRemoteDataSource
import com.isabri.androidsuperpoderes.ui.characterDetail.CharacterDetailViewModel
import com.isabri.androidsuperpoderes.ui.charactersList.CharactersListViewModel
import com.isabri.androidsuperpoderes.ui.seriesList.SeriesListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterDetailViewModelTests {

    lateinit var fakeLocalDataSource: FakeLocalDataSource
    lateinit var fakeRemoteDataSource: FakeRemoteDataSource
    lateinit var repository: Repository
    lateinit var sut: CharacterDetailViewModel

    @Before
    fun setUp() {
        fakeLocalDataSource = FakeLocalDataSource()
        fakeRemoteDataSource = FakeRemoteDataSource()
        repository = RepositoryImpl(fakeRemoteDataSource, fakeLocalDataSource)
        sut = CharacterDetailViewModel(repository, "0")
    }

    @Test
    fun `WHEN init sut THEN charactersList is not empty`() = runTest {
        // GIVEN
        fakeLocalDataSource.setSharedValue(true)
        // WHEN
        val actualList = mutableListOf<List<Character>>()
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            repository.getCharacters().toList(actualList)
        }
        fakeLocalDataSource.emit(listOf(FakeCharacterData.getFakeEntityCharacter(0)))
        // THEN
        Truth.assertThat(actualList).isNotEmpty()
        // FINALLY
        collectJob.cancel()
    }

    @Test
    fun `WHEN clickFavorite sut THEN favorite characters are updated`() = runTest {
        // GIVEN
        // WHEN
        sut.onClickFavorite(true)
        var localFavoriteCharacterEntities = listOf<CharacterEntity>()
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            localFavoriteCharacterEntities = fakeLocalDataSource.fakeLocalCharacterEntities
        }
        // THEN
        Truth.assertThat(localFavoriteCharacterEntities[0].name).isEqualTo("fakeNameEntity")
        // FINALLY
        collectJob.cancel()
    }
}