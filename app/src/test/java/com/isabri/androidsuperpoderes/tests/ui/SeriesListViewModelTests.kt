package com.isabri.androidsuperpoderes.tests.ui

import com.google.common.truth.Truth
import com.isabri.androidsuperpoderes.data.RepositoryImpl
import com.isabri.androidsuperpoderes.data.remote.models.states.ComicsListState
import com.isabri.androidsuperpoderes.data.remote.models.states.SeriesListState
import com.isabri.androidsuperpoderes.domain.Repository
import com.isabri.androidsuperpoderes.testUtils.FakeData.FakeComicData
import com.isabri.androidsuperpoderes.testUtils.FakeData.FakeSerieData
import com.isabri.androidsuperpoderes.testUtils.fakes.FakeLocalDataSource
import com.isabri.androidsuperpoderes.testUtils.fakes.FakeRemoteDataSource
import com.isabri.androidsuperpoderes.ui.comicsList.ComicsListViewModel
import com.isabri.androidsuperpoderes.ui.seriesList.SeriesListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SeriesListViewModelTests {

    lateinit var fakeLocalDataSource: FakeLocalDataSource
    lateinit var fakeRemoteDataSource: FakeRemoteDataSource
    lateinit var repository: Repository
    lateinit var sut: SeriesListViewModel

    @Before
    fun setUp() {
        fakeLocalDataSource = FakeLocalDataSource()
        fakeRemoteDataSource = FakeRemoteDataSource()
        repository = RepositoryImpl(fakeRemoteDataSource, fakeLocalDataSource)
    }

    @Test
    fun `GIVEN a list of series locally stored WHEN init sut THEN series are available`() {
        runTest {
            // GIVEN
            val characterId = 0
            fakeLocalDataSource.setFlagValue(true)
            sut = SeriesListViewModel(repository, "0")
            // WHEN

            var seriesListState = SeriesListState.Success(listOf(FakeSerieData.getFakeSerie(0)))
            val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
                seriesListState =
                    SeriesListState.Success(
                        (repository.getSeries(characterId.toString()) as SeriesListState.Success).series)
            }
            // THEN
            Truth.assertThat(seriesListState.series[0].title).isEqualTo("fakeTitleEntity")
            // FINALLY
            collectJob.cancel()
        }
    }
}