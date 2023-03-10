package com.isabri.androidsuperpoderes.ui.charactersList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isabri.androidsuperpoderes.domain.Repository
import com.isabri.androidsuperpoderes.domain.models.Character
import com.isabri.androidsuperpoderes.utils.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private var _characters = MutableStateFlow(emptyList<Character>())
    val characters: StateFlow<List<Character>>
    get() = _characters

    private var _fetchingError = MutableStateFlow(Constant.ERR_NONE)
    val fetchingError: StateFlow<String>
        get() = _fetchingError

    init {
        getCharacters()
    }

    fun getCharacters(favorites: Boolean = false) {
        if(!favorites) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getCharacters().collect() {
                    if (it.isEmpty()) _fetchingError.value = Constant.ERR_CHARACTERS_FETCHING
                    if (it.isNotEmpty()) _characters.value = it
                }
            }
        }
        if(favorites) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getFavoriteCharacters().collect() {
                    _characters.value = it
                }
            }
        }
    }
}