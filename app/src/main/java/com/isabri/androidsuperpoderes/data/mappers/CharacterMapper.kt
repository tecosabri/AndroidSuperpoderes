package com.isabri.androidsuperpoderes.data.mappers

import com.isabri.androidsuperpoderes.data.remote.models.character.CharacterRemote
import com.isabri.androidsuperpoderes.domain.models.Character

class CharacterMapper {

    companion object {

        fun mapRemoteCharactersToCharacters(charactersRemote: List<CharacterRemote>): List<Character> {
            return charactersRemote.map { mapRemoteCharacterToCharacter(it) }
        }
        private fun mapRemoteCharacterToCharacter(characterRemote: CharacterRemote): Character {
            return Character(
                characterRemote.id,
                characterRemote.name,
                characterRemote.description,
                characterRemote.thumbnail,
                characterRemote.series)
        }


    }
}