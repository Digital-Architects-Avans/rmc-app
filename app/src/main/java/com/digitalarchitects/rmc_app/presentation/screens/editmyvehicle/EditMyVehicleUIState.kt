package com.digitalarchitects.rmc_app.presentation.screens.editmyvehicle

import com.digitalarchitects.rmc_app.domain.model.EngineType

data class EditMyVehicleUIState (
    val vehicleId: String = "",
    val userId: String = "",
    val brand: String = "",
    val model: String = "",
    val year: Int = 1990,
    val vehicleClass: String = "",
    val engineType: EngineType = EngineType.ICE,
    val licensePlate: String = "",
    val imgLink: Int = 1,
    val description: String = "",
    val address: String = "",
    val latitude: Float = 0.00F,
    val longitude: Float = 0.00F,
    val price: Double = 0.00,
    val availability: Boolean = false,

    var vehicleUpdated: Boolean = false
)