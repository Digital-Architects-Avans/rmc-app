package com.digitalarchitects.rmc_app.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import com.digitalarchitects.rmc_app.data.auth.AuthResult
import com.digitalarchitects.rmc_app.domain.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResult = resultChannel.receiveAsFlow()

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
                signIn()
            }

            is LoginUIEvent.RegisterButtonClicked -> {
                _navigateToScreen.value = RmcScreen.Register
            }

            is LoginUIEvent.NavigateUpButtonClicked -> {
                _navigateToScreen.value = RmcScreen.Welcome
            }

            is LoginUIEvent.Authorized -> {
                _navigateToScreen.value = RmcScreen.RmcTestScreen
            }

            is LoginUIEvent.Unauthorized -> {
                _navigateToScreen.value = RmcScreen.Welcome
            }

            is LoginUIEvent.NoConnectionError -> {
                _navigateToScreen.value = RmcScreen.Welcome
            }

            is LoginUIEvent.UnknownError -> {
                _navigateToScreen.value = RmcScreen.Welcome
            }
        }
    }

    private fun signIn() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val result = userRepository.signIn(
                username = uiState.value.email,
                password = uiState.value.password
            )
            resultChannel.send(result)
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }

}