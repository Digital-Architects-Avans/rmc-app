package com.digitalarchitects.rmc_app.presentation.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.data.auth.AuthResult
import com.digitalarchitects.rmc_app.data.auth.SignUpRequest
import com.digitalarchitects.rmc_app.data.di.IoDispatcher
import com.digitalarchitects.rmc_app.domain.model.UserType
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
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val vehicleRepository: VehicleRepository,
    private val rentalRepository: RentalRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUIState())
    val uiState: StateFlow<RegisterUIState> = _uiState.asStateFlow()

    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResult = resultChannel.receiveAsFlow()

    fun onEvent(event: RegisterUIEvent) {
        when (event) {
            is RegisterUIEvent.FirstNameChanged -> {
                _uiState.value = _uiState.value.copy(
                    firstName = event.firstName
                )
            }

            is RegisterUIEvent.LastNameChanged -> {
                _uiState.value = _uiState.value.copy(
                    lastName = event.lastName
                )
            }

            is RegisterUIEvent.EmailChanged -> {
                _uiState.value = _uiState.value.copy(
                    email = event.email
                )
            }

            is RegisterUIEvent.TelephoneChanged -> {
                _uiState.value = _uiState.value.copy(
                    telephone = event.telephone
                )
            }

            is RegisterUIEvent.PasswordChanged -> {
                _uiState.value = _uiState.value.copy(
                    password = event.password
                )
            }

            is RegisterUIEvent.AddressChanged -> {
                _uiState.value = _uiState.value.copy(
                    address = event.address
                )
            }

            is RegisterUIEvent.PostalCodeChanged -> {
                _uiState.value = _uiState.value.copy(
                    postalCode = event.postalCode
                )
            }

            is RegisterUIEvent.BuildingNumberChanged -> {
                _uiState.value = _uiState.value.copy(
                    buildingNumber = event.buildingNumber
                )
            }

            is RegisterUIEvent.CityChanged -> {
                _uiState.value = _uiState.value.copy(
                    city = event.city
                )
            }

            is RegisterUIEvent.RegisterButtonClicked -> {
                signUp()
            }

            is RegisterUIEvent.PrivacyPolicyCheckBoxClicked -> {
                _uiState.value = _uiState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val result = userRepository.signUp(
                SignUpRequest(
                    email = uiState.value.email,
                    password = uiState.value.password,
                    userType = UserType.CLIENT,
                    firstName = uiState.value.firstName,
                    lastName = uiState.value.lastName,
                    phone = uiState.value.telephone,
                    street = uiState.value.address,
                    buildingNumber = uiState.value.buildingNumber,
                    zipCode = uiState.value.postalCode,
                    city = uiState.value.city
                )
            )
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