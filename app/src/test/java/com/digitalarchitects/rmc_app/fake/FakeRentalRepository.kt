package com.digitalarchitects.rmc_app.fake

import FakeLocalDataSource
import com.digitalarchitects.rmc_app.data.mapper.toRental
import com.digitalarchitects.rmc_app.data.mapper.toRentalListFromLocal
import com.digitalarchitects.rmc_app.data.remote.dto.rental.CreateRentalDTO
import com.digitalarchitects.rmc_app.data.remote.dto.rental.UpdateRentalDTO
import com.digitalarchitects.rmc_app.domain.model.Rental
import com.digitalarchitects.rmc_app.domain.model.RentalStatus
import com.digitalarchitects.rmc_app.domain.model.User
import com.digitalarchitects.rmc_app.domain.model.Vehicle
import com.digitalarchitects.rmc_app.domain.repo.RentalRepository

class FakeRentalRepository: RentalRepository {
    override suspend fun getAllRentals(): List<Rental> {
        return FakeLocalDataSource.rentalList.toRentalListFromLocal()
    }

    override suspend fun getAllRentalsFromLocalCache(): List<Rental> {
        return FakeLocalDataSource.rentalList.toRentalListFromLocal()
    }

    override suspend fun getAllRentalsFromRemote() {
        return
    }

    override suspend fun getRentalById(rentalId: String): Rental? {
        return FakeLocalDataSource.rentalList.firstOrNull { it.rentalId == rentalId }?.toRental()
    }

    override suspend fun getRentalsForRenter(userId: String): List<Rental>? {
        TODO("Not yet implemented")
    }

    override suspend fun getRentalsForVehicle(vehicleId: String): List<Rental>? {
        TODO("Not yet implemented")
    }

    override suspend fun getListOfRentalDetailsForRenter(userId: String): List<Triple<Rental, Vehicle, User>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRentalDetails(rentalId: String): Triple<Rental, Vehicle, User> {
        TODO("Not yet implemented")
    }

    override suspend fun getListOfRentalDetailsForOwner(userId: String): List<Triple<Rental, Vehicle, User>> {
        TODO("Not yet implemented")
    }

    override suspend fun addRental(createRentalDTO: CreateRentalDTO) {
        TODO("Not yet implemented")
    }

    override suspend fun updateRental(rentalId: String, updatedRental: UpdateRentalDTO) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRental(rental: Rental): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getRentalDate(rentalId: String): String? {
        TODO("Not yet implemented")
    }

    override suspend fun setRentalStatus(rentalId: String, status: RentalStatus) {
        TODO("Not yet implemented")
    }
}