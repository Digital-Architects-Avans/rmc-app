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
        val DATE = stringPreferencesKey("")
        val LOCATION = stringPreferencesKey("")
        val PRICE = stringPreferencesKey("")
        val DISTANCE = stringPreferencesKey("")
        val ENGINETYPEICE = stringPreferencesKey("")
        val ENGINETYPEBEV = stringPreferencesKey("")
        val ENGINETYPEFCEV = stringPreferencesKey("")
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

    // save filter preference of user in datastore
    suspend fun saveFilterPreference(
        date: String,
        location: String,
        price: Double,
        distance: Double,
        engineTypeICE: Boolean,
        engineTypeBEV: Boolean,
        engineTypeFCEV: Boolean
    ) {
        dataStore.edit { preferences ->
            preferences[DATE] = date
            preferences[LOCATION] = location
            preferences[PRICE] = price.toString()
            preferences[DISTANCE] = distance.toString()
            preferences[ENGINETYPEICE] = engineTypeICE.toString()
            preferences[ENGINETYPEBEV] = engineTypeBEV.toString()
            preferences[ENGINETYPEFCEV] = engineTypeFCEV.toString()
        }
    }

    // get filter preference from user from datastore
    suspend fun getFilterPreference(): FilterStates {
        val preferences = dataStore.data.first()
        return FilterStates(
            date = preferences[DATE] ?: "Date",
            location = preferences[LOCATION] ?: "Location",
            price = preferences[PRICE]?.toDouble() ?: 55.0,
            distance = preferences[DISTANCE]?.toDouble() ?: 4.1,
            engineTypeICE = preferences[ENGINETYPEICE]?.toBoolean() ?: true,
            engineTypeBEV = preferences[ENGINETYPEBEV]?.toBoolean() ?: true,
            engineTypeFCEV = preferences[ENGINETYPEFCEV]?.toBoolean() ?: true
        )
    }

    // data class to hold and handle filter preference data
    data class FilterStates(
        val date: String,
        val location: String,
        val price: Double,
        val distance: Double,
        val engineTypeICE: Boolean,
        val engineTypeBEV: Boolean,
        val engineTypeFCEV: Boolean
    )

}