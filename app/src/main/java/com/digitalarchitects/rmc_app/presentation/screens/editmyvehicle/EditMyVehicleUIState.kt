package com.digitalarchitects.rmc_app.presentation.screens.editmyvehicle

import com.digitalarchitects.rmc_app.domain.model.EngineType

data class EditMyVehicleUIState (
    val id: String = "1",
    val userId: String = "1",
    val brand: String = "",
    val model: String = "",
    val year: Int = 1990,
    val vehicleClass: String = "",
    val engineType: EngineType = EngineType.ICE,
    val licensePlate: String = "",
    val imgLink: Int = 1,
    val latitude: Float =51.72F,
    val longitude: Float = 5.42F,
    val price: Double = 99.00,
    val availability: Boolean = false
)