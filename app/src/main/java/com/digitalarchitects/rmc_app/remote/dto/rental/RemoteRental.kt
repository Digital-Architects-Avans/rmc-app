package com.digitalarchitects.rmc_app.remote.dto.rental

import com.digitalarchitects.rmc_app.model.RentalStatus
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class RemoteRental(
    val objectId: String,
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
