package com.digitalarchitects.rmc_app.fake

import com.digitalarchitects.rmc_app.model.User
import com.digitalarchitects.rmc_app.model.Vehicle
import com.digitalarchitects.rmc_app.network.RmcApiService
import retrofit2.http.Header

class FakeRmcApiService : RmcApiService {

    override suspend fun getUsers(@Header(value = "Authorization") token: String): List<User> {
        return FakeDataSource.userList
    }

    override suspend fun getVehicles(): List<Vehicle> {
        return FakeDataSource.vehicleList
    }
}