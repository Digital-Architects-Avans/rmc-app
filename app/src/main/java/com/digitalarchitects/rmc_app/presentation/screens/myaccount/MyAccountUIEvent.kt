package com.digitalarchitects.rmc_app.presentation.screens.myaccount

sealed interface MyAccountUIEvent {
    object FetchUser : MyAccountUIEvent
    object OnLogoutButtonClicked : MyAccountUIEvent
}