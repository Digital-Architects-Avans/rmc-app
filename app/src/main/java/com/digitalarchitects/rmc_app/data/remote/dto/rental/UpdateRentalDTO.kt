package com.digitalarchitects.rmc_app.data.remote.dto.rental

import com.digitalarchitects.rmc_app.domain.model.RentalStatus
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class UpdateRentalDTO(
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