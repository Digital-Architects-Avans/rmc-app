package com.digitalarchitects.rmc_app.domain.repo

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val JWT_TOKEN = stringPreferencesKey("jwt_token")
        val USER_ID = stringPreferencesKey("user_id")
    }

    suspend fun saveJwt(token: String) {
        dataStore.edit { preferences ->
            preferences[JWT_TOKEN] = token
        }
    }

    suspend fun saveUserId(userId: String) {
        dataStore.edit { preferences ->
            preferences[USER_ID] = userId
        }
    }

    // Read JWT token from DataStore
    suspend fun getJwtToken(): String? {
        val preferences = dataStore.data.first()
        return preferences[JWT_TOKEN]
    }

    // Read User ID from DataStore
    suspend fun getUserId(): String? {
        val preferences = dataStore.data.first()
        return preferences[USER_ID]
    }
}