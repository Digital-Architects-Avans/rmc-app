package com.digitalarchitects.rmc_app.remote.dto.vehicle

import com.digitalarchitects.rmc_app.model.EngineType
import kotlinx.serialization.Serializable

@Serializable
data class RemoteVehicle(
    val objectId: String,
    val vehicleId: String,
    val userId: Int,
    val brand: String,
    val model: String,
    val year: Int,
    val vehicleClass: String,
    val engineType: EngineType,
    val licensePlate: String,
    val imgLink: String,
    val latitude: Float,
    val longitude: Float,
    val price: Double,
    val availability: Boolean
)