package com.digitalarchitects.rmc_app.data.auth

import android.util.Log
import com.digitalarchitects.rmc_app.data.remote.RmcApiService
import com.digitalarchitects.rmc_app.domain.repo.UserPreferencesRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Provider

class AuthInterceptor @Inject constructor(
    private val rmcApiService: Provider<RmcApiService>,
    private val userPreferencesRepository: UserPreferencesRepository
) : Interceptor {


    private suspend fun refreshToken(chain: Interceptor.Chain): Response {
        try {
            Log.d("refreshToken", "try")
            // Call the refresh token API to obtain a new access token
            val newToken = rmcApiService.get().refreshToken().token

            // Save the new JWT token to shared preferences
            userPreferencesRepository.saveJwt(newToken)
            Log.d("AuthInterceptor", "Refresh token successful")
        } catch (e: Exception) {
            Log.e("AuthInterceptor", "Error trying to refresh the token: $e")
        }

        val token = userPreferencesRepository.getJwtToken()
        val newRequest = chain.request()
            .newBuilder()
            .header("Authorization", "Bearer $token")
            .build()

        return chain.proceed(newRequest)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        // Using runBlocking to call the suspend function within a non-suspend context
        return runBlocking {
            val token = userPreferencesRepository.getJwtToken()
            if (!token.isNullOrEmpty()) {
                val newRequest = chain.request()
                    .newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
                val response = chain.proceed(newRequest)
                if (response.code == 401) {
                    // If the response indicates unauthorized, try to refresh the token
                    refreshToken(chain)
                } else {
                    response
                }
            } else {
                // No token in shared preferences, proceed with the original request
                chain.proceed(chain.request())
            }
        }
    }
}