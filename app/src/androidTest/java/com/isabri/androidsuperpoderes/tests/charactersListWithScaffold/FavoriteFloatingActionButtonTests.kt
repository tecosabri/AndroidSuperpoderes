package com.isabri.androidsuperpoderes.tests.charactersListWithScaffold

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import com.isabri.androidsuperpoderes.ui.charactersList.CharactersListWithScaffold
import com.isabri.androidsuperpoderes.ui.charactersList.FavoriteFloatingActionButton
import kotlinx.coroutines.delay
import org.junit.Rule
import org.junit.Test

class FavoriteFloatingActionButtonTests {


    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun givenFavoriteFloatingActionButtonWithFalseFavorite_whenAppearing_isDisabled() {
        // GIVEN
        composeRule.setContent {
            FavoriteFloatingActionButton(favorite = mutableStateOf(false)) {
            }
        }
        // WHEN
        val favoriteButton = composeRule.onNodeWithContentDescription("Not favorite")

        // THEN
        favoriteButton.assertExists()
    }

    @Test
    fun givenFavoriteFloatingActionButtonWithTrueFavorite_whenAppearing_isEnabled() {
        // GIVEN
        composeRule.setContent {
            FavoriteFloatingActionButton(favorite = mutableStateOf(true)) {
            }
        }
        // WHEN
        val favoriteButton = composeRule.onNodeWithContentDescription("Favorite")

        // THEN
        favoriteButton.assertExists()
    }
}