package com.digitalarchitects.rmc_app.fake

import FakeLocalDataSource
import com.digitalarchitects.rmc_app.data.mapper.toVehicle
import com.digitalarchitects.rmc_app.data.mapper.toVehicleListFromLocal
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
import com.digitalarchitects.rmc_app.model.Vehicle

// Class inherits from userRepository interface overrides the getUsers() fun to return fake data.
class FakeVehicleRepository : VehicleRepository {
    override suspend fun getAllVehicles(): List<Vehicle> {
        return FakeLocalDataSource.vehicleList.toVehicleListFromLocal()
    }

    override suspend fun getAllVehiclesFromLocalCache(): List<Vehicle> {
        return FakeLocalDataSource.vehicleList.toVehicleListFromLocal()
    }

    override suspend fun getAllVehiclesFromRemote() {
        return
    }

    override suspend fun getVehicleById(vehicleId: Int): Vehicle? {
        return FakeLocalDataSource.vehicleList.firstOrNull { it.id == vehicleId }?.toVehicle()
    }

    override suspend fun addVehicle(vehicle: Vehicle) {
        TODO("Not yet implemented")
    }

    override suspend fun updateVehicle(vehicle: Vehicle) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteVehicle(vehicle: Vehicle): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getVehicleModel(vehicleId: Int): String? {
        TODO("Not yet implemented")
    }

}