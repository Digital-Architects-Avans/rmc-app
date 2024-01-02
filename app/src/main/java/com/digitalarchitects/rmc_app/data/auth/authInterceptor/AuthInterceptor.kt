package com.digitalarchitects.rmc_app.data.auth.authInterceptor

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val prefs: SharedPreferences,
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = prefs.getString("jwtToken", null)
        val request = chain.request()
        if (!token.isNullOrEmpty()) {
            val newRequest = request
                .newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
            return chain.proceed(newRequest)
        }
        return chain.proceed(request)
    }
}