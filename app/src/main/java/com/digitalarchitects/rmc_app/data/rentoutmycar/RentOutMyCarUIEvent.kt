package com.digitalarchitects.rmc_app.data.rentoutmycar

sealed interface RentOutMyCarUIEvent {
    object NavigateUpButtonClicked: RentOutMyCarUIEvent

}