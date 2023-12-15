package com.digitalarchitects.rmc_app.data.myaccount


sealed interface MyAccountUIEvent {
    object UpsertUser: MyAccountUIEvent
    object ShowUser: MyAccountUIEvent
    object onEditMyAccountButtonClicked: MyAccountUIEvent
    object onMyVehiclesButtonClicked : MyAccountUIEvent
    object onRentOutMyCarButtonClicked: MyAccountUIEvent
    object onMyRentalsButtonClicked: MyAccountUIEvent
    object onLogoutButtonClicked: MyAccountUIEvent
}