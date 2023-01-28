package com.isabri.androidsuperpoderes.tests.charactersListWithScaffold

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import com.isabri.androidsuperpoderes.testUtils.FakeData.FakeCharacterData
import com.isabri.androidsuperpoderes.ui.charactersList.CharactersListView
import org.junit.Rule
import org.junit.Test

class CharactersListViewTests {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun givenCharactersList_whenClickedCharacter_navigationActionIsPerformed(){
        // GIVEN
        composeRule.setContent {
            CharactersListView(
                characters = mutableStateOf(FakeCharacterData.getFakeCharacters()),
                onClicked = {}
            )
        }
        // WHEN clicked
        // THEN
        composeRule.onAllNodesWithText("fakeName").onFirst().assertHasClickAction()
    }

    @Test
    fun givenCharactersList_whenLoaded_atLeastOneCharacterIsShown(){
        // GIVEN
        composeRule.setContent {
            CharactersListView(
                characters = mutableStateOf(FakeCharacterData.getFakeCharacters()),
                onClicked = {}
            )
        }
        // WHEN clicked
        // THEN
        composeRule.onAllNodesWithText("fakeName").onFirst().assertExists()
    }
}