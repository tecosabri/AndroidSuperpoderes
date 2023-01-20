package com.isabri.androidsuperpoderes.ui.herolist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.isabri.androidsuperpoderes.ui.detail.DetailView

@Preview
@Composable
fun CharactersListView(characters: List<String> = listOf("Spiderman", "Wolverine", "Other")) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(characters) { _, hero ->
            DetailView(label = hero, photoURL = "https://upload.wikimedia.org/wikipedia/ar/7/70/WOlverine.jpg")
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}



