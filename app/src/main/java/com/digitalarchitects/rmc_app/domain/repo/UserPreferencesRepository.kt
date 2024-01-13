package com.digitalarchitects.rmc_app.domain.repo

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
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
        val LATITUDE = floatPreferencesKey("latitude")
        val LONGITUDE = floatPreferencesKey("longitude")
        val PRICE = stringPreferencesKey("price")
        val DISTANCE = stringPreferencesKey("distance")
        val ENGINETYPEICE = booleanPreferencesKey("enginetype_ice")
        val ENGINETYPEBEV = booleanPreferencesKey("enginetype_bev")
        val ENGINETYPEFCEV = booleanPreferencesKey("enginetype_fcev")
        val SHOWSEARCHLOCATION = booleanPreferencesKey("show_search_location")
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
        latitude: Float,
        longitude: Float,
        price: Double,
        distance: Double,
        engineTypeICE: Boolean,
        engineTypeBEV: Boolean,
        engineTypeFCEV: Boolean
    ) {
        dataStore.edit { preferences ->
            preferences[DATE] = date
            preferences[LATITUDE] = latitude
            preferences[LONGITUDE] = longitude
            preferences[PRICE] = price.toString()
            preferences[DISTANCE] = distance.toString()
            preferences[ENGINETYPEICE] = engineTypeICE
            preferences[ENGINETYPEBEV] = engineTypeBEV
            preferences[ENGINETYPEFCEV] = engineTypeFCEV
        }
        Log.d("UserPreferencesRepository", "Saving filter preference, date = $DATE")

    }

    // get filter preference from user from datastore
    suspend fun getFilterPreference(): FilterStates {
        Log.d("UserPreferencesRepository", "Fetching filter preference, date = ${dataStore.data.first()[DATE]}")
        val preferences = dataStore.data.first()
        return FilterStates(
            date = preferences[DATE] ?: "",
            latitude = preferences[LATITUDE] ?: 0.0F,
            longitude = preferences[LONGITUDE] ?: 0.0F,
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
        val latitude: Float,
        val longitude: Float,
        val price: Double,
        val distance: Double,
        val engineTypeICE: Boolean,
        val engineTypeBEV: Boolean,
        val engineTypeFCEV: Boolean
    )

    // Save new search query activity in datastore to focus camera on map
    suspend fun saveShowSearchLocation(show: Boolean) {
        dataStore.edit { preferences ->
            preferences[SHOWSEARCHLOCATION] = show
        }
    }

    // Get current status from datastore
    suspend fun getShowSearchLocation(): Boolean {
        val preferences = dataStore.data.first()
        return preferences[SHOWSEARCHLOCATION] ?: false
    }
}