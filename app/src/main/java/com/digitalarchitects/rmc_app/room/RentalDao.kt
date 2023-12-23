package com.digitalarchitects.rmc_app.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RentalDao {

    @Query("SELECT * FROM rentaltable ORDER BY id ASC")
    fun getRentalOrderedById(): Flow<List<RentalTable>>

    // TODO GET CURRENT Vehicle
//    @Query("SELECT * FROM rentaltable LIMIT 1")
//    fun getRental(): RentalTable

    @Query("SELECT date FROM rentaltable LIMIT 1")
    fun getRentalDate(): String?

    @Upsert
    suspend fun upsertRental(rental: RentalTable)

    @Insert
    suspend fun insertRental(rental: RentalTable)

    @Delete
    suspend fun deleteRental(rental: RentalTable)
}