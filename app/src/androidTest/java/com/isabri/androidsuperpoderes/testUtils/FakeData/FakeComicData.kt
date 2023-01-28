package com.isabri.androidsuperpoderes.testUtils.FakeData

import com.isabri.androidsuperpoderes.data.local.models.CharacterEntity
import com.isabri.androidsuperpoderes.data.local.models.ComicEntity
import com.isabri.androidsuperpoderes.data.remote.models.ComicRemote
import com.isabri.androidsuperpoderes.domain.models.Comic

class FakeComicData {

    companion object {

        fun getFakeEntityComic(id: Int = 0, characterId: Int): ComicEntity {
            return ComicEntity(id, characterId, "fakeTitleEntity", FakeCharacterData.getFakeThumbnail(), "fakeDescriptionEntity")
        }
        fun getFakeEntityComics(characterId: Int): List<ComicEntity> {
            return (0 until 10).map {
                getFakeEntityComic(it, characterId)
            }
        }

        fun getFakeRemoteComic(id: Int = 0): ComicRemote {
            return ComicRemote(id, "fakeTitleRemote", FakeCharacterData.getFakeThumbnail(), "fakeDescriptionRemote")
        }
        fun getFakeRemoteComics(): List<ComicRemote> {
            return (0 until 10).map {
                getFakeRemoteComic(it)
            }
        }

        fun getFakeComic(id: Int = 0): Comic {
            return Comic(id, "fakeTitle", FakeCharacterData.getFakeThumbnail(), "fakeDescription")
        }
        fun getFakeeComics(): List<Comic> {
            return (0 until 10).map {
                getFakeComic(it)
            }
        }
    }
}