package com.digitalarchitects.rmc_app.fake

import com.digitalarchitects.rmc_app.remote.RmcApiService
import com.digitalarchitects.rmc_app.remote.dto.rental.RemoteRental
import com.digitalarchitects.rmc_app.remote.dto.user.RemoteUser
import com.digitalarchitects.rmc_app.remote.dto.user.SignupDTO
import com.digitalarchitects.rmc_app.remote.dto.vehicle.RemoteVehicle
import retrofit2.Response
import retrofit2.http.Header

class FakeRmcApiService : RmcApiService {
    override suspend fun signupUser(user: SignupDTO): Response<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUsers(@Header(value = "Authorization") token: String): List<RemoteUser> {
        return FakeRemoteDataSource.userList
    }

    override suspend fun updateUser(token: String, id: Int?, user: RemoteUser): Response<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(token: String, id: Int?): Response<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllVehicles(token: String): List<RemoteVehicle> {
        TODO("Not yet implemented")
    }

    override suspend fun updateVehicle(
        token: String,
        id: Int?,
        user: RemoteVehicle
    ): Response<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteVehicle(token: String, id: Int?): Response<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllRentals(token: String): List<RemoteRental> {
        TODO("Not yet implemented")
    }

    override suspend fun updateRental(token: String, id: Int?, user: RemoteRental): Response<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRental(token: String, id: Int?): Response<Unit> {
        TODO("Not yet implemented")
    }
}