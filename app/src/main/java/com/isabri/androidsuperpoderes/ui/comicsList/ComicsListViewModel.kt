package com.isabri.androidsuperpoderes.ui.comicsList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.isabri.androidsuperpoderes.data.remote.models.states.ComicsListState
import com.isabri.androidsuperpoderes.domain.Repository
import com.isabri.androidsuperpoderes.domain.models.Comic
import com.isabri.androidsuperpoderes.domain.models.Serie
import com.isabri.androidsuperpoderes.utils.Constant
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ComicsListViewModel @AssistedInject constructor(private val repository: Repository, @Assisted private val characterId: String): ViewModel() {

    private val _comics = MutableStateFlow(emptyList<Comic>())
    val comics: StateFlow<List<Comic>>
        get() = _comics

    private val _fetchingError = MutableStateFlow(Constant.ERR_NONE)
    val fetchingError: StateFlow<String>
        get() = _fetchingError

    init {
        getComics(characterId)
    }

    fun getComics(characterId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val comicsListState = repository.getComics(characterId)
            when(comicsListState) {
                is ComicsListState.Failure -> _fetchingError.value = Constant.ERR_SERIES_FETCHING
                is ComicsListState.Success -> {
                    _comics.value = comicsListState.comics
                }
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(characterId: String): ComicsListViewModel
    }

    companion object {
        fun provideComicsListViewModelFactory(factory: Factory, characterId: String): ViewModelProvider.Factory {
            return object: ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(characterId) as T
                }
            }
        }
    }

}