package com.digitalarchitects.rmc_app.presentation.screens.rentoutmycar

import androidx.lifecycle.ViewModel
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

    private val _state = MutableStateFlow(RentOutMyCarUIState())
    private val _uiState = _state
    val uiState: StateFlow<RentOutMyCarUIState> get() = _uiState.asStateFlow()
}