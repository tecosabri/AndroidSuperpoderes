package com.isabri.androidsuperpoderes


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.isabri.androidsuperpoderes.ui.characterDetail.CharacterDetailView
import com.isabri.androidsuperpoderes.ui.charactersList.CharactersListView
import com.isabri.androidsuperpoderes.ui.charactersList.CharactersListWithScaffold
import com.isabri.androidsuperpoderes.ui.comicsList.ComicsListView
import com.isabri.androidsuperpoderes.ui.seriesList.SeriesListView
import com.isabri.androidsuperpoderes.ui.theme.AndroidSuperpoderesTheme
import com.isabri.androidsuperpoderes.utils.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidSuperpoderesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Constant.NAV_CHARACTERS){
                        // CHARACTERS LIST VIEW (ENTRY POINT)
                        composable(route = Constant.NAV_CHARACTERS){
                            CharactersListWithScaffold { id ->
                                navController.navigate("${Constant.NAV_CHARACTER}/$id")
                            }
                        }
                        // CHARACTER DETAIL VIEW (NAVIGATES TO COMICS OR SERIES)
                        composable(
                            route = "${Constant.NAV_CHARACTER}/{${Constant.PATH_CHARACTER_ID}}",
                            arguments = listOf(navArgument(Constant.PATH_CHARACTER_ID) {
                                this.type = NavType.IntType
                            })
                        ){
                            val id = it.arguments?.getInt(Constant.PATH_CHARACTER_ID) ?: -1
                            CharacterDetailView(characterId = id.toString()) { clickedButtonOption ->
                                navController.navigate("$clickedButtonOption/$id")
                            }
                        }
                        // COMICS LIST VIEW
                        composable(
                            route = "${Constant.NAV_COMICS}/{${Constant.PATH_CHARACTER_ID}}",
                            arguments = listOf(navArgument(Constant.PATH_CHARACTER_ID) {
                                this.type = NavType.IntType
                            })
                        ){
                            val id = it.arguments?.getInt(Constant.PATH_CHARACTER_ID) ?: -1
                            ComicsListView(characterId = id.toString())
                        }
                        // SERIES LIST VIEW
                        composable(
                            route = "${Constant.NAV_SERIES}/{${Constant.PATH_CHARACTER_ID}}",
                            arguments = listOf(navArgument(Constant.PATH_CHARACTER_ID) {
                                this.type = NavType.IntType
                            })
                        ){
                            val id = it.arguments?.getInt(Constant.PATH_CHARACTER_ID) ?: -1
                            SeriesListView(characterId = id.toString())
                        }
                    }
                }
            }
        }
    }
}

