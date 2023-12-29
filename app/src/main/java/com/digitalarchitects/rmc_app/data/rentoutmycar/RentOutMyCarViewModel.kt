package com.digitalarchitects.rmc_app.data.rentoutmycar

import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.app.RmcScreen
import com.digitalarchitects.rmc_app.domain.repo.RentalRepository
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RentOutMyCarViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository,
    private val rentalRepository: RentalRepository
) : ViewModel() {
    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()

    private val _state = MutableStateFlow(RentOutMyCarUIState())
    private val _uiState = _state
    val uiState: StateFlow<RentOutMyCarUIState> get() = _uiState.asStateFlow()

    fun onEvent(event: RentOutMyCarUIEvent) {
        when (event) {
            is RentOutMyCarUIEvent.NavigateUpButtonClicked -> {
                _navigateToScreen.value = RmcScreen.RentACar
            }
        }
    }
}