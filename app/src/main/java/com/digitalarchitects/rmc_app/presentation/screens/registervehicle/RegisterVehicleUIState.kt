package com.digitalarchitects.rmc_app.presentation.screens.registervehicle

import com.digitalarchitects.rmc_app.domain.model.EngineType

data class RegisterVehicleUIState(
    val userId: String = "",
    val brand: String = "",
    val model: String = "",
    val year: Int = 1999,
    val vehicleClass: String = "",
    val engineType: EngineType = EngineType.ICE,
    val licensePlate: String = "",
    val imgLink: Int = 0,
    val latitude: Float = 0.0F,
    val longitude: Float = 0.0F,
    val price: Double = 0.00,
    val availability: Boolean = false
)