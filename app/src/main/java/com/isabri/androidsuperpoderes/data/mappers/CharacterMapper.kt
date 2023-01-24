package com.isabri.androidsuperpoderes.data.mappers

import com.isabri.androidsuperpoderes.data.local.models.CharacterEntity
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
                characterRemote.series,
            false)
        }

        fun mapCharacterEntitiesToCharacters(characterEntities: List<CharacterEntity>): List<Character> {
            return characterEntities.map { mapCharacterEntityToCharacter(it) }
        }
        private fun mapCharacterEntityToCharacter(characterEntity: CharacterEntity): Character {
            return Character(
                characterEntity.id,
                characterEntity.name,
                characterEntity.description,
                characterEntity.thumbnail,
                null,
            characterEntity.favorite)
        }

        fun mapRemoteCharactersToCharacterEntities(charactersRemote: List<CharacterRemote>): List<CharacterEntity> {
            return charactersRemote.map { mapRemoteCharacterToCharacterEntity(it) }
        }
        private fun mapRemoteCharacterToCharacterEntity(characterRemote: CharacterRemote): CharacterEntity {
            return CharacterEntity(
                characterRemote.id,
                characterRemote.name,
                characterRemote.description,
                characterRemote.thumbnail,
                false
            )
        }

        fun mapCharactersToCharacterEntities(characters: List<Character>): List<CharacterEntity> {
            return characters.map { mapCharacterToCharacterEntity(it) }
        }
        fun mapCharacterToCharacterEntity(character: Character): CharacterEntity {
            return CharacterEntity(
                character.id,
                character.name,
                character.description,
                character.thumbnail,
                character.favorite
            )
        }

    }
}