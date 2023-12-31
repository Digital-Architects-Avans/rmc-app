package com.digitalarchitects.rmc_app.data.welcome

sealed class WelcomeUIEvent {
    object RegisterButtonClicked : WelcomeUIEvent()
    object LoginButtonClicked : WelcomeUIEvent()
    object Authorized : WelcomeUIEvent()
    object Unauthorized : WelcomeUIEvent()
    object UnknownError : WelcomeUIEvent()
    object NoConnectionError : WelcomeUIEvent()
}