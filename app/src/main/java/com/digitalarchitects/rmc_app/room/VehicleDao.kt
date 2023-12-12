package com.digitalarchitects.rmc_app.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {

    @Query("SELECT * FROM vehicletable ORDER BY id ASC")
    fun getVehiclessOrderedById(): Flow<List<VehicleTable>>

    // TODO GET CURRENT Vehicle
//    @Query("SELECT * FROM vehicletable LIMIT 1")
//    fun getVehicle(): VehicleTable

    @Query("SELECT model FROM vehicletable LIMIT 1")
    fun getVehicleModel(): String?

    @Upsert
    suspend fun upsertVehicle(vehicle: VehicleTable)

    @Insert
    suspend fun insertUser(vehicle: VehicleTable)

//    @Delete
//    suspend fun deleteVehicle()
}