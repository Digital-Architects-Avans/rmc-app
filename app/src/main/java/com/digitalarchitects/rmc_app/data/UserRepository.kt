package com.digitalarchitects.rmc_app.data

import com.digitalarchitects.rmc_app.model.User
import com.digitalarchitects.rmc_app.model.Vehicle
import com.digitalarchitects.rmc_app.network.RmcApiService

interface UserRepository {
    suspend fun getUsers(): List<User>
    suspend fun getVehicles(): List<Vehicle>
}

class NetworkUserRepository(
    private val rmcApiService: RmcApiService
) : UserRepository {
    /** Fetches list of MarsPhoto from marsApi*/
    override suspend fun getUsers(): List<User> = rmcApiService.getUsers()

    override suspend fun getVehicles(): List<Vehicle> = rmcApiService.getVehicles()
}