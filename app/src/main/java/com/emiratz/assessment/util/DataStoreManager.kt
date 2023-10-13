package com.emiratz.assessment.util

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.emiratz.assessment.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_prefs")

class DataStoreManager(context: Context) {
    private val dataStore = context.dataStore

    suspend fun saveToken(token: String) {
        Log.i("Token", token)
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun getTokenValue(): String {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY] ?: ""
        }.first()
    }

    val getToken: Flow<String> = dataStore.data.map { preferences ->
        preferences[TOKEN_KEY] ?: ""
    }

    suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }

    suspend fun saveUserId(userId: Int) {
        Log.i("saveUserId", "test")
        dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
        }
    }

    suspend fun getUserIdValue(): Int {
        return dataStore.data.map { preferences ->
            preferences[USER_ID_KEY] ?: 0
        }.first()
    }

    val getUserId: Flow<Int> = dataStore.data.map { preferences ->
        preferences[USER_ID_KEY] ?: 0
    }

    suspend fun clearUserId() {
        dataStore.edit { preferences ->
            preferences.remove(USER_ID_KEY)
        }
    }

    suspend fun getUserData(): UserData {
        val token = getTokenValue()
        val userId = getUserIdValue()
        return UserData(token, userId)
    }

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val USER_ID_KEY = intPreferencesKey("user_id")
    }
}
