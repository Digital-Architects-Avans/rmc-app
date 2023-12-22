package com.digitalarchitects.rmc_app.data.rentacar

sealed class RentACarUIEvent {
    data class ShowListView(val show: Boolean) : RentACarUIEvent()
    data class RmcMapVehicleItemClicked(val id: Int) : RentACarUIEvent()
}