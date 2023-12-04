package com.digitalarchitects.rmc_app.model

import java.math.BigDecimal
import java.time.LocalDate

enum class RentalStatus(val status: String) {
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    DENIED("DENIED"),
    CANCELLED("CANCELLED")
}
data class Rental(
    val id: Int,
    val vehicleId: Int,
    val userId: Int,
    val date: LocalDate,
    val price: Double,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
    val status: RentalStatus,
    val distanceTravelled: Double,
    val score: Int
)
