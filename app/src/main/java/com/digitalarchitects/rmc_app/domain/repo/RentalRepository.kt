package com.digitalarchitects.rmc_app.domain.repo

import com.digitalarchitects.rmc_app.data.remote.dto.rental.CreateRentalDTO
import com.digitalarchitects.rmc_app.data.remote.dto.rental.UpdateRentalDTO
import com.digitalarchitects.rmc_app.domain.model.Rental
import com.digitalarchitects.rmc_app.domain.model.RentalStatus
import com.digitalarchitects.rmc_app.domain.model.User
import com.digitalarchitects.rmc_app.domain.model.Vehicle

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
    suspend fun getRentalById(rentalId: String): Rental?

    /** Retrieves [Rental] for a specific user from underlying data source */
    suspend fun getRentalsForUser(userId: String): List<Rental>?

    /** Retrieves [Rental] for a specific vehicle from underlying data source */
    suspend fun getRentalsForVehicle(vehicleId: String): List<Rental>?

    /** Retrieves [Rental] [Vehicle] and [User] for a specific rental from underlying data source */
    suspend fun getRentalDetails(rentalId: String): Triple<Rental, Vehicle, User>

    /** Retrieves a list of Triple containing rental details for a specific user from underlying data source */
    suspend fun getListOfRentalDetailsForOwner(userId: String): List<Triple<Rental, Vehicle, User>>

    /** Adds [Rental] to the underlying data source */
    suspend fun addRental(createRentalDTO: CreateRentalDTO, rental: Rental)

    /** Updates the [Rental] to the underlying data source */
    suspend fun updateRental(rentalId: String, updatedRental: UpdateRentalDTO)

    /** Deletes the [Rental] from the underlying data source */
    suspend fun deleteRental(rental: Rental): Result<Unit>

    /** Retreives the rental date from the [Rental] */
    suspend fun getRentalDate(rentalId: String): String?

    /** Set the rental status */
    suspend fun setRentalStatus(rentalId: String, status: RentalStatus)

}