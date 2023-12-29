package com.digitalarchitects.rmc_app.data.repo

import android.util.Log
import com.digitalarchitects.rmc_app.RmcApplication
import com.digitalarchitects.rmc_app.data.di.IoDispatcher
import com.digitalarchitects.rmc_app.data.mapper.toLocalRental
import com.digitalarchitects.rmc_app.data.mapper.toLocalRentalListFromRemote
import com.digitalarchitects.rmc_app.data.mapper.toRemoteRental
import com.digitalarchitects.rmc_app.data.mapper.toRental
import com.digitalarchitects.rmc_app.data.mapper.toRentalListFromLocal
import com.digitalarchitects.rmc_app.domain.repo.RentalRepository
import com.digitalarchitects.rmc_app.model.Rental
import com.digitalarchitects.rmc_app.model.Vehicle
import com.digitalarchitects.rmc_app.remote.RmcApiService
import com.digitalarchitects.rmc_app.room.RmcRoomDatabaseRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * Implementation of [RentalRepository] that retrieves [Rental] data from underlying data sources.
 */
class RentalRepositoryImpl(
    private val rmcRoomDatabase: RmcRoomDatabaseRepo,
    private val rmcApiService: RmcApiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : RentalRepository {

    // TODO(): Add token dynamically to header after a user logs in
    // Currently use this global variable to store the token declared in [RmcApplication.GlobalVariables
    private val token = RmcApplication.GlobalVariables.token

    override suspend fun getAllRentals(): List<Rental> {
        getAllRentalsFromRemote()
        return rmcRoomDatabase.getAllRentalsFromLocalDb().toRentalListFromLocal()
    }

    override suspend fun getAllRentalsFromLocalCache(): List<Rental> {
        return rmcRoomDatabase.getAllRentalsFromLocalDb().toRentalListFromLocal()
    }

    /** Retrieve all [Vehicle] from remote data source and update the local date source */
    override suspend fun getAllRentalsFromRemote() {
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
        val remoteRentals = rmcApiService.getAllRentals(
            "Bearer $token"
        )
        rmcRoomDatabase.addAllRentalsToLocalDb(remoteRentals.toLocalRentalListFromRemote())
    }

    /** Returns true if local data source does not contain any [Vehicle] data */
    private suspend fun isCacheEmpty(): Boolean {
        var empty = true
        if (rmcRoomDatabase.getAllRentalsFromLocalDb().isNotEmpty()) empty = false
        return empty
    }

    override suspend fun getRentalById(rentalId: Int): Rental? {
        return rmcRoomDatabase.getRentalByIdFromLocalDb(rentalId).toRental()
    }

    override suspend fun addRental(rental: Rental) {
        val newId = rmcRoomDatabase.addRentalToLocalDb(rental.toLocalRental())
        val id = newId.toInt()
        rmcApiService.updateRental(
            "Bearer $token", id, rental.toRemoteRental().copy(id = id)
        )
    }

    override suspend fun updateRental(rental: Rental) {
        rmcRoomDatabase.addRentalToLocalDb(rental.toLocalRental())
        rmcApiService.updateRental(
            "Bearer $token", rental.id, rental.toRemoteRental()
        )
    }

    override suspend fun deleteRental(rental: Rental): Result<Unit> {
        return try {
            val response = rmcApiService.deleteRental(
                "Bearer $token", rental.id
            )

            if (response.isSuccessful) {
                Log.i("API_DELETE", "Rental deleted successfully: ${rental.id}")
                Result.success(Unit)
            } else {
                val errorMessage = "Error deleting rental: ${response.code()}\n${response.message()}"
                Log.e("API_DELETE", errorMessage)
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            when (e) {
                is UnknownHostException, is ConnectException, is HttpException -> {
                    Log.e("HTTP", "Error: Could not delete rental", e)
                    Result.failure(e)
                }
                else -> {
                    Log.e("HTTP", "Unexpected error during rental deletion", e)
                    Result.failure(e)
                }
            }
        }
    }

    override suspend fun getRentalDate(rentalId: Int): String? {
        return rmcRoomDatabase.getRentalDate(rentalId)
    }
}