package com.digitalarchitects.rmc_app.presentation.screens.myaccount


sealed interface MyAccountUIEvent {
    object InsertUser: MyAccountUIEvent
    object UpsertUser: MyAccountUIEvent
    object ShowUser: MyAccountUIEvent
    object OnLogoutButtonClicked: MyAccountUIEvent
}