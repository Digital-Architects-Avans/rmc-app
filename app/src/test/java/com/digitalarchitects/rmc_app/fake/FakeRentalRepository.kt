package com.digitalarchitects.rmc_app.fake

import FakeLocalDataSource
import com.digitalarchitects.rmc_app.data.mapper.toRental
import com.digitalarchitects.rmc_app.data.mapper.toRentalListFromLocal
import com.digitalarchitects.rmc_app.domain.repo.RentalRepository
import com.digitalarchitects.rmc_app.model.Rental
import com.digitalarchitects.rmc_app.remote.dto.rental.CreateRentalDTO

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

    override suspend fun getRentalById(rentalId: Int): Rental? {
        return FakeLocalDataSource.rentalList.firstOrNull { it.id == rentalId }?.toRental()
    }

    override suspend fun addRental(createRentalDTO: CreateRentalDTO, rental: Rental) {
        TODO("Not yet implemented")
    }


    override suspend fun updateRental(rental: Rental) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRental(rental: Rental): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getRentalDate(rentalId: Int): String? {
        TODO("Not yet implemented")
    }
}