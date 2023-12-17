package com.digitalarchitects.rmc_app.data.rentacar

import com.digitalarchitects.rmc_app.data.editmyaccount.EditMyAccountUIEvent

sealed interface RentACarUIEvent {
    object ConfirmEditMyAccountButtonClicked: RentACarUIEvent

}