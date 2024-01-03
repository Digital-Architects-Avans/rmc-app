package com.digitalarchitects.rmc_app.presentation.screens.myaccount


sealed interface MyAccountUIEvent {
    object InsertUser: MyAccountUIEvent
    object UpsertUser: MyAccountUIEvent
    object ShowUser: MyAccountUIEvent
    object onEditMyAccountButtonClicked: MyAccountUIEvent
    object onMyVehiclesButtonClicked : MyAccountUIEvent
    object onRentOutMyCarButtonClicked: MyAccountUIEvent
    object onMyRentalsButtonClicked: MyAccountUIEvent
    object onLogoutButtonClicked: MyAccountUIEvent
}