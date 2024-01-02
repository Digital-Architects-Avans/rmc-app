package com.digitalarchitects.rmc_app.remote

import com.digitalarchitects.rmc_app.data.auth.AuthRequest
import com.digitalarchitects.rmc_app.data.auth.SignUpRequest
import com.digitalarchitects.rmc_app.data.auth.TokenResponse
import com.digitalarchitects.rmc_app.model.Rental
import com.digitalarchitects.rmc_app.model.User
import com.digitalarchitects.rmc_app.model.Vehicle
import com.digitalarchitects.rmc_app.remote.dto.rental.CreateRentalDTO
import com.digitalarchitects.rmc_app.remote.dto.rental.RemoteRental
import com.digitalarchitects.rmc_app.remote.dto.rental.UpdateRentalDTO
import com.digitalarchitects.rmc_app.remote.dto.user.UpdateUserDTO
import com.digitalarchitects.rmc_app.remote.dto.vehicle.CreateVehicleDTO
import com.digitalarchitects.rmc_app.remote.dto.vehicle.RemoteVehicle
import com.digitalarchitects.rmc_app.remote.dto.vehicle.UpdateVehicleDTO
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

    @POST("signup")
    suspend fun signup(
        @Body request: SignUpRequest
    )

    @POST("signin")
    suspend fun signin(
        @Body request: AuthRequest
    ): TokenResponse

    @GET("authenticate")
    suspend fun authenticate(
        @Header("Authorization") token: String
    )

    @GET("users")
    suspend fun getAllUsers(
        @Header("Authorization") token: String
    ): List<User>

    @GET("users/{id}")
    suspend fun getUserById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): User?

    @PUT("users/{id}")
    suspend fun updateUser(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body updatedUser: UpdateUserDTO
    ): Response<Unit>

    @GET("users/{email}")
    suspend fun getUserByEmail(
        @Header("Authorization") token: String,
        @Path("email") email: String,
    ): User?

    @DELETE("users/{id}")
    suspend fun deleteUser(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<Unit>

    // -------------------------------------------------------------------------------------------

    @GET("vehicles")
    suspend fun getAllVehicles(
        @Header("Authorization") token: String
    ): List<Vehicle>

    @GET("vehicles/{id}")
    suspend fun getVehiclesById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): RemoteVehicle?

    @GET("vehicles/{licensePlate}")
    suspend fun getVehiclesByLicensePlate(
        @Header("Authorization") token: String,
        @Path("licensePlate") licensePlate: String
    ): RemoteVehicle?

    @POST("vehicles")
    suspend fun addVehicle(
        @Header("Authorization") token: String,
        @Body createVehicleDTO: CreateVehicleDTO
    ): Response<Unit>

    @PUT("vehicles/{id}")
    suspend fun updateVehicle(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body updatedVehicle: UpdateVehicleDTO
    ): Response<Unit>

    @DELETE("vehicles/{id}")
    suspend fun deleteVehicle(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<Unit>

    // -------------------------------------------------------------------------------------------

    @GET("rentals")
    suspend fun getAllRentals(
        @Header("Authorization") token: String
    ): List<Rental>

    @GET("rentals/{id}")
    suspend fun getRentalById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): RemoteRental?

    @GET("rentals/user/{userId}")
    suspend fun getRentalsByUserId(
        @Header("Authorization") token: String,
        @Path("userId") userId: String
    ): List<RemoteRental>

    @PUT("rentals/{rentalId}")
    suspend fun updateRental(
        @Header("Authorization") token: String,
        @Path("rentalId") rentalId: String,
        @Body updatedRental: UpdateRentalDTO
    ): List<RemoteRental>

    @POST("rentals")
    suspend fun addRental(
        @Header("Authorization") token: String,
        @Body createRentalDTO: CreateRentalDTO
    ): Response<Unit>

    @DELETE("rentals/{id}")
    suspend fun deleteRental(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<Unit>
}