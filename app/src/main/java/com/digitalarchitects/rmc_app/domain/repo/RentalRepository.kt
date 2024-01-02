package com.digitalarchitects.rmc_app.domain.repo

import com.digitalarchitects.rmc_app.model.Rental
import com.digitalarchitects.rmc_app.remote.dto.rental.CreateRentalDTO
import com.digitalarchitects.rmc_app.remote.dto.rental.UpdateRentalDTO

/**
 * Repository retrieves Rental data from underlying data source (remote and local).
 */
interface RentalRepository {

    /** Retrieves list of [Rental] from underlying data source */
    suspend fun getAllRentals(): List<Rental>

    /** Retrieves list of [Rental] from local data source */
    suspend fun getAllRentalsFromLocalCache(): List<Rental>

    /** Retrieves all [Rental] from remote data source and updates the local data source */
    suspend fun getAllRentalsFromRemote()

    /** Retrieves [Rental] by id from underlying data source */
    suspend fun getRentalById(rentalId: Int): Rental?

    /** Retrieves [Rental] for a specific user from underlying data source */
    suspend fun getRentalsForUser(userId: Int): List<Rental>?

    /** Retrieves [Rental] for a specific vehicle from underlying data source */
    suspend fun getRentalsForVehicle(vehicleId: Int): List<Rental>?

    /** Adds [Rental] to the underlying data source */
    suspend fun addRental(createRentalDTO: CreateRentalDTO, rental: Rental)

    /** Updates the [Rental] to the underlying data source */
    suspend fun updateRental(rentalId: String, updatedRental: UpdateRentalDTO)

    /** Deletes the [Rental] from the underlying data source */
    suspend fun deleteRental(rental: Rental): Result<Unit>

    /** Retreives the rental date from the [Rental] */
    suspend fun getRentalDate(rentalId: Int): String?

}