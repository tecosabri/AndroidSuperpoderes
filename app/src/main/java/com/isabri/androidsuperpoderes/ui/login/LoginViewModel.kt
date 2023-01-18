package com.isabri.androidsuperpoderes.ui.login

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isabri.androidsuperpoderes.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Credentials
import java.nio.charset.StandardCharsets
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: Repository, private val sharedPreferences: SharedPreferences): ViewModel() {

    fun login(email: String, password: String) {
        if(sharedPreferences.getString("TOKEN", null) == null) {
            sharedPreferences.edit().putString("CREDENTIAL", getCredentials(email, password)).apply()
            getToken()
        } else {
            Log.d("TOKEN", "The token is ${sharedPreferences.getString("TOKEN", null)}")
        }
    }

    private fun getToken() {
        viewModelScope.launch {
            val token = withContext(Dispatchers.IO) {
                repository.getToken()
            }
            sharedPreferences.edit().putString("TOKEN", token).apply()
        }
    }

    private fun getCredentials(email: String, password: String): String {
        return Credentials.basic(email, password, StandardCharsets.UTF_8)
    }
}