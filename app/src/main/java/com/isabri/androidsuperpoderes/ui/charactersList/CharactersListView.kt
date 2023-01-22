package com.isabri.androidsuperpoderes.ui.charactersList

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

@Composable
fun CharactersListView(onClicked: () -> Unit) {
    val charactersListViewModel = hiltViewModel<CharactersListViewModel>()
    val characters = charactersListViewModel.characters.collectAsState()
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(characters.value) { _, character ->
            character.name?.apply {
                character.thumbnail?.path?.apply {
                    DetailView(label = character.name, photoURL = character.thumbnail.completePath) {
                        onClicked()
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}



