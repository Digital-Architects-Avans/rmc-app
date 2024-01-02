package com.digitalarchitects.rmc_app.data.login

data class LoginUIState(
    var isLoading: Boolean = false,
    var email: String = "",
    var password: String = ""
)