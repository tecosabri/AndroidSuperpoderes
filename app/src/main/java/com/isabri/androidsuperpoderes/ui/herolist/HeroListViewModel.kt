package com.isabri.androidsuperpoderes.ui.herolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isabri.androidsuperpoderes.data.remote.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(private val remoteDataSource: RemoteDataSource): ViewModel() {

    fun getCharacters() {
        viewModelScope.launch {
            remoteDataSource.getCharacters()
        }
    }
}