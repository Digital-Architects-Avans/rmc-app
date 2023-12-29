package com.digitalarchitects.rmc_app.data.login

import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.app.RmcScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()

    // Login UI state
    private val _state = MutableStateFlow(LoginUIState())
    private val _uiState = _state
    val uiState: StateFlow<LoginUIState> get() = _uiState.asStateFlow()

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
//                login()
                _navigateToScreen.value = RmcScreen.RentACar
            }

            is LoginUIEvent.RegisterButtonClicked -> {
                _navigateToScreen.value = RmcScreen.Register
            }

            is LoginUIEvent.NavigateUpButtonClicked -> {
                _navigateToScreen.value = RmcScreen.Welcome
            }
        }
    }

    private fun login() {
        //TODO Implement login function (API) with validation
    }

}