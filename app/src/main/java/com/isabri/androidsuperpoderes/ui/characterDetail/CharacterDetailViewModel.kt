package com.isabri.androidsuperpoderes.ui.characterDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.isabri.androidsuperpoderes.domain.Repository
import com.isabri.androidsuperpoderes.domain.models.Character
import com.isabri.androidsuperpoderes.utils.Constant
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CharacterDetailViewModel @AssistedInject constructor(private val repository: Repository, @Assisted private val characterId: String): ViewModel() {

    private val _character = MutableStateFlow(Constant.getRandomCharacter())
    val character: StateFlow<Character>
        get() = _character

    private val _fetchingError = MutableStateFlow(Constant.ERR_NONE)
    val fetchingError: StateFlow<String>
        get() = _fetchingError

    init {
        getCharacter(characterId)
    }

    fun getCharacter(characterId: String) {
        viewModelScope.launch {
            repository.getCharacter(characterId).collect {
                _character.value = it.get(0)
            }
        }
    }

    fun onClickFavorite(favorite: Boolean) {
        setFavorite(favorite)
    }

    private fun setFavorite(favorite: Boolean) {
        val newCharacter = _character.value.copy(favorite = favorite)
        repository.updateCharacter(newCharacter) // Hot flow updates character reactively
    }

    @AssistedFactory
    interface Factory {
        fun create(characterId: String): CharacterDetailViewModel
    }

    companion object {
        fun provideCharacterViewModelFactory(factory: Factory, characterId: String): ViewModelProvider.Factory {
            return object: ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(characterId) as T
                }
            }
        }
    }
}