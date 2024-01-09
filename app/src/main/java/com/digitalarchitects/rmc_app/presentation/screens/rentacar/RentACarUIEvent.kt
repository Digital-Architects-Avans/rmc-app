package com.digitalarchitects.rmc_app.presentation.screens.rentacar

sealed interface RentACarUIEvent {
    object PermissionsGranted : RentACarUIEvent
    object PermissionsRevoked : RentACarUIEvent
    class ShowListView(val show: Boolean) : RentACarUIEvent
    class ShowPermissionDialog(val show: Boolean) : RentACarUIEvent
    class RmcMapVehicleItemClicked(val id: String) : RentACarUIEvent
}