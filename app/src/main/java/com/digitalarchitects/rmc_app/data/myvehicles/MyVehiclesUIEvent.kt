package com.digitalarchitects.rmc_app.data.myvehicles

sealed interface MyVehiclesUIEvent {
    object NavigateUpButtonClicked: MyVehiclesUIEvent

    object NewVehicleButtonClicked: MyVehiclesUIEvent

}