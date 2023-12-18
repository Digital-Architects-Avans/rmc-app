package com.digitalarchitects.rmc_app.data.rentacar

sealed class RentACarUIEvent {
    object ViewListButtonClicked : RentACarUIEvent()
}