package com.digitalarchitects.rmc_app.network

import com.digitalarchitects.rmc_app.model.User
import com.digitalarchitects.rmc_app.model.Vehicle
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://10.0.2.2:8443"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object RmcApi {
    val retrofitService : RmcApiService by lazy {
        retrofit.create(RmcApiService::class.java)
    }
}

interface RmcApiService {
    @GET("user/users")
    suspend fun getUsers(): List<User>

    @GET("vehicle/vehicles")
    suspend fun getVehicles(): List<Vehicle>

    // Add more endpoint calls ...
}