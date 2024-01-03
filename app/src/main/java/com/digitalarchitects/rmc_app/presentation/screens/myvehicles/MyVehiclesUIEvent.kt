package com.digitalarchitects.rmc_app.presentation.screens.myvehicles

import com.digitalarchitects.rmc_app.presentation.screens.editmyvehicle.EditMyVehicleUIEvent

sealed interface MyVehiclesUIEvent {
    object NavigateUpButtonClicked: MyVehiclesUIEvent

    object NewVehicleButtonClicked: MyVehiclesUIEvent

    object ShowVehicles: MyVehiclesUIEvent

}