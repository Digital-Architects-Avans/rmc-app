package com.digitalarchitects.rmc_app.network

import com.digitalarchitects.rmc_app.data.FakeDataSource
import com.digitalarchitects.rmc_app.model.User
import com.digitalarchitects.rmc_app.model.Vehicle

class FakeRmcApiService : RmcApiService {

    override suspend fun getUsers(): List<User> {
        return FakeDataSource.userList
    }

    override suspend fun getVehicles(): List<Vehicle> {
        return FakeDataSource.vehicleList
    }
}