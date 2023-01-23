package com.isabri.androidsuperpoderes.ui.characterDetail

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isabri.androidsuperpoderes.di.ViewModelFactoryProvider
import com.isabri.androidsuperpoderes.ui.components.DetailImage
import com.isabri.androidsuperpoderes.utils.Constant
import dagger.hilt.android.EntryPointAccessors


@Composable
fun CharacterDetailView(characterId: String, onClickedButton: (String) -> Unit) {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        ViewModelFactoryProvider::class.java
    ).characterDetailViewModelFactory()
    val characterDetailViewModel: CharacterDetailViewModel = viewModel(factory = CharacterDetailViewModel.provideCharacterViewModelFactory(factory, characterId))
    val character = characterDetailViewModel.character.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize(1f),
        contentAlignment = Alignment.BottomCenter
    ) {
        DetailImage(photoURL = character.value.thumbnail?.completePath.toString())
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            NameText(name = character.value.name.toString())
            NavigationButtonsRow(onClickedButton)
            DescriptionText(text = character.value.description.toString())
        }
    }
}

@Composable
fun NameText(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.h4,
        color = Color.White,
        modifier = Modifier
            .padding(20.dp)
    )

}

@Composable
fun DescriptionText(text: String) {
    Text(
        text = text,
        color = Color.White,
        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Light),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(20.dp)
    )
}

@Composable
fun NavigationButtonsRow(onClickedButton: (String) -> Unit) {
    Row(
        modifier = Modifier
            .width(500.dp)
            .background(Color.Transparent)
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly){
        NavigationButton(text = "Series") {
            onClickedButton(Constant.NAV_SERIES)
            Log.d("SERIES", "Series")
        }
        NavigationButton(text = "Comics") {
            onClickedButton(Constant.NAV_COMICS)
            Log.d("Comics", "Comics")
        }
    }
}

@Composable
fun NavigationButton(text: String, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .width(100.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
        border = BorderStroke(1.dp, Color.White)){
        Text(
            text = text,
            color = Color.White
        )
    }
}