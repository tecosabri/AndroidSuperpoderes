package com.isabri.androidsuperpoderes.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constant {

    companion object {
        // ENDPOINTS
        const val BASE_URL = "https://gateway.marvel.com"
        const val CHARACTERS_ENDPOINT = "/v1/public/characters"

        // URL QUERY PARAMETERS
        const val NAME = "name"
        const val RESULTS_LIMIT = 100

        // KEYS
        val ts = Timestamp(System.currentTimeMillis()).time.toString()
        const val PUBLIC_KEY = "5ed7dc76b420bc84a2d4a15450c50f4a"
        private const val PRIVATE_KEY = "106d4a90fd6df27742659f14d4c4a26015917214"

        // SHARED PREFERENCES
        const val SHARED_PREFERENCES_NAME = "sharedPreferencesName"

        private fun getMD5(input:String): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }

        fun getHash(): String {
            return getMD5("$ts$PRIVATE_KEY$PUBLIC_KEY")
        }
    }
}