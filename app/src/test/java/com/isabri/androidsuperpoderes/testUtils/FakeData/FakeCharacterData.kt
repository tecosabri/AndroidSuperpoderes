package com.isabri.androidsuperpoderes.testUtils.FakeData

import com.isabri.androidsuperpoderes.data.local.models.CharacterEntity
import com.isabri.androidsuperpoderes.data.mappers.CharacterMapper
import com.isabri.androidsuperpoderes.data.remote.models.Thumbnail
import com.isabri.androidsuperpoderes.data.remote.models.character.CharacterRemote
import com.isabri.androidsuperpoderes.data.remote.models.character.SeriesList
import com.isabri.androidsuperpoderes.data.remote.models.character.SeriesSummary
import com.isabri.androidsuperpoderes.domain.models.Character
import com.isabri.androidsuperpoderes.domain.models.Serie

class FakeCharacterData {

    companion object {
        fun getFakeEntityCharacter(id: Int = 0): CharacterEntity {
            return CharacterEntity(id, "fakeNameEntity", "fakeDescriptionEntity", getFakeThumbnail(), false)
        }
        fun getFakeEntityCharacters(): List<CharacterEntity> {
            return (0 until 10).map { getFakeEntityCharacter(it) }
        }

        fun getFakeRemoteCharacter(id: Int = 0): CharacterRemote {
            return CharacterRemote(id, "fakeNameRemote", "fakeDescriptionRemote", getFakeThumbnail(), null)
        }
        fun getFakeRemoteCharacters(): List<CharacterRemote> {
            return (0 until 10).map { getFakeRemoteCharacter(it) }
        }

        fun getFakeCharacter(id: Int = 0): Character {
            return Character(id, "fakeName", "fakeDescription", getFakeThumbnail(), null, false)
        }
        fun getFakeCharacters(): List<Character> {
            return (0 until 10).map { getFakeCharacter(it) }
        }

        fun getFakeThumbnail(): Thumbnail {
            return Thumbnail("fakePath", "fakeExtension")
        }

    }

}