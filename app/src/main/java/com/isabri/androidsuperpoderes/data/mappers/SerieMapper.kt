package com.isabri.androidsuperpoderes.data.mappers

import com.isabri.androidsuperpoderes.data.local.models.SerieEntity
import com.isabri.androidsuperpoderes.data.remote.models.SerieRemote
import com.isabri.androidsuperpoderes.domain.models.Serie

class SerieMapper {

    companion object {
        fun mapSeriesRemoteToSeries(seriesRemote: List<SerieRemote>): List<Serie> {
            return seriesRemote.map { mapSerieRemoteToSerie(it) }
        }
        private fun mapSerieRemoteToSerie(serieRemote: SerieRemote): Serie {
            return Serie(serieRemote.id, serieRemote.title, serieRemote.thumbnail, serieRemote.description)
        }

        fun mapSerieEntitiesToSeries(serieEntities: List<SerieEntity>): List<Serie> {
            return serieEntities.map { mapSerieEntityToSerie(it) }
        }
        private fun mapSerieEntityToSerie(serieEntity: SerieEntity): Serie {
            return Serie(serieEntity.id, serieEntity.title, serieEntity.thumbnail, serieEntity.description)
        }


        fun mapSeriesToSerieEntities(series: List<Serie>, characterId: String): List<SerieEntity> {
            return series.map { mapSerieToSerieEntity(it, characterId) }
        }
        private fun mapSerieToSerieEntity(serie: Serie, characterId: String): SerieEntity {
            return SerieEntity(
                serie.id,
                characterId.toInt(),
                serie.title,
                serie.thumbnail,
                serie.description
            )
        }
    }
}