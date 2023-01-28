package com.isabri.androidsuperpoderes.tests.characterDetail

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.isabri.androidsuperpoderes.ui.characterDetail.NameAndFavoriteRow
import org.junit.Rule
import org.junit.Test

class NameAndFavoriteRowTests {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun givenNameAndFavoriteRow_whenFavorite_favoriteEnabled() {
        // GIVEN
        composeRule.setContent {

            NameAndFavoriteRow(name = "name", favorite = true, onClickFavorite = {})
        }
        // WHEN
        val favoriteButton = composeRule.onNodeWithContentDescription("Favorite")
        // THEN
        favoriteButton.assertExists()
    }

    @Test
    fun givenNameAndFavoriteRow_whenNotFavorite_favoriteDisabled() {
        // GIVEN
        composeRule.setContent {

            NameAndFavoriteRow(name = "name", favorite = false, onClickFavorite = {})
        }
        // WHEN
        val favoriteButton = composeRule.onNodeWithContentDescription("Not favorite")
        // THEN
        favoriteButton.assertExists()
    }
}