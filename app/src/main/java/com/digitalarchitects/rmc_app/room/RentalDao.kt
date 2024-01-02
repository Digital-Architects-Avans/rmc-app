package com.digitalarchitects.rmc_app.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface RentalDao {

    @Query("SELECT * FROM LocalRental ORDER BY rentalId ASC")
    suspend fun getAllRentals(): List<LocalRental>

    @Query("SELECT * FROM LocalRental WHERE rentalId = :rentalId")
    suspend fun getRentalById(rentalId: Int): LocalRental

    @Query("SELECT * FROM LocalRental WHERE userId = :userId")
    suspend fun getRentalsForUser(userId: Int): List<LocalRental>?

    @Query("SELECT * FROM LocalRental WHERE vehicleId = :vehicleId")
    suspend fun getRentalsForVehicle(vehicleId: Int): List<LocalRental>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRentals(rentals: List<LocalRental>)

    @Query("SELECT date FROM LocalRental WHERE rentalId = :rentalId")
    suspend fun getRentalDate(rentalId: Int): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRental(rental: LocalRental): Long

    @Upsert
    suspend fun upsertRental(rental: LocalRental)

    @Insert
    suspend fun insertRental(rental: LocalRental)

    @Delete
    suspend fun deleteRental(rental: LocalRental)

    @Query("DELETE FROM LocalRental")
    suspend fun clearCache()

// TODO GET CURRENT Vehicle
//    @Query("SELECT * FROM rentaltable LIMIT 1")
//    fun getRental(): RentalTable
}