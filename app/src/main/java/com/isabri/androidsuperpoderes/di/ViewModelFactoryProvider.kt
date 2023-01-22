package com.isabri.androidsuperpoderes.di

import com.isabri.androidsuperpoderes.ui.comicsList.ComicsListViewModel
import com.isabri.androidsuperpoderes.ui.seriesList.SeriesListViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ViewModelFactoryProvider {
    fun seriesListViewModelFactory(): SeriesListViewModel.Factory
    fun comicsListViewModelFactory(): ComicsListViewModel.Factory
}