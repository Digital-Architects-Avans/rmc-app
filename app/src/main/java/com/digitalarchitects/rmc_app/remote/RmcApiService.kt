package com.digitalarchitects.rmc_app.remote

import com.digitalarchitects.rmc_app.model.User
import com.digitalarchitects.rmc_app.remote.dto.rental.RemoteRental
import com.digitalarchitects.rmc_app.remote.dto.user.RemoteUser
import com.digitalarchitects.rmc_app.remote.dto.user.SignupDTO
import com.digitalarchitects.rmc_app.remote.dto.vehicle.RemoteVehicle
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface RmcApiService {

    @POST("user/signup")
    suspend fun signupUser(@Body user: SignupDTO): Response<Unit>

    /**
     * Returns a [List] of [User] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "users" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("user/users")
    suspend fun getAllUsers(@Header("Authorization") token: String): List<RemoteUser>

    @PUT("user/{id}")
    suspend fun updateUser(
        @Header("Authorization") token: String,
        @Path("id") id: Int?,
        @Body user: RemoteUser
    ): Response<Unit>

    @DELETE("user/{id}")
    suspend fun deleteUser(
        @Header("Authorization") token: String,
        @Path("id") id: Int?
    ): Response<Unit>

    @GET("vehicle/all")
    suspend fun getAllVehicles(@Header("Authorization") token: String): List<RemoteVehicle>

    @PUT("vehicle/{id}")
    suspend fun updateVehicle(
        @Header("Authorization") token: String,
        @Path("id") id: Int?,
        @Body user: RemoteVehicle
    ): Response<Unit>

    @DELETE("vehicle/{id}")
    suspend fun deleteVehicle(
        @Header("Authorization") token: String,
        @Path("id") id: Int?
    ): Response<Unit>

    @GET("rental/allRentals")
    suspend fun getAllRentals(@Header("Authorization") token: String): List<RemoteRental>

    @PUT("rental/{id}")
    suspend fun updateRental(
        @Header("Authorization") token: String,
        @Path("id") id: Int?,
        @Body user: RemoteRental
    ): Response<Unit>

    @DELETE("rental/{id}")
    suspend fun deleteRental(
        @Header("Authorization") token: String,
        @Path("id") id: Int?
    ): Response<Unit>
}