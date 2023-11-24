package com.digitalarchitects.rmc_app.data.register

data class RegisterUIState(
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
