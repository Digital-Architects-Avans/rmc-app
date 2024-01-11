package com.digitalarchitects.rmc_app.data.remote.dto.vehicle

import com.digitalarchitects.rmc_app.domain.model.EngineType
import kotlinx.serialization.Serializable

@Serializable
data class RemoteVehicle(
    val _id: String,
    val vehicleId: String,
    val userId: String,
    val brand: String,
    val model: String,
    val year: Int,
    val vehicleClass: String,
    val engineType: EngineType,
    val licensePlate: String,
    val imgLink: Int,
    val description: String,
    val address: String,
    val latitude: Float,
    val longitude: Float,
    val price: Double,
    val availability: Boolean
)