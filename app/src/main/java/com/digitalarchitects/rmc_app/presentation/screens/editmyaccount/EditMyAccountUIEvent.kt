package com.digitalarchitects.rmc_app.presentation.screens.editmyaccount

sealed interface EditMyAccountUIEvent {

    object FetchUser: EditMyAccountUIEvent
    data class SetEmail(val email: String) : EditMyAccountUIEvent
    data class SetPassword(val password: String) : EditMyAccountUIEvent
    data class SetFirstName(val firstName: String): EditMyAccountUIEvent
    data class SetLastName(val lastName: String) : EditMyAccountUIEvent
    data class SetPhone(val phone: String) : EditMyAccountUIEvent
    data class SetStreet(val street: String) : EditMyAccountUIEvent
    data class SetBuildingNumber(val buildingNumber: String) : EditMyAccountUIEvent
    data class SetZipCode(val zipCode: String) : EditMyAccountUIEvent
    data class SetCity(val city: String) : EditMyAccountUIEvent
    data class SetImageResourceId(val imageResourceId: Int) : EditMyAccountUIEvent
    object ConfirmEditMyAccountButtonClicked: EditMyAccountUIEvent
    object ResetUserUpdated: EditMyAccountUIEvent
}