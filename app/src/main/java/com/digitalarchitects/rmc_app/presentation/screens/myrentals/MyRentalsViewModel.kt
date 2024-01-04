package com.digitalarchitects.rmc_app.presentation.screens.myrentals

import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MyRentalsViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MyRentalsUIState())
    private val _uiState = _state
    val uiState: StateFlow<MyRentalsUIState> get() = _uiState.asStateFlow()

}
