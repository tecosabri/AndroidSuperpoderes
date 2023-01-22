package com.isabri.androidsuperpoderes.ui.seriesList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.isabri.androidsuperpoderes.data.remote.models.states.SeriesListState
import com.isabri.androidsuperpoderes.domain.Repository
import com.isabri.androidsuperpoderes.domain.models.Serie
import com.isabri.androidsuperpoderes.utils.Constant
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class SeriesListViewModel @AssistedInject constructor(private val repository: Repository, @Assisted private val characterId: String): ViewModel() {

    private val _series = MutableStateFlow(emptyList<Serie>())
    val series: StateFlow<List<Serie>>
        get() = _series

    private val _fetchingError = MutableStateFlow(Constant.ERR_NONE)
    val fetchingError: StateFlow<String>
        get() = _fetchingError

    init {
        getSeries(characterId)
    }

    fun getSeries(characterId: String) {
        viewModelScope.launch {
            val seriesListState = repository.getSeries(characterId)
            when(seriesListState) {
                is SeriesListState.Failure -> _fetchingError.value = Constant.ERR_SERIES_FETCHING
                is SeriesListState.Success -> {
                    _series.value = seriesListState.series
                    Log.d("TAG", "${series.value.toString()}\n\n\n")
                }
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(characterId: String): SeriesListViewModel
    }

    companion object {
        fun provideSeriesListViewModelFactory(factory: Factory, characterId: String): ViewModelProvider.Factory {
            return object: ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(characterId) as T
                }
            }
        }
    }

}