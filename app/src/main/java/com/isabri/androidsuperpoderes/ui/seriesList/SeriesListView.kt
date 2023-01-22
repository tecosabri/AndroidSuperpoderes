package com.isabri.androidsuperpoderes.ui.seriesList

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isabri.androidsuperpoderes.di.ViewModelFactoryProvider
import com.isabri.androidsuperpoderes.ui.detail.DetailView
import dagger.hilt.android.EntryPointAccessors


@Composable
fun SeriesListView(characterId: String) {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        ViewModelFactoryProvider::class.java
    ).seriesListViewModelFactory()
    val seriesListViewModel: SeriesListViewModel = viewModel(factory = SeriesListViewModel.provideSeriesListViewModelFactory(factory, characterId))
    val series = seriesListViewModel.series.collectAsState()

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(series.value) { _, serie ->
            serie.apply {
                serie.thumbnail?.path?.apply {
                    serie.title?.apply {
                        DetailView(label = this, photoURL = serie.thumbnail.completePath) {
                            Log.d("TAG", "Pressed serie $this")
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}


