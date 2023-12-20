package com.digitalarchitects.rmc_app.data.register

import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.app.RmcScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel : ViewModel() {
    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()

    // Login UI state
    private val _uiState = MutableStateFlow(RegisterUIState())
    val uiState: StateFlow<RegisterUIState> = _uiState.asStateFlow()

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
                register()
                _navigateToScreen.value = RmcScreen.RentACar
            }

            is RegisterUIEvent.PrivacyPolicyCheckBoxClicked -> {
                _uiState.value = _uiState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }

            is RegisterUIEvent.NavigateUpButtonClicked -> {
                _navigateToScreen.value = RmcScreen.Welcome

            }
        }
    }

    private fun register() {
        //TODO Implement register function (API) with validation
    }
}