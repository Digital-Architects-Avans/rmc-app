package com.digitalarchitects.rmc_app.remote.dto.rental

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class CreateRentalDTO(
    val date: LocalDate
)