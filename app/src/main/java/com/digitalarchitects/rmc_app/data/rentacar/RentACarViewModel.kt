package com.digitalarchitects.rmc_app.data.rentacar

import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.app.RmcScreen
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RentACarViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {
    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()

    private val _state = MutableStateFlow(RentACarUIState())
    private val _uiState = _state
    val uiState: StateFlow<RentACarUIState> get() = _uiState.asStateFlow()

    fun onEvent(event: RentACarUIEvent) {
        when (event) {
            is RentACarUIEvent.MyAccountButtonClicked -> {
                _navigateToScreen.value = RmcScreen.MyAccount
            }
            is RentACarUIEvent.MyRentalsButtonClicked -> {
                _navigateToScreen.value = RmcScreen.MyRentals
            }
            is RentACarUIEvent.RentOutMyVehicleButtonClicked -> {
                _navigateToScreen.value = RmcScreen.RentOutMyCar
            }
            is RentACarUIEvent.SearchButtonClicked -> {
                _navigateToScreen.value = RmcScreen.Search
            }
        }
    }
}