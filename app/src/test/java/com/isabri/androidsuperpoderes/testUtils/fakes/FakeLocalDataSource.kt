package com.isabri.androidsuperpoderes.testUtils.fakes

import com.isabri.androidsuperpoderes.data.local.LocalDataSource
import com.isabri.androidsuperpoderes.data.local.models.CharacterEntity
import com.isabri.androidsuperpoderes.data.local.models.ComicEntity
import com.isabri.androidsuperpoderes.data.local.models.SerieEntity
import com.isabri.androidsuperpoderes.domain.models.Comic
import com.isabri.androidsuperpoderes.domain.models.Serie
import com.isabri.androidsuperpoderes.testUtils.FakeData.FakeCharacterData
import com.isabri.androidsuperpoderes.testUtils.FakeData.FakeComicData
import com.isabri.androidsuperpoderes.testUtils.FakeData.FakeSerieData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLocalDataSource: LocalDataSource {

    var flag = false
    var fakeLocalCharacterEntities = mutableListOf<CharacterEntity>()
    var fakeLocalSerieEntities = mutableListOf<SerieEntity>()
    var fakeLocalComicEntities = mutableListOf<ComicEntity>()


    fun getFlagValue(): Boolean {
        return flag
    }
    fun setFlagValue(flag: Boolean) {
        this.flag = flag
    }

    override fun countCharacters(): Int {
        TODO("Not yet implemented")
    }

    override fun getCharactersFlow(): Flow<List<CharacterEntity>> {
        return if(flag) flow {
            val entityCharacters = FakeCharacterData.getFakeEntityCharacters()
            emit(entityCharacters)
        } else flow { emit(emptyList()) }
    }

    override fun getCharacter(characterId: String): Flow<List<CharacterEntity>> {
        return flow {
            val entityCharacters = FakeCharacterData.getFakeEntityCharacters().filter { it.id == characterId.toInt() }
            emit(entityCharacters)
        }
    }

    override fun getFavoriteCharacters(): Flow<List<CharacterEntity>> {
        return flow {
            val entityCharacters = FakeCharacterData.getFakeEntityCharacters().map { it.copy(favorite = true) }
            emit(entityCharacters)
        }
    }

    override fun updateCharacter(characterEntity: CharacterEntity) {
        fakeLocalCharacterEntities.add(characterEntity)
    }

    override fun insertCharacters(characterEntities: List<CharacterEntity>) {
        characterEntities.forEach {
            fakeLocalCharacterEntities.add(it)
        }
    }

    override fun getSeriesByCharacterId(characterId: String): List<SerieEntity> {
        return if(flag) FakeSerieData.getFakeEntitySeries(characterId.toInt())
        else { emptyList() }
    }

    override fun insertSeries(serieEntities: List<SerieEntity>) {
        serieEntities.forEach {
            fakeLocalSerieEntities.add(it)
        }
    }

    override fun getComicsByCharacterId(characterId: String): List<ComicEntity> {
        return if(flag) FakeComicData.getFakeEntityComics(characterId.toInt())
        else { emptyList() }
    }

    override fun insertComics(comicEntities: List<ComicEntity>) {
        comicEntities.forEach {
            fakeLocalComicEntities.add(it)
        }
    }
}