package com.isabri.androidsuperpoderes.data.mappers

import com.isabri.androidsuperpoderes.data.local.models.ComicEntity
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

        fun mapComicEntitiesToComics(comicEntities: List<ComicEntity>): List<Comic> {
            return comicEntities.map { mapComicEntityToComic(it) }
        }
        private fun mapComicEntityToComic(comicEntity: ComicEntity): Comic {
            return Comic(comicEntity.id, comicEntity.title, comicEntity.thumbnail, comicEntity.description)
        }

        fun mapComicsToComicEntities(comics: List<Comic>, characterId: String): List<ComicEntity> {
            return comics.map { mapComicToComicEntity(it, characterId) }
        }
        private fun mapComicToComicEntity(comic: Comic, characterId: String): ComicEntity {
            return ComicEntity(comic.id, characterId.toInt(), comic.title, comic.thumbnail, comic.description)
        }
    }
}