package com.digitalarchitects.rmc_app.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.digitalarchitects.rmc_app.model.EngineType
import java.math.BigDecimal

@Entity
class VehicleTable (
    val userId: Int,
    val brand: String,
    val model: String,
    val year: Int,
    val vehicleClass: String,
    val engineType: EngineType = EngineType.ICE,
    val licensePlate: String,
    val imgLink: Int,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
    val price: Double,
    val availability: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int
)