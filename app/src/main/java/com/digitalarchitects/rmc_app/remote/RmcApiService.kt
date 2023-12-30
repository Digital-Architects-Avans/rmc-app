package com.digitalarchitects.rmc_app.remote

import com.digitalarchitects.rmc_app.remote.dto.rental.CreateRentalDTO
import com.digitalarchitects.rmc_app.remote.dto.rental.RemoteRental
import com.digitalarchitects.rmc_app.remote.dto.user.RemoteUser
import com.digitalarchitects.rmc_app.remote.dto.user.SigninDTO
import com.digitalarchitects.rmc_app.remote.dto.user.SignupDTO
import com.digitalarchitects.rmc_app.remote.dto.vehicle.CreateVehicleDTO
import com.digitalarchitects.rmc_app.remote.dto.vehicle.RemoteVehicle
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


/**
 * CRUD operations on API database - perform other operations on local database
 * Local database acts as single source of truth and is updated from API database
 */
interface RmcApiService {

    @POST("user/signup")
    suspend fun addUser(@Body signupDTO: SignupDTO): Response<Unit>

    @POST("user/signin")
    suspend fun authenticateUser(@Body signinDTO: SigninDTO): Response<Unit>

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

    // -------------------------------------------------------------------------------------------

    @POST("vehicle/createVehicle")
    suspend fun addVehicle(
        @Header("Authorization") token: String,
        @Body createVehicleDTO: CreateVehicleDTO
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

    // -------------------------------------------------------------------------------------------

    @GET("createRental/{vehicleId}")
    suspend fun addRental(
        @Header("Authorization") token: String,
        @Body createRentalDTO: CreateRentalDTO
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