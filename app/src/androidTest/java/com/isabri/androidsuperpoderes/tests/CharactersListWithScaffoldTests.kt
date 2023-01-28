package com.isabri.androidsuperpoderes.tests

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.isabri.androidsuperpoderes.ui.charactersList.CharactersListWithScaffold
import org.junit.Rule
import org.junit.Test

class CharactersListWithScaffoldTests {


    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun givenCharactersListWithScaffold_whenAppearing_favoriteButtonIsDisabled() {
        // GIVEN
        composeRule.setContent {
            CharactersListWithScaffold(onClickedCharacter = {})
        }

        // WHEN
        val favoriteButton = composeRule.onNodeWithTag("Not favorite")

        // THEN
        favoriteButton.assertExists()
    }
}