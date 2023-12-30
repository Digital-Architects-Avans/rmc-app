package com.digitalarchitects.rmc_app.data.repo

import android.util.Log
import com.digitalarchitects.rmc_app.RmcApplication
import com.digitalarchitects.rmc_app.data.di.IoDispatcher
import com.digitalarchitects.rmc_app.data.mapper.toLocalVehicle
import com.digitalarchitects.rmc_app.data.mapper.toLocalVehicleListFromRemote
import com.digitalarchitects.rmc_app.data.mapper.toRemoteVehicle
import com.digitalarchitects.rmc_app.data.mapper.toVehicle
import com.digitalarchitects.rmc_app.data.mapper.toVehicleListFromLocal
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
import com.digitalarchitects.rmc_app.model.Vehicle
import com.digitalarchitects.rmc_app.remote.RmcApiService
import com.digitalarchitects.rmc_app.remote.dto.vehicle.CreateVehicleDTO
import com.digitalarchitects.rmc_app.room.RmcRoomDatabaseRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * Implementation of [VehicleRepository] that retrieves [Vehicle] data from underlying data sources.
 */
class VehicleRepositoryImpl(
    private val rmcRoomDatabase: RmcRoomDatabaseRepo,
    private val rmcApiService: RmcApiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : VehicleRepository {

    // TODO(): Add token dynamically to header after a user logs in
    // Currently use this global variable to store the token declared in [RmcApplication.GlobalVariables
    private val token = RmcApplication.GlobalVariables.token

    /** Fetches data from remote, updates local data source, returns list of [Vehicle] from local data source */
    override suspend fun getAllVehicles(): List<Vehicle> {
        getAllVehiclesFromRemote()
        return rmcRoomDatabase.getAllVehiclesFromLocalDb().toVehicleListFromLocal()
    }

    override suspend fun getAllVehiclesFromLocalCache(): List<Vehicle> {
        return rmcRoomDatabase.getAllVehiclesFromLocalDb().toVehicleListFromLocal()
    }

    /** Retrieve all [Vehicle] from remote data source and update the local date source */
    override suspend fun getAllVehiclesFromRemote() {
        return withContext(dispatcher) {
            try {
                refreshRoomCache()
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException, is ConnectException, is HttpException -> {
                        Log.e("HTTP", "Error: No data from Remote")
                        Log.e("HTTP", "$e")
                        if (isCacheEmpty()) {
                            Log.e("Cache", "Error: No data from local Room cache")
                            throw Exception("Error: Device offline and\nno data from local Room cache")
                        }
                    }

                    else -> throw e
                }
            }
        }
    }

    // Update local Room cache with data from remote Retrofit API
    private suspend fun refreshRoomCache() {
        val remoteVehicles = rmcApiService.getAllVehicles(
            "Bearer $token"
        )
        rmcRoomDatabase.addAllVehiclesToLocalDb(remoteVehicles.toLocalVehicleListFromRemote())
    }

    /** Returns true if local data source does not contain any [Vehicle] data */
    private suspend fun isCacheEmpty(): Boolean {
        var empty = true
        if (rmcRoomDatabase.getAllVehiclesFromLocalDb().isNotEmpty()) empty = false
        return empty
    }

    override suspend fun getVehicleById(vehicleId: Int): Vehicle? {
        return rmcRoomDatabase.getVehicleByIdFromLocalDb(vehicleId).toVehicle()
    }

    override suspend fun addVehicle(createVehicleDTO: CreateVehicleDTO, vehicle: Vehicle) {
        rmcRoomDatabase.addVehicleToLocalDb(vehicle.toLocalVehicle())
        rmcApiService.addVehicle(
            "Bearer $token", createVehicleDTO
        )
    }

    override suspend fun updateVehicle(vehicle: Vehicle) {
        rmcRoomDatabase.addVehicleToLocalDb(vehicle.toLocalVehicle())
        rmcApiService.updateVehicle(
            "Bearer $token", vehicle.id, vehicle.toRemoteVehicle()
        )
    }

    override suspend fun deleteVehicle(vehicle: Vehicle): Result<Unit> {
        return try {
            val response = rmcApiService.deleteVehicle(
                "Bearer $token", vehicle.id
            )

            if (response.isSuccessful) {
                Log.i("API_DELETE", "Vehicle deleted successfully: ${vehicle.id}")
                Result.success(Unit)
            } else {
                val errorMessage =
                    "Error deleting vehicle: ${response.code()}\n${response.message()}"
                Log.e("API_DELETE", errorMessage)
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            when (e) {
                is UnknownHostException, is ConnectException, is HttpException -> {
                    Log.e("HTTP", "Error: Could not delete vehicle", e)
                    Result.failure(e)
                }

                else -> {
                    Log.e("HTTP", "Unexpected error during vehicle deletion", e)
                    Result.failure(e)
                }
            }
        }
    }

    /** Returns the model name of [Vehicle] */
    override suspend fun getVehicleModel(vehicleId: Int): String? {
        return rmcRoomDatabase.getVehicleModel(vehicleId)
    }
}