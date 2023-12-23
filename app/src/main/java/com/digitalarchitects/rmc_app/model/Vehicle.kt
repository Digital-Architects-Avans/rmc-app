package com.digitalarchitects.rmc_app.model

import java.math.BigDecimal


/**
 * Note, imgLink is set to Int datatype, in the API it is String data type!
 */
data class Vehicle(
    val id: Int,
    val userId: Int,
    val brand: String,
    val model: String,
    val year: Int,
    val vehicleClass: String,
    val engineType: EngineType,
    val licensePlate: String,
    val imgLink: Int,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
    val price: Double,
    val availability: Boolean
)

enum class EngineType{
    ICE, BEV, FCEV
}