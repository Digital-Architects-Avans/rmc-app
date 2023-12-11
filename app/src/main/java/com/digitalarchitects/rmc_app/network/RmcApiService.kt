package com.digitalarchitects.rmc_app.network

import com.digitalarchitects.rmc_app.model.User
import com.digitalarchitects.rmc_app.model.Vehicle
import retrofit2.http.GET
import retrofit2.http.Header


interface RmcApiService {
    /**
     * Returns a [List] of [User] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "photos" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("user/users")
    //suspend fun getUsers(): List<User>
    suspend fun getUsers(@Header("Authorization") token: String): List<User>

    /**
     * Returns a [List] of [Vehicle] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "photos" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("vehicle/vehicles")
    suspend fun getVehicles(): List<Vehicle>

    // Add more endpoint calls ...
}