package com.isabri.androidsuperpoderes.tests.ui

import com.google.common.truth.Truth
import com.isabri.androidsuperpoderes.data.RepositoryImpl
import com.isabri.androidsuperpoderes.data.remote.models.states.ComicsListState
import com.isabri.androidsuperpoderes.domain.Repository
import com.isabri.androidsuperpoderes.domain.models.Character
import com.isabri.androidsuperpoderes.testUtils.FakeData.FakeCharacterData
import com.isabri.androidsuperpoderes.testUtils.fakes.FakeLocalDataSource
import com.isabri.androidsuperpoderes.testUtils.fakes.FakeRemoteDataSource
import com.isabri.androidsuperpoderes.ui.charactersList.CharactersListViewModel
import com.isabri.androidsuperpoderes.ui.comicsList.ComicsListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
@OptIn(ExperimentalCoroutinesApi::class)
class ComicsListViewModelTests {

    lateinit var fakeLocalDataSource: FakeLocalDataSource
    lateinit var fakeRemoteDataSource: FakeRemoteDataSource
    lateinit var repository: Repository
    lateinit var sut: ComicsListViewModel

    @Before
    fun setUp() {
        fakeLocalDataSource = FakeLocalDataSource()
        fakeRemoteDataSource = FakeRemoteDataSource()
        repository = RepositoryImpl(fakeRemoteDataSource, fakeLocalDataSource)
    }

    @Test
    fun `GIVEN a list of comics locally stored WHEN init sut THEN comics are availaible`() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        runTest {
            // GIVEN
            val characterId = 0
            fakeLocalDataSource.setFlagValue(true)
            sut = ComicsListViewModel(repository, "0")
            // WHEN
            sut.getComics(characterId.toString())
            // THEN
            Truth.assertThat(sut.comics.value.get(0).title).isEqualTo("fakeTitleEntity")
        }
    }


}