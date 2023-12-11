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

    /* Each instance of the emulator runs behind a virtual router or firewall service that isolates
     * it from your development machine network interfaces and settings and from the internet. An
     * emulated device can't detect your development machine or other emulator instances on the
     * network. It detects only that it is connected through ethernet to a router or firewall.
     * The virtual router for each instance manages the 10.0.2/24 network address space. All
     * addresses managed by the router are in the form of 10.0.2.xx, where xx is a number.
     */

     private val BASE_URL = "https://10.0.2.2:8443/"

    //  Fetch a JSON response from the web service and return it as a String
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