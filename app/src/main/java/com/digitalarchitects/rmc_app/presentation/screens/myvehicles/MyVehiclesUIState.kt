package com.digitalarchitects.rmc_app.presentation.screens.myvehicles

import com.digitalarchitects.rmc_app.domain.model.Vehicle

data class MyVehiclesUIState(
    var listOfVehicles: List<Vehicle> = emptyList(),
    val isLoading: Boolean = false
)