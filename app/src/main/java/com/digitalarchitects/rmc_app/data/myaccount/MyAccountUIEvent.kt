package com.digitalarchitects.rmc_app.data.myaccount

import com.digitalarchitects.rmc_app.room.UserTable

sealed interface MyAccountUIEvent {
    data class ShowUser(val user: UserTable): MyAccountUIEvent
    object onEditMyAccountButtonClicked: MyAccountUIEvent
    object onMyVehiclesButtonClicked : MyAccountUIEvent
    object onRentOutMyCarButtonClicked: MyAccountUIEvent
    object onMyRentalsButtonClicked: MyAccountUIEvent
    object onLogoutButtonClicked: MyAccountUIEvent
}