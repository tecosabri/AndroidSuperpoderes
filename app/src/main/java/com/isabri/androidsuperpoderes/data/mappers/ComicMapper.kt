package com.isabri.androidsuperpoderes.data.mappers

import com.isabri.androidsuperpoderes.data.remote.models.ComicRemote
import com.isabri.androidsuperpoderes.domain.models.Comic

class ComicMapper {

    companion object {

        fun mapRemoteComicsToComics(comicsRemote: List<ComicRemote>): List<Comic> {
            return comicsRemote.map { mapRemoteComicToComic(it) }
        }
        private fun mapRemoteComicToComic(comicRemote: ComicRemote): Comic {
            return Comic(
                comicRemote.id,
                comicRemote.title,
                comicRemote.thumbnail,
                comicRemote.description
            )
        }


    }
}