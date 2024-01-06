package com.digitalarchitects.rmc_app.data.remote.dto.rental

import com.digitalarchitects.rmc_app.domain.model.RentalStatus
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class RemoteRental(
    val _id: String,
    val rentalId: String,
    val vehicleId: String,
    val userId: String,
    val date: LocalDate,
    val price: Double,
    val latitude: Float,
    val longitude: Float,
    val status: RentalStatus,
    val distanceTravelled: Double,
    val score: Int
)
