package com.isabri.androidsuperpoderes.tests.utils

import com.isabri.androidsuperpoderes.utils.Constant
import org.junit.Test

import org.junit.Assert.*

class ConstantTests {
    @Test
    fun `getHash() WHEN getHash() THEN expect MD5 containing 32 characters`() {
        // GIVEN
        // WHEN
        val hash = Constant.getHash()
        // THEN
        assertEquals(hash.length, 32)
    }

    @Test
    fun `getRandomCharacter() WHEN getRandomCharacter() THEN expect valid character`() {
        // GIVEN
        // WHEN
        val randomCharacter = Constant.getRandomCharacter()
        // THEN
        assertEquals(randomCharacter.id, 0)
        assertEquals(randomCharacter.name, "randomName")
        assertEquals(randomCharacter.description, "randomDescription")
        assertEquals(randomCharacter.thumbnail?.path, "https://upload.wikimedia.org/wikipedia/commons/9/90/Spiderman")
        assertEquals(randomCharacter.favorite, false)

    }
}