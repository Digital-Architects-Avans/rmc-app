package com.digitalarchitects.rmc_app.fake

import FakeLocalDataSource
import com.digitalarchitects.rmc_app.data.mapper.toVehicle
import com.digitalarchitects.rmc_app.data.mapper.toVehicleListFromLocal
import com.digitalarchitects.rmc_app.data.remote.dto.vehicle.CreateVehicleDTO
import com.digitalarchitects.rmc_app.data.remote.dto.vehicle.UpdateVehicleDTO
import com.digitalarchitects.rmc_app.domain.model.Vehicle
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository

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

    override suspend fun getVehicleById(vehicleId: String): Vehicle? {
        return FakeLocalDataSource.vehicleList.firstOrNull { it.vehicleId == vehicleId }?.toVehicle()
    }

    override suspend fun addVehicle(createVehicleDTO: CreateVehicleDTO, vehicle: Vehicle) {
        TODO("Not yet implemented")
    }

    override suspend fun updateVehicle(vehicleId: String, updatedVehicle: UpdateVehicleDTO) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteVehicle(vehicle: Vehicle): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getVehicleModel(vehicleId: String): String? {
        TODO("Not yet implemented")
    }

}