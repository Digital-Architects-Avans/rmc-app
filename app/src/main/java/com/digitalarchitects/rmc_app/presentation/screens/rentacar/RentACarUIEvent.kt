package com.digitalarchitects.rmc_app.presentation.screens.rentacar

sealed interface RentACarUIEvent {
    object SearchButtonClicked: RentACarUIEvent
    object RentOutMyVehicleButtonClicked: RentACarUIEvent
    object MyRentalsButtonClicked: RentACarUIEvent
    object MyAccountButtonClicked: RentACarUIEvent
    class ShowListView(val show: Boolean) : RentACarUIEvent
    class RmcMapVehicleItemClicked(val id: Int): RentACarUIEvent
}