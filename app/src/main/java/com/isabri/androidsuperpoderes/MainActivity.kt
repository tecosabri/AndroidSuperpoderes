package com.isabri.androidsuperpoderes

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.isabri.androidsuperpoderes.di.ViewModelFactoryProvider
import com.isabri.androidsuperpoderes.ui.charactersList.CharactersListView
import com.isabri.androidsuperpoderes.ui.comicsList.ComicsListView
import com.isabri.androidsuperpoderes.ui.seriesList.SeriesListView
import com.isabri.androidsuperpoderes.ui.seriesList.SeriesListViewModel
import com.isabri.androidsuperpoderes.ui.theme.AndroidSuperpoderesTheme
import com.isabri.androidsuperpoderes.utils.Constant
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors

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
//                        composable(route = Constant.NAV_CHARACTERS){
//                            CharactersListView { id ->
//                                navController.navigate("${Constant.NAV_SERIES}/$id")
//                            }
//                        }
//                        composable(
//                            route = "${Constant.NAV_SERIES}/{${Constant.PATH_CHARACTER_ID}}",
//                            arguments = listOf(navArgument(Constant.PATH_CHARACTER_ID) {
//                                this.type = NavType.IntType
//                            })
//                        ){
//                            val id = it.arguments?.getInt(Constant.PATH_CHARACTER_ID) ?: -1
//                            SeriesListView(characterId = id.toString())
//                        }
                        composable(route = Constant.NAV_CHARACTERS){
                            CharactersListView { id ->
                                navController.navigate("${Constant.NAV_COMICS}/$id")
                            }
                        }
                        composable(
                            route = "${Constant.NAV_COMICS}/{${Constant.PATH_CHARACTER_ID}}",
                            arguments = listOf(navArgument(Constant.PATH_CHARACTER_ID) {
                                this.type = NavType.IntType
                            })
                        ){
                            val id = it.arguments?.getInt(Constant.PATH_CHARACTER_ID) ?: -1
                            ComicsListView(characterId = id.toString())
                        }
                    }
                }
            }
        }
    }
}

