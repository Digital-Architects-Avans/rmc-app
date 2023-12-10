package com.digitalarchitects.rmc_app.fake

import com.digitalarchitects.rmc_app.data.UserRepository
import com.digitalarchitects.rmc_app.model.User
import com.digitalarchitects.rmc_app.model.Vehicle

// Class inherits from userRepository interface overrides the getUsers() fun to return fake data.
class FakeNetworkUserRepository : UserRepository {
    override suspend fun getUsers(): List<User> {
        return FakeDataSource.userList
    }

    override suspend fun getVehicles(): List<Vehicle> {
        return FakeDataSource.vehicleList
    }
}