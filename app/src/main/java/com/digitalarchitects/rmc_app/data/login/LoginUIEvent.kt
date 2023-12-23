package com.digitalarchitects.rmc_app.data.login

import com.digitalarchitects.rmc_app.data.editmyaccount.EditMyAccountUIEvent

sealed class LoginUIEvent {
    data class EmailChanged(val email: String) : LoginUIEvent()
    data class PasswordChanged(val password: String) : LoginUIEvent()
    object LoginButtonClicked : LoginUIEvent()
    object RegisterButtonClicked: LoginUIEvent()
    object NavigateUpButtonClicked: LoginUIEvent()


}