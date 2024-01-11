package com.digitalarchitects.rmc_app.domain.repo

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
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
        val DATE = stringPreferencesKey("date")
        val LOCATION = stringPreferencesKey("location")
        val PRICE = stringPreferencesKey("price")
        val DISTANCE = stringPreferencesKey("distance")
        val ENGINETYPEICE = booleanPreferencesKey("enginetype_ice")
        val ENGINETYPEBEV = booleanPreferencesKey("enginetype_bev")
        val ENGINETYPEFCEV = booleanPreferencesKey("enginetype_fcev")
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
            preferences[ENGINETYPEICE] = engineTypeICE
            preferences[ENGINETYPEBEV] = engineTypeBEV
            preferences[ENGINETYPEFCEV] = engineTypeFCEV
        }
    }

    // get filter preference from user from datastore
    suspend fun getFilterPreference(): FilterStates {
        val preferences = dataStore.data.first()
        return FilterStates(
            date = preferences[DATE] ?: "",
            location = preferences[LOCATION] ?: "",
            price = preferences[PRICE]?.toDouble() ?: 0.0,
            distance = preferences[DISTANCE]?.toDouble() ?: 0.0,
            engineTypeICE = preferences[ENGINETYPEICE] ?: true,
            engineTypeBEV = preferences[ENGINETYPEBEV] ?: true,
            engineTypeFCEV = preferences[ENGINETYPEFCEV] ?: true
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