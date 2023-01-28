package com.isabri.androidsuperpoderes.ui.charactersList

import android.util.Log
import com.isabri.androidsuperpoderes.domain.models.Character
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.isabri.androidsuperpoderes.ui.components.DetailView
import com.keepcoding.androidsuperpoderes.R

@Composable
fun CharactersListWithScaffold(onClickedCharacter: (Int) -> Unit) {
    val charactersListViewModel = hiltViewModel<CharactersListViewModel>()
    val characters = charactersListViewModel.characters.collectAsState()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
    val favorite = remember { mutableStateOf(false) }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FavoriteFloatingActionButton(favorite = favorite) {
                charactersListViewModel.getCharacters(favorite.value)
            } }
    ) {
        Box {
            Column {
                TopAppBar(
                    title = {Text(
                        text = "Marvel Heroes",
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier
                            .background(Color.Transparent)
                            .fillMaxSize()
                            .padding(vertical = 5.dp)
                    )},
                    backgroundColor = Color.Transparent,
                    elevation = 0.dp
                )
                CharactersListView(characters, onClicked = onClickedCharacter)
            }
        }
    }
}

@Composable
fun FavoriteFloatingActionButton(favorite: MutableState<Boolean>, onClick: () -> (Unit)) {
    // Floating action button: represents the main action in the view. This is the case
    // of the "directions" button of the google maps. In this case, the action assigned
    // to it is the filtering of the list by liked characters.
    FloatingActionButton(
        backgroundColor = Color.White,
        modifier = Modifier.border(BorderStroke(1.dp, Color.LightGray), CircleShape),
        onClick = {
            favorite.value = !favorite.value
            onClick()
        }
    ){
        Image(
            painter = if(favorite.value) painterResource(id = R.drawable.ic_favorite) else painterResource(id = R.drawable.ic_not_favorite),
            contentDescription = if (favorite.value) "Favorite" else "Not favorite"
        )
    }
}

@Composable
fun CharactersListView(characters: State<List<Character>>, onClicked: (Int) -> Unit) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(characters.value) { _, character ->
            character.name?.apply {
                character.thumbnail?.path?.apply {
                    Log.d("HERO", "${character.name} has URL: ${character.thumbnail.completePath}")

                    DetailView(label = character.name, photoURL = character.thumbnail.completePath) {
                        character.id?.apply {
                            onClicked(this)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}



