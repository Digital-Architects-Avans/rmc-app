package com.digitalarchitects.rmc_app.presentation.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.data.auth.AuthResult
import com.digitalarchitects.rmc_app.data.di.IoDispatcher
import com.digitalarchitects.rmc_app.domain.repo.RentalRepository
import com.digitalarchitects.rmc_app.domain.repo.UserRepository
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val vehicleRepository: VehicleRepository,
    private val rentalRepository: RentalRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(WelcomeUIState())
    val uiState: StateFlow<WelcomeUIState> = _uiState.asStateFlow()

    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResult = resultChannel.receiveAsFlow()

    // Authenticate user on init by checking validity of token in shared preferences
    // Disable if you would like to test the welcome / register / login screens
    init {
        authenticate()
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

    fun getDataFromRemoteSource() {
        viewModelScope.launch(dispatcher) {
            userRepository.getAllUsersFromRemote()
            vehicleRepository.getAllVehiclesFromRemote()
            rentalRepository.getAllRentalsFromRemote()
        }
    }
}