package com.digitalarchitects.rmc_app.data.remote

import com.digitalarchitects.rmc_app.data.auth.AuthRequest
import com.digitalarchitects.rmc_app.data.auth.SignUpRequest
import com.digitalarchitects.rmc_app.data.auth.TokenResponse
import com.digitalarchitects.rmc_app.data.remote.dto.rental.CreateRentalDTO
import com.digitalarchitects.rmc_app.data.remote.dto.rental.RemoteRental
import com.digitalarchitects.rmc_app.data.remote.dto.rental.UpdateRentalDTO
import com.digitalarchitects.rmc_app.data.remote.dto.user.ProfileImageResponse
import com.digitalarchitects.rmc_app.data.remote.dto.user.UpdateUserDTO
import com.digitalarchitects.rmc_app.data.remote.dto.vehicle.CreateVehicleDTO
import com.digitalarchitects.rmc_app.data.remote.dto.vehicle.RemoteVehicle
import com.digitalarchitects.rmc_app.data.remote.dto.vehicle.UpdateVehicleDTO
import com.digitalarchitects.rmc_app.domain.model.Rental
import com.digitalarchitects.rmc_app.domain.model.RentalStatus
import com.digitalarchitects.rmc_app.domain.model.User
import com.digitalarchitects.rmc_app.domain.model.Vehicle
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
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
    suspend fun authenticate()

    @GET("refreshToken")
    suspend fun refreshToken(): TokenResponse

    @GET("users")
    suspend fun getAllUsers(): List<User>

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") id: String
    ): User?

    @PUT("users/{id}")
    suspend fun updateUser(
        @Path("id") id: String,
        @Body updatedUser: UpdateUserDTO
    ): Response<Unit>

    @PUT("users/{id}/{profileImageSrc}")
    suspend fun updateProfileImageSrc(
        @Path("id") id: String,
        @Path("profileImageSrc") profileImageSrc: String
    ): Response<Unit>

    @GET("users/{email}")
    suspend fun getUserByEmail(
        @Path("email") email: String,
    ): User?

    @DELETE("users/{id}")
    suspend fun deleteUser(
        @Path("id") id: String
    ): Response<Unit>

    // -------------------------------------------------------------------------------------------

    @GET("vehicles")
    suspend fun getAllVehicles(): List<Vehicle>

    @GET("vehicles/{id}")
    suspend fun getVehiclesById(
        @Path("id") id: String
    ): RemoteVehicle?

    @PUT("vehicles/{vehicleId}/{availability}")
    suspend fun setVehicleAvailability(
        @Path("vehicleId") id: String,
        @Path("availability") availability: Boolean
    ): Response<Unit>

    @GET("vehicles/{licensePlate}")
    suspend fun getVehicleByLicensePlate(
        @Path("licensePlate") licensePlate: String
    ): RemoteVehicle?

    @POST("vehicles")
    suspend fun addVehicle(
        @Body createVehicleDTO: CreateVehicleDTO
    ): RemoteVehicle

    @PUT("vehicles/{id}")
    suspend fun updateVehicle(
        @Path("id") id: String,
        @Body updatedVehicle: UpdateVehicleDTO
    ): Response<Unit>

    @DELETE("vehicles/{id}")
    suspend fun deleteVehicle(
        @Path("id") id: String
    ): Response<Unit>

    // -------------------------------------------------------------------------------------------

    @GET("rentals")
    suspend fun getAllRentals(
    ): List<Rental>

    @GET("rentals/{id}")
    suspend fun getRentalById(
        @Path("id") id: String
    ): RemoteRental?

    @GET("rentals/user/{userId}")
    suspend fun getRentalsByUserId(
        @Path("userId") userId: String
    ): List<RemoteRental>

    @PUT("rentals/{rentalId}")
    suspend fun updateRental(
        @Path("rentalId") rentalId: String,
        @Body updatedRental: UpdateRentalDTO
    ): List<RemoteRental>

    @GET("rentals/{rentalId}/{status}")
    suspend fun setRentalStatus(
        @Path("rentalId") rentalId: String,
        @Path("status") status: RentalStatus
    ): Response<Unit>

    @POST("rentals")
    suspend fun addRental(
        @Body createRentalDTO: CreateRentalDTO
    ): RemoteRental

    @DELETE("rentals/{id}")
    suspend fun deleteRental(
        @Path("id") id: String
    ): Response<Unit>

    // -------------------------------------------------------------------------------------------

    @POST("profileImage")
    @Multipart
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    ): ProfileImageResponse

}