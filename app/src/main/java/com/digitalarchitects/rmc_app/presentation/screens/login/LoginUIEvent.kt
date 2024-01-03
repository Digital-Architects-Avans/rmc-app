package com.digitalarchitects.rmc_app.presentation.screens.login

sealed class LoginUIEvent {
    data class EmailChanged(val email: String) : LoginUIEvent()
    data class PasswordChanged(val password: String) : LoginUIEvent()
    object LoginButtonClicked : LoginUIEvent()
    object RegisterButtonClicked: LoginUIEvent()
    object NavigateUpButtonClicked: LoginUIEvent()
    object Authorized : LoginUIEvent()
    object Unauthorized : LoginUIEvent()
    object UnknownError : LoginUIEvent()
    object NoConnectionError : LoginUIEvent()


}