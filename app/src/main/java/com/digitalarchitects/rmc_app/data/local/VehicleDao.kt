package com.digitalarchitects.rmc_app.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface VehicleDao {

    @Query("SELECT * FROM LocalVehicle ORDER BY vehicleId ASC")
    suspend fun getAllVehicles(): List<LocalVehicle>

    @Query("SELECT * FROM LocalVehicle WHERE vehicleId = :vehicleId")
    suspend fun getVehicleById(vehicleId: String): LocalVehicle

    @Query("SELECT * FROM LocalVehicle WHERE userId = :userId")
    suspend fun getVehiclesForUser(userId: String): List<LocalVehicle>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllVehicles(vehicles: List<LocalVehicle>)

    @Query("SELECT model FROM LocalVehicle WHERE vehicleId = :vehicleId")
    suspend fun getVehicleModel(vehicleId: String): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicle(vehicle: LocalVehicle): Long

    @Upsert
    suspend fun upsertVehicle(vehicle: LocalVehicle)

    @Delete
    suspend fun deleteVehicle(vehicle: LocalVehicle)

    @Query("DELETE FROM LocalVehicle")
    suspend fun clearCache()

//     TODO GET CURRENT Vehicle
//    @Query("SELECT * FROM VehicleEntity LIMIT 1")
//    fun getVehicle(): VehicleTable
}