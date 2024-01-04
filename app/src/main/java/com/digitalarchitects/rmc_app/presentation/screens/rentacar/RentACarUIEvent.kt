package com.digitalarchitects.rmc_app.presentation.screens.rentacar

sealed interface RentACarUIEvent {
    class ShowListView(val show: Boolean) : RentACarUIEvent
    class RmcMapVehicleItemClicked(val id: String) : RentACarUIEvent
}