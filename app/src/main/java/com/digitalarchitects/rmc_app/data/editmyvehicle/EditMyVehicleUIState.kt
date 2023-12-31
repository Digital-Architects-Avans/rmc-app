package com.digitalarchitects.rmc_app.data.editmyvehicle

import com.digitalarchitects.rmc_app.model.EngineType

data class EditMyVehicleUIState (
    val id: Int = 1,
    val userId: Int = 1,
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