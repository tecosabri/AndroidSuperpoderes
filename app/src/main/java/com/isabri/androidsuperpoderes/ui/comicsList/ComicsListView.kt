package com.isabri.androidsuperpoderes.ui.comicsList

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
import com.isabri.androidsuperpoderes.ui.components.DetailView
import dagger.hilt.android.EntryPointAccessors

@Composable
fun ComicsListView(characterId: String) {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        ViewModelFactoryProvider::class.java
    ).comicsListViewModelFactory()
    val comicsListViewModel: ComicsListViewModel = viewModel(factory = ComicsListViewModel.provideComicsListViewModelFactory(factory, characterId))
    val comics = comicsListViewModel.comics.collectAsState()

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(comics.value) { _, comic ->
            comic.apply {
                comic.thumbnail?.path?.apply {
                    comic.title?.apply {
                        DetailView(label = this, photoURL = comic.thumbnail.completePath) {
                            Log.d("TAG", "Pressed comic $this")
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}


