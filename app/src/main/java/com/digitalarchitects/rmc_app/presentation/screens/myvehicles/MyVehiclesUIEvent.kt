package com.digitalarchitects.rmc_app.presentation.screens.myvehicles

sealed interface MyVehiclesUIEvent {

    object FetchVehicles: MyVehiclesUIEvent
    class ShowVehicleDetails(val vehicleId: String?): MyVehiclesUIEvent
    object CancelShowVehicleDetails: MyVehiclesUIEvent
    class DeleteVehicle(val vehicleId: String): MyVehiclesUIEvent
}