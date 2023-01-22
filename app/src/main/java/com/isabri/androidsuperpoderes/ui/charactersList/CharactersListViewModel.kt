package com.isabri.androidsuperpoderes.ui.charactersList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isabri.androidsuperpoderes.data.remote.models.states.CharactersListState
import com.isabri.androidsuperpoderes.domain.Repository
import com.isabri.androidsuperpoderes.domain.models.Character
import com.isabri.androidsuperpoderes.utils.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _characters = MutableStateFlow(emptyList<Character>())
    val characters: StateFlow<List<Character>>
    get() = _characters

    private val _fetchingError = MutableStateFlow(Constant.ERR_NONE)
    val fetchingError: StateFlow<String>
        get() = _fetchingError

    init {
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launch {
            val charactersListState = repository.getCharacters()
            when(charactersListState) {
                is CharactersListState.Failure -> _fetchingError.value = Constant.ERR_CHARACTERS_FETCHING
                is CharactersListState.Success -> _characters.value = charactersListState.characters
            }
        }
    }
}