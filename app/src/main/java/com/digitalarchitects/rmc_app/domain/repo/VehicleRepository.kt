package com.digitalarchitects.rmc_app.domain.repo

import com.digitalarchitects.rmc_app.model.Vehicle
import com.digitalarchitects.rmc_app.remote.dto.vehicle.CreateVehicleDTO

/**
 * Repository retrieves Vehicle data from underlying data source (remote and local).
 */
interface VehicleRepository {

    /** Retrieves list of [Vehicle] from underlying data source */
    suspend fun getAllVehicles(): List<Vehicle>

    /** Retrieves list of [Vehicle] from local data source */
    suspend fun getAllVehiclesFromLocalCache(): List<Vehicle>

    /** Retrieves all [Vehicle] from remote data source and updates the local data source */
    suspend fun getAllVehiclesFromRemote()

    /** Retrieves [Vehicle] by id from underlying data source */
    suspend fun getVehicleById(vehicleId: Int): Vehicle?

    /** Adds [Vehicle] to the underlying data source */
    suspend fun addVehicle(createVehicleDTO: CreateVehicleDTO, vehicle: Vehicle)

    /** Updates the [Vehicle] to the underlying data source */
    suspend fun updateVehicle(vehicle: Vehicle)

    /** Deletes the [Vehicle] from the underlying data source */
    suspend fun deleteVehicle(vehicle: Vehicle): Result<Unit>

    /** Retrieves the model name from the [Vehicle]  */
    suspend fun getVehicleModel(vehicleId: Int): String?

}