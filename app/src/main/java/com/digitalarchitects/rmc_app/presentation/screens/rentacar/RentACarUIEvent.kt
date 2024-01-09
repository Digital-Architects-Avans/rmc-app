package com.digitalarchitects.rmc_app.presentation.screens.rentacar

sealed interface RentACarUIEvent {

    // Map controls
    class ShowListView(val show: Boolean) : RentACarUIEvent
    class RmcMapVehicleItemClicked(val id: String) : RentACarUIEvent

    // Rental
    data class DateChanged(val date: String) : RentACarUIEvent
    object ReserveVehicleButtonClicked : RentACarUIEvent

    // Permissions
    object PermissionsGranted : RentACarUIEvent
    object PermissionsRevoked : RentACarUIEvent
    class ShowPermissionDialog(val show: Boolean) : RentACarUIEvent
}