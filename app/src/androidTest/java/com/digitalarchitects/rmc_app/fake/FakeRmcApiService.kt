package com.digitalarchitects.rmc_app.fake

import com.digitalarchitects.rmc_app.data.auth.AuthRequest
import com.digitalarchitects.rmc_app.data.auth.SignUpRequest
import com.digitalarchitects.rmc_app.data.auth.TokenResponse
import com.digitalarchitects.rmc_app.data.remote.RmcApiService
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

class FakeRmcApiService : RmcApiService {
    override suspend fun signup(request: SignUpRequest) {
        TODO("Not yet implemented")
    }

    override suspend fun signin(request: AuthRequest): TokenResponse {
        TODO("Not yet implemented")
    }

    override suspend fun authenticate() {
        TODO("Not yet implemented")
    }

    override suspend fun refreshToken(): TokenResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUsers(): List<User> {
        return FakeRemoteDataSource.userList
    }

    override suspend fun getUserById(id: String): User? {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(id: String, updatedUser: UpdateUserDTO): Response<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun updateProfileImageSrc(
        id: String,
        profileImageSrc: String
    ): Response<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserByEmail(email: String): User? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(id: String): Response<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllVehicles(): List<Vehicle> {
        TODO("Not yet implemented")
    }

    override suspend fun getVehiclesById(id: String): RemoteVehicle? {
        TODO("Not yet implemented")
    }

    override suspend fun getVehicleByLicensePlate(licensePlate: String): RemoteVehicle? {
        TODO("Not yet implemented")
    }

    override suspend fun addVehicle(createVehicleDTO: CreateVehicleDTO): RemoteVehicle {
        TODO("Not yet implemented")
    }

    override suspend fun updateVehicle(
        id: String,
        updatedVehicle: UpdateVehicleDTO
    ): Response<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteVehicle(id: String): Response<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllRentals(): List<Rental> {
        TODO("Not yet implemented")
    }

    override suspend fun getRentalById(id: String): RemoteRental? {
        TODO("Not yet implemented")
    }

    override suspend fun getRentalsByUserId(userId: String): List<RemoteRental> {
        TODO("Not yet implemented")
    }

    override suspend fun updateRental(
        rentalId: String,
        updatedRental: UpdateRentalDTO
    ): List<RemoteRental> {
        TODO("Not yet implemented")
    }

    override suspend fun addRental(createRentalDTO: CreateRentalDTO): RemoteRental {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRental(id: String): Response<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun uploadImage(image: MultipartBody.Part): ProfileImageResponse {
        TODO("Not yet implemented")
    }

    override suspend fun setRentalStatus(rentalId: String, status: RentalStatus): Response<Unit>{
        TODO("Not yet implemented")
    }

    override suspend fun setVehicleAvailability(id: String, availability: Boolean): Response<Unit>{
        TODO("Not yet implemented")
    }

}