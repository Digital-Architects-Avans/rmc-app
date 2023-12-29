package com.digitalarchitects.rmc_app.model

import kotlinx.serialization.Serializable


/**
 * Note, imgLink is set to Int datatype, in the API it is String data type!
 */
@Serializable
data class Vehicle(
    val id: Int,
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

enum class EngineType{
    ICE, BEV, FCEV
}