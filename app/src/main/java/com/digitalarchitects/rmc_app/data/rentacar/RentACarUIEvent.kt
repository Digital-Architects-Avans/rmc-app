package com.digitalarchitects.rmc_app.data.rentacar

sealed class RentACarUIEvent {
    object ToggleListView: RentACarUIEvent()
    object ToggleDetailsView: RentACarUIEvent()
}