package com.digitalarchitects.rmc_app.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface VehicleDao {

    @Query("SELECT * FROM LocalVehicle ORDER BY id ASC")
    suspend fun getAllVehicles(): List<LocalVehicle>

    @Query("SELECT * FROM LocalVehicle WHERE id = :vehicleId")
    suspend fun getVehicleById(vehicleId: Int): LocalVehicle

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllVehicles(vehicles: List<LocalVehicle>)

    @Query("SELECT model FROM LocalVehicle WHERE id = :vehicleId")
    suspend fun getVehicleModel(vehicleId: Int): String?

    @Insert
    suspend fun insertVehicle(vehicle: LocalVehicle): Long

    @Upsert
    suspend fun upsertVehicle(vehicle: LocalVehicle)

    @Delete
    suspend fun deleteVehicle(vehicle: LocalVehicle)

//     TODO GET CURRENT Vehicle
//    @Query("SELECT * FROM VehicleEntity LIMIT 1")
//    fun getVehicle(): VehicleTable
}