package com.digitalarchitects.rmc_app.data

import com.digitalarchitects.rmc_app.model.User
import com.digitalarchitects.rmc_app.model.Vehicle
import com.digitalarchitects.rmc_app.network.RmcApiService

/**
 * Repository retrieves User data from underlying data source (rmc-api).
 */
interface UserRepository {
    /** Retrieves list of User from underlying data source */
    suspend fun getUsers(): List<User>
    /** Retrieves list of Vehicle from underlying data source */
    suspend fun getVehicles(): List<Vehicle>
}

/**
 * Network Implementation of repository that retrieves User data from underlying data source.
 */
class NetworkUserRepository(
    private val rmcApiService: RmcApiService
) : UserRepository {

    // TODO(): Add token dynamically to header after a user logs in
    // TODO(): Separate User/Vehicle/Rental repositories?
    /** Retrieves list of User from underlying data source */
    override suspend fun getUsers(): List<User> = rmcApiService.getUsers("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJodHRwOi8vMC4wLjAuMDo4MDgwL2hlbGxvIiwiaXNzIjoiaHR0cDovLzAuMC4wLjA6ODA4MC8iLCJlbWFpbCI6InN0YWZmQGVtYWlsLmNvbSIsInVzZXJUeXBlIjoiU1RBRkYiLCJ1c2VySWQiOjIzLCJleHAiOjE3MDIzNDI1NjR9.OcdtZK2pRiNIn0QYVhctGgRCvR3f_lpsJ6lyQtOO1E0")

    /** Retrieves list of Vehicle from underlying data source */
    override suspend fun getVehicles(): List<Vehicle> = rmcApiService.getVehicles()
}