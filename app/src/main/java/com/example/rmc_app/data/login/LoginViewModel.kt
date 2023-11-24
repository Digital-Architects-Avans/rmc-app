package com.example.rmc_app.data.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {

    // Login UI state
    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                _uiState.value = _uiState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                _uiState.value = _uiState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.LoginButtonClicked -> {
                login()
            }

        }
    }

    private fun login() {
        //TODO Implement login function (API) with validation
    }

}