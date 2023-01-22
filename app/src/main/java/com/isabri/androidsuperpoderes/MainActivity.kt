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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.isabri.androidsuperpoderes.di.ViewModelFactoryProvider
import com.isabri.androidsuperpoderes.ui.charactersList.CharactersListView
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
                        composable(Constant.NAV_CHARACTERS){
                            CharactersListView {
                                navController.navigate(Constant.NAV_SERIES)
                            }
                        }
                        composable(Constant.NAV_SERIES){
                            val factory = EntryPointAccessors.fromActivity(
                                LocalContext.current as Activity,
                                ViewModelFactoryProvider::class.java
                            ).seriesListViewModelFactory()
                            val seriesListViewModel: SeriesListViewModel = viewModel(factory = SeriesListViewModel.provideSeriesListViewModelFactory(factory, "1011334"))
                        }
                    }
                }
            }
        }
    }
}

