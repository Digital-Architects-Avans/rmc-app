package com.digitalarchitects.rmc_app.data.rentacar

import com.digitalarchitects.rmc_app.data.editmyaccount.EditMyAccountUIEvent

sealed interface RentACarUIEvent {
    object MyAccountButtonClicked: RentACarUIEvent
    object SearchButtonClicked: RentACarUIEvent
    object RentOutMyVehicleButtonClicked: RentACarUIEvent
    object MyRentalsButtonClicked: RentACarUIEvent
}