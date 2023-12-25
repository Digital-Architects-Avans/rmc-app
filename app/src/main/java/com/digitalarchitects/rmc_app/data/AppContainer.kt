package com.digitalarchitects.rmc_app.data

import com.digitalarchitects.rmc_app.network.RmcApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val userRepository: UserRepository
}

/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */
class DefaultAppContainer : AppContainer {

    /* Each instance of the emulator runs behind a virtual router or firewall service that isolates
     * it from your development machine network interfaces and settings and from the internet. An
     * emulated device can't detect your development machine or other emulator instances on the
     * network. It detects only that it is connected through ethernet to a router or firewall.
     * The virtual router for each instance manages the 10.0.2/24 network address space. All
     * addresses managed by the router are in the form of 10.0.2.xx, where xx is a number.
     */

     private val BASE_URL = "https://10.0.2.2:8443/"

    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    /**
     * Retrofit service object for creating api calls
     */
    private val retrofitService: RmcApiService by lazy {
        retrofit.create(RmcApiService::class.java)
    }

    /**
     * DI implementation for User repository
     */
    override val userRepository: UserRepository by lazy {
        NetworkUserRepository(retrofitService)
    }

}