package com.digitalarchitects.rmc_app.presentation.screens.myvehicles

import com.digitalarchitects.rmc_app.domain.model.Vehicle

data class MyVehiclesUIState(
    var listOfVehicles: List<Vehicle> = emptyList(),
    var listOfLocations: List<String> = emptyList(),
    var listOfLocationsDetailed: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val selectedVehicle: Vehicle? = null,
    var isAvailable: Boolean = false
)