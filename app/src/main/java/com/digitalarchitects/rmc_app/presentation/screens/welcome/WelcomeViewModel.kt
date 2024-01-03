package com.digitalarchitects.rmc_app.presentation.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.data.auth.AuthResult
import com.digitalarchitects.rmc_app.domain.repo.UserRepository
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import com.digitalarchitects.rmc_app.presentation.screens.register.RegisterUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()

    private val _uiState = MutableStateFlow(RegisterUIState())
    val uiState: StateFlow<RegisterUIState> = _uiState.asStateFlow()

    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResult = resultChannel.receiveAsFlow()

    // Authenticate user on init by checking validity of token in shared preferences
    // Disable if you would like to test the welcome / register / login screens
    init {
        authenticate()
    }

    fun onEvent(event: WelcomeUIEvent) {
        when (event) {

            is WelcomeUIEvent.RegisterButtonClicked -> {
                _navigateToScreen.value = RmcScreen.Register
            }

            is WelcomeUIEvent.LoginButtonClicked -> {
                _navigateToScreen.value = RmcScreen.Login
            }

            is WelcomeUIEvent.Authorized -> {
                _navigateToScreen.value = RmcScreen.RentACar
            }

            is WelcomeUIEvent.Unauthorized -> {
                return
            }

            is WelcomeUIEvent.NoConnectionError -> {
                _navigateToScreen.value = RmcScreen.Welcome
            }

            is WelcomeUIEvent.UnknownError -> {
                _navigateToScreen.value = RmcScreen.Welcome
            }
        }
    }

    // Authenticate user using the JWT token stored in shared preferences
    private fun authenticate() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val result = userRepository.authenticate()
            resultChannel.send(result)
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }
}