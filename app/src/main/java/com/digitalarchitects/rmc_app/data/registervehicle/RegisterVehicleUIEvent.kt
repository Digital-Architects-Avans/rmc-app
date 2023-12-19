package com.digitalarchitects.rmc_app.data.registervehicle

import com.digitalarchitects.rmc_app.data.myvehicles.MyVehiclesUIEvent

sealed interface RegisterVehicleUIEvent {
    object NavigateUpButtonClicked: RegisterVehicleUIEvent
}