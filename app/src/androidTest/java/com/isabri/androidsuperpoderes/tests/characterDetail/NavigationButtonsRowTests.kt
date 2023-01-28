package com.isabri.androidsuperpoderes.tests.characterDetail

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.isabri.androidsuperpoderes.ui.characterDetail.NameAndFavoriteRow
import com.isabri.androidsuperpoderes.ui.characterDetail.NavigationButtonsRow
import org.junit.Rule
import org.junit.Test

class NavigationButtonsRowTests {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun givenNavigationButtonsRow_whenLoaded_twoButtonsAppear() {
        // GIVEN
        composeRule.setContent {
            NavigationButtonsRow(onClickedButton = {})
        }
        // WHEN
        val seriesButton = composeRule.onNodeWithText("Series")
        val comicsButton = composeRule.onNodeWithText("Comics")

        // THEN
        seriesButton.assertExists()
        comicsButton.assertExists()
    }
}