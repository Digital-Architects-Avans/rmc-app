package com.digitalarchitects.rmc_app.presentation.screens.register

data class RegisterUIState(
    val isLoading: Boolean = false,
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var telephone: String = "",
    var password: String = "",
    var address: String = "",
    var postalCode: String = "",
    var buildingNumber: String = "",
    var city: String = "",
    var privacyPolicyAccepted :Boolean = false

)
