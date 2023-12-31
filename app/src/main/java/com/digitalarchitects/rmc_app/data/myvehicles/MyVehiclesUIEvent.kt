package com.digitalarchitects.rmc_app.data.myvehicles

import com.digitalarchitects.rmc_app.data.editmyvehicle.EditMyVehicleUIEvent

sealed interface MyVehiclesUIEvent {
    object NavigateUpButtonClicked: MyVehiclesUIEvent

    object NewVehicleButtonClicked: MyVehiclesUIEvent

    object ShowVehicles: MyVehiclesUIEvent

}