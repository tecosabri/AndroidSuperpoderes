package com.isabri.androidsuperpoderes.ui.herolist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.isabri.androidsuperpoderes.ui.detail.DetailView

@Preview
@Composable
fun CharactersListView(characters: List<String> = listOf("Spiderman", "Wolverine", "Other")) {
    val heroListViewModel = hiltViewModel<HeroListViewModel>()
    val heros = heroListViewModel.characters.collectAsState()
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(heros.value) { _, hero ->
            hero.name?.apply {
                hero.thumbnail?.path?.apply {
                    DetailView(label = hero.name, photoURL = hero.thumbnail.completePath) {
                        heroListViewModel.getCharacters()
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}



