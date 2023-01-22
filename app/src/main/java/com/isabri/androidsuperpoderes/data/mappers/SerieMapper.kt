package com.isabri.androidsuperpoderes.data.mappers

import com.isabri.androidsuperpoderes.data.remote.models.SerieRemote
import com.isabri.androidsuperpoderes.domain.models.Serie

class SerieMapper {

    companion object {
        private fun mapSerieRemoteToSerie(serieRemote: SerieRemote): Serie {
            return Serie(serieRemote.id, serieRemote.title, serieRemote.thumbnail, serieRemote.description)
        }

        fun mapSeriesRemoteToSeries(seriesRemote: List<SerieRemote>): List<Serie> {
            return seriesRemote.map { mapSerieRemoteToSerie(it) }
        }
    }
}