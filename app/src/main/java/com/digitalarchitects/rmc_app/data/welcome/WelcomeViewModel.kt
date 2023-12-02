package com.digitalarchitects.rmc_app.data.welcome

import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {
    fun onEvent(event: WelcomeUIEvent) {
        when (event) {
            is WelcomeUIEvent.LoginButtonClicked -> {
                TODO("Navigate to LoginScreen")
            }

            is WelcomeUIEvent.RegisterButtonClicked -> {
                TODO("Navigate to RegisterScreen")
            }
        }
    }
}