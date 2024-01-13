package com.digitalarchitects.rmc_app.presentation.screens.register

sealed interface RegisterUIEvent{
    data class FirstNameChanged(val firstName: String) : RegisterUIEvent
    data class LastNameChanged(val lastName: String) : RegisterUIEvent
    data class EmailChanged(val email: String): RegisterUIEvent
    data class TelephoneChanged(val telephone: String) : RegisterUIEvent
    data class PasswordChanged(val password: String) : RegisterUIEvent
    data class AddressChanged(val address: String) : RegisterUIEvent
    data class PostalCodeChanged(val postalCode: String) : RegisterUIEvent
    data class BuildingNumberChanged(val buildingNumber: String) : RegisterUIEvent
    data class CityChanged(val city: String) : RegisterUIEvent
    data class PrivacyPolicyCheckBoxClicked(val status:Boolean) : RegisterUIEvent
    object RegisterButtonClicked : RegisterUIEvent
}