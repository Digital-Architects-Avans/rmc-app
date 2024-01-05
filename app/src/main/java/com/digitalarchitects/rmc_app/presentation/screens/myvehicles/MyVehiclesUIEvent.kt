package com.digitalarchitects.rmc_app.presentation.screens.myvehicles

sealed interface MyVehiclesUIEvent {

    object NewVehicleButtonClicked: MyVehiclesUIEvent

    object ShowVehicles: MyVehiclesUIEvent

}