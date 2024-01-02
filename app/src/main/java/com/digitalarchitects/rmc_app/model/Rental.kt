package com.digitalarchitects.rmc_app.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

enum class RentalStatus(val status: String) {
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    DENIED("DENIED"),
    CANCELLED("CANCELLED")
}

@Serializable
data class Rental(
    val rentalId: String,
    val vehicleId: Int,
    val userId: Int,
    val date: LocalDate,
    val price: Double,
    val latitude: Float,
    val longitude: Float,
    val status: RentalStatus,
    val distanceTravelled: Double,
    val score: Int
)
