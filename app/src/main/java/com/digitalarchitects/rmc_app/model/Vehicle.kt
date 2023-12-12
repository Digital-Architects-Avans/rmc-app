package com.digitalarchitects.rmc_app.model

import kotlinx.serialization.Serializable

@Serializable
data class Vehicle(
    val id: Int,
    val userId: Int,
    val brand: String,
    val model: String,
    val year: Int,
    val vehicleClass: String,
    val engineType: String,
    val licensePlate: String,
    val imgLink: Int,
    val latitude: Float,
    val longitude: Float,
    val price: Double,
    val availability: Boolean
)