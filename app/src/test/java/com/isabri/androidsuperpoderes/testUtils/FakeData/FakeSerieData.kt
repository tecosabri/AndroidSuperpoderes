package com.isabri.androidsuperpoderes.testUtils.FakeData

import com.isabri.androidsuperpoderes.data.local.models.SerieEntity
import com.isabri.androidsuperpoderes.data.remote.models.SerieRemote
import com.isabri.androidsuperpoderes.domain.models.Serie

class FakeSerieData {

    companion object {
        fun getFakeEntitySeries(characterId: Int): List<SerieEntity> {
            return (0 until 10).map { getFakeEntitySerie(it, characterId) }
        }
        fun getFakeEntitySerie(id: Int = 0, characterId: Int): SerieEntity {
            return SerieEntity(0, characterId,"fakeTitleEntity", FakeCharacterData.getFakeThumbnail(), "fakeDescriptionEntity")
        }

        fun getFakeRemoteSeries(): List<SerieRemote> {
            return (0 until 10).map { getFakeRemoteSerie(it) }
        }
        fun getFakeRemoteSerie(id: Int = 0): SerieRemote {
            return SerieRemote(0,"fakeTitleEntity", FakeCharacterData.getFakeThumbnail(), "fakeDescriptionEntity")
        }

        fun getFakeSeries(): List<Serie> {
            return (0 until 10).map { getFakeSerie(it) }
        }
        fun getFakeSerie(id: Int = 0): Serie {
            return Serie(0, "fakeTitle", FakeCharacterData.getFakeThumbnail(), "fakeDescription")
        }
    }
}