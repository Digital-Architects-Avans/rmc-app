package com.digitalarchitects.rmc_app.data.welcome

sealed class WelcomeUIEvent {
    object LoginButtonClicked : WelcomeUIEvent()
    object RegisterButtonClicked : WelcomeUIEvent()
}

