package com.digitalarchitects.rmc_app.domain.model

import kotlinx.serialization.Serializable

enum class EngineType{
    ICE, BEV, FCEV
}

@Serializable
data class Vehicle(
    val vehicleId: String,
    val userId: String,
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