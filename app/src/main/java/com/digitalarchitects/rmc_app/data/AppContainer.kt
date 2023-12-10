package com.digitalarchitects.rmc_app.data

import com.digitalarchitects.rmc_app.network.RmcApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/* In summary, the DefaultAppContainer class acts as a centralized container for managing
 * dependencies related to network requests and data retrieval. It follows principles of dependency
 * injection, lazy initialization, and separation of concerns. This design enhances the
 * maintainability and testability of the codebase by abstracting the creation and management of
 * dependencies.
 */

interface AppContainer {
    val userRepository: UserRepository
}

class DefaultAppContainer : AppContainer {

    private val BASE_URL = "https://10.0.2.2:8443"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: RmcApiService by lazy {
        retrofit.create(RmcApiService::class.java)
    }

    override val userRepository: UserRepository by lazy {
        NetworkUserRepository(retrofitService)
    }

}