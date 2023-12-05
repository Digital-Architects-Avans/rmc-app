package com.digitalarchitects.rmc_app

import com.digitalarchitects.rmc_app.network.FakeDataSource
import com.digitalarchitects.rmc_app.model.User
import com.digitalarchitects.rmc_app.model.Vehicle
import com.digitalarchitects.rmc_app.network.RmcApiService

class FakeMarsApiService : RmcApiService {

    override suspend fun getUsers(): List<User> {
        return FakeDataSource.userList
    }

    override suspend fun getVehicles(): List<Vehicle> {
        return FakeDataSource.vehicleList
    }
}