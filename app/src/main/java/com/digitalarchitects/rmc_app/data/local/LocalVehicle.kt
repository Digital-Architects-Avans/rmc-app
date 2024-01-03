package com.digitalarchitects.rmc_app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.digitalarchitects.rmc_app.domain.model.EngineType

@Entity
class LocalVehicle(
    @PrimaryKey(autoGenerate = false)
    val vehicleId: String,
    val userId: Int,
    val brand: String,
    val model: String,
    val year: Int,
    val vehicleClass: String,
    val engineType: EngineType,
    val licensePlate: String,
    val imgLink: Int,
    val latitude: Float,
    val longitude: Float,
    val price: Double,
    val availability: Boolean
)
