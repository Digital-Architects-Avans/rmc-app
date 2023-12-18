package com.digitalarchitects.rmc_app.data.rentacar

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RentACarViewModel : ViewModel() {

    // Rent A Car UI state
    private val _uiState = MutableStateFlow(RentACarUIState())
    val uiState: StateFlow<RentACarUIState> = _uiState.asStateFlow()

    fun onEvent(event: RentACarUIEvent) {
        TODO("Implement events")
    }

    fun viewListButtonClicked() {
        val showListViewSheet = _uiState.value.showListViewSheet
        _uiState.value = _uiState.value.copy(
            showListViewSheet = !showListViewSheet
        )
    }
}