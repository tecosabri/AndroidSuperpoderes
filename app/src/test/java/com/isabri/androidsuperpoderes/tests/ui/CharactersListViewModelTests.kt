package com.isabri.androidsuperpoderes.tests.ui

import com.google.common.truth.Truth
import com.isabri.androidsuperpoderes.data.RepositoryImpl
import com.isabri.androidsuperpoderes.domain.Repository
import com.isabri.androidsuperpoderes.domain.models.Character
import com.isabri.androidsuperpoderes.testUtils.FakeData.FakeCharacterData
import com.isabri.androidsuperpoderes.testUtils.fakes.FakeLocalDataSource
import com.isabri.androidsuperpoderes.testUtils.fakes.FakeRemoteDataSource
import com.isabri.androidsuperpoderes.ui.charactersList.CharactersListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersListViewModelTests {

    lateinit var fakeLocalDataSource: FakeLocalDataSource
    lateinit var fakeRemoteDataSource: FakeRemoteDataSource
    lateinit var repository: Repository
    lateinit var sut: CharactersListViewModel

    @Before
    fun setUp() {
        fakeLocalDataSource = FakeLocalDataSource()
        fakeRemoteDataSource = FakeRemoteDataSource()
        repository = RepositoryImpl(fakeRemoteDataSource, fakeLocalDataSource)
        sut = CharactersListViewModel(repository)
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
        fakeLocalDataSource.emit(FakeCharacterData.getFakeEntityCharacters())

        // THEN
        Truth.assertThat(actualList).isNotEmpty()
        // FINALLY
        collectJob.cancel()
    }

    @Test
    fun `WHEN getCharacters() THEN charactersList is not empty`() = runTest {
        // GIVEN
        fakeLocalDataSource.setSharedValue(true)
        // WHEN
        sut.getCharacters(true)
        val actualList = mutableListOf<List<Character>>()
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            repository.getFavoriteCharacters().toList(actualList)
        }
        fakeLocalDataSource.emit(FakeCharacterData.getFakeEntityCharacters())

        // THEN
        Truth.assertThat(actualList).isNotEmpty()
        // FINALLY
        collectJob.cancel()
    }
}