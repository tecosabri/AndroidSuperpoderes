package com.isabri.androidsuperpoderes.ui.characterDetail

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isabri.androidsuperpoderes.di.ViewModelFactoryProvider
import com.isabri.androidsuperpoderes.ui.components.DetailImage
import com.isabri.androidsuperpoderes.utils.Constant
import com.keepcoding.androidsuperpoderes.R
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
            NameAndFavoriteRow(name = character.value.name.toString(), character.value.favorite) {
                characterDetailViewModel.onClickFavorite(it)
            }
            NavigationButtonsRow(onClickedButton)
            DescriptionText(text = character.value.description.toString())
        }
    }
}

@Composable
fun NameAndFavoriteRow(name: String, favorite: Boolean, onClickFavorite: (Boolean) -> Unit) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Row(
        modifier = Modifier
            .width(screenWidth - 20.dp)
            .background(Color.Transparent)
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        Text(
            text = name,
            color = Color.White,
            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Light),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 20.dp)
        )
        Image(
            painter = if(favorite) painterResource(id = R.drawable.ic_favorite) else painterResource(id = R.drawable.ic_not_favorite),
            contentDescription = if (favorite) "Favorite" else "Not favorite",
            modifier = Modifier
                .weight(1f)
                .clickable { onClickFavorite(!favorite) } // Toggle favorite
        )
    }
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
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Row(
        modifier = Modifier
            .width(screenWidth)
            .background(Color.Transparent)
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly){
        NavigationButton(text = "Series") { onClickedButton(Constant.NAV_SERIES) }
        NavigationButton(text = "Comics") { onClickedButton(Constant.NAV_COMICS) }
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