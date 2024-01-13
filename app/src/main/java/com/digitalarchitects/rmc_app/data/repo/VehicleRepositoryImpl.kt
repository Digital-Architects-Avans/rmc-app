package com.digitalarchitects.rmc_app.data.repo

import android.util.Log
import com.digitalarchitects.rmc_app.data.di.IoDispatcher
import com.digitalarchitects.rmc_app.data.local.RmcRoomDatabaseRepo
import com.digitalarchitects.rmc_app.data.mapper.toLocalVehicle
import com.digitalarchitects.rmc_app.data.mapper.toLocalVehicleList
import com.digitalarchitects.rmc_app.data.mapper.toVehicle
import com.digitalarchitects.rmc_app.data.mapper.toVehicleListFromLocal
import com.digitalarchitects.rmc_app.data.remote.RmcApiService
import com.digitalarchitects.rmc_app.data.remote.dto.vehicle.CreateVehicleDTO
import com.digitalarchitects.rmc_app.data.remote.dto.vehicle.UpdateVehicleDTO
import com.digitalarchitects.rmc_app.domain.model.Vehicle
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
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
        val remoteVehicles = rmcApiService.getAllVehicles()
        rmcRoomDatabase.clearVehicleCache()
        rmcRoomDatabase.addAllVehiclesToLocalDb(remoteVehicles.toLocalVehicleList())
    }

    /** Returns true if local data source does not contain any [Vehicle] data */
    private suspend fun isCacheEmpty(): Boolean {
        var empty = true
        if (rmcRoomDatabase.getAllVehiclesFromLocalDb().isNotEmpty()) empty = false
        return empty
    }

    override suspend fun getVehicleById(vehicleId: String): Vehicle {
        return rmcRoomDatabase.getVehicleByIdFromLocalDb(vehicleId).toVehicle()
    }

    override suspend fun addVehicle(createVehicleDTO: CreateVehicleDTO) {
        try {
            val remoteVehicle = rmcApiService.addVehicle(createVehicleDTO)
            rmcRoomDatabase.addVehicleToLocalDb(remoteVehicle.toLocalVehicle())
        } catch (e: Exception) {
            Log.d("VehicleRepositoryImpl", "Error: Could not add vehicle $e")
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun updateVehicle(vehicleId: String, updatedVehicle: UpdateVehicleDTO) {
        // Update remote data source
        // Remove user from local data source
        // Add updated user to local data source
        rmcApiService.updateVehicle(vehicleId, updatedVehicle)
    }

    override suspend fun deleteVehicle(vehicleId: String): Result<Unit> {
        return try {
            val response = rmcApiService.deleteVehicle(vehicleId)

            if (response.isSuccessful) {
                Log.i("API_DELETE", "Vehicle deleted successfully: $vehicleId")
                getAllVehiclesFromRemote()
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
                    Log.e("API_DELETE", "Error: Could not delete vehicle", e)
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
    override suspend fun getVehicleModel(vehicleId: String): String? {
        return rmcRoomDatabase.getVehicleModel(vehicleId)
    }

    override suspend fun setVehicleAvailability(vehicleId: String, availability: Boolean) {
        rmcApiService.setVehicleAvailability(vehicleId, availability)
        refreshRoomCache()
    }
}