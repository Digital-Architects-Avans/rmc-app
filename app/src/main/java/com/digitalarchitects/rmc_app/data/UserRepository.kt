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
    /** Fetches list of User from rmcApi*/
    // TODO(): Add token dynamically to header after a user logs in
    override suspend fun getUsers(): List<User> = rmcApiService.getUsers("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJodHRwOi8vMC4wLjAuMDo4MDgwL2hlbGxvIiwiaXNzIjoiaHR0cDovLzAuMC4wLjA6ODA4MC8iLCJlbWFpbCI6InN0YWZmQGVtYWlsLmNvbSIsInVzZXJUeXBlIjoiU1RBRkYiLCJ1c2VySWQiOjIzLCJleHAiOjE3MDIzNDI1NjR9.OcdtZK2pRiNIn0QYVhctGgRCvR3f_lpsJ6lyQtOO1E0")

    override suspend fun getVehicles(): List<Vehicle> = rmcApiService.getVehicles()
}