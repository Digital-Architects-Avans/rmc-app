package com.digitalarchitects.rmc_app.data.registervehicle

import com.digitalarchitects.rmc_app.model.EngineType

data class RegisterVehicleUIState(
    val id: Int = 0,
    val userId: Int = 0,
    val brand: String = "",
    val model: String = "",
    val year: String = "",
    val vehicleClass: String = "",
    val engineType: EngineType = EngineType.ICE,
    val licensePlate: String = "",
    val imgLink: String = "",
    val latitude: String = "",
    val longitude: String = "",
    val price: String = "",
    val availability: Boolean = false
)