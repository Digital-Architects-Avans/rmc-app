package com.digitalarchitects.rmc_app.data.auth

import android.content.SharedPreferences
import android.util.Log
import com.digitalarchitects.rmc_app.remote.RmcApiService
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Provider

class AuthInterceptor @Inject constructor(
    private val rmcApiService: Provider<RmcApiService>,
    private val prefs: SharedPreferences
) : Interceptor {


    private suspend fun refreshToken(chain: Interceptor.Chain): Response {
        try {
            // Call the refresh token API to obtain a new access token
            val newToken = rmcApiService.get().refreshToken().token

            // Save the new JWT token to shared preferences
            prefs.edit().putString("jwtToken", newToken).apply()
            Log.d("AuthInterceptor", "Refresh token successful")
        } catch (e: Exception) {
            Log.e("AuthInterceptor", "Error trying to refresh the token: $e")
        }

        val token = prefs.getString("jwtToken", null)
        val newRequest = chain.request()
            .newBuilder()
            .header("Authorization", "Bearer $token")
            .build()

        return chain.proceed(newRequest)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = prefs.getString("jwtToken", null)
        return if (!token.isNullOrEmpty()) {
            val newRequest = chain.request()
                .newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
            val response = chain.proceed(newRequest)
            if (response.code == 401) {
                runBlocking { refreshToken(chain) }
            } else {
                response
            }
        } else {
            runBlocking { refreshToken(chain) }
        }
    }
}