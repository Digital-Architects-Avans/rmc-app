package com.digitalarchitects.rmc_app.presentation.screens.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUIState())
    val uiState: StateFlow<SearchUIState> = _uiState.asStateFlow()

    // TODO: Initialize search options based on current settings, passed in by the NavHost
    init {
        _uiState.value = _uiState.value.copy(
            date = "Date from NavHost",
            location = "Location from NavHost",
            price = 0.0,
            distance = 0,
            engineTypeIce = true,
            engineTypeBev = false,
            engineTypeFcev = true
        )
    }

    fun onEvent(event: SearchUIEvent) {
        when (event) {
            is SearchUIEvent.DateChanged -> {
                _uiState.value = _uiState.value.copy(
                    date = event.date
                )
            }

            is SearchUIEvent.LocationChanged -> {
                _uiState.value = _uiState.value.copy(
                    location = event.location
                )
            }

            is SearchUIEvent.PriceChanged -> {
                _uiState.value = _uiState.value.copy(
                    price = event.price.toDouble()
                )
            }

            is SearchUIEvent.DistanceChanged -> {
                _uiState.value = _uiState.value.copy(
                    distance = event.distance.toInt()
                )
            }

            is SearchUIEvent.EngineTypeIceChanged -> {
                _uiState.value = _uiState.value.copy(
                    engineTypeIce = !event.selected
                )
            }

            is SearchUIEvent.EngineTypeCevChanged -> {
                _uiState.value = _uiState.value.copy(
                    engineTypeBev = !event.selected
                )
            }

            is SearchUIEvent.EngineTypeFbevChanged -> {
                _uiState.value = _uiState.value.copy(
                    engineTypeFcev = !event.selected
                )
            }

            is SearchUIEvent.ClearFiltersButtonClicked -> {
                clearFilters()
            }

            is SearchUIEvent.ApplyFiltersButtonClicked -> {
                applyFilters()
            }
        }
    }

    private fun applyFilters() {
        TODO("Implement callback to NavHost with filters to Rent A Car view for search")
    }

    private fun clearFilters() {
        _uiState.value = _uiState.value.copy(
            date = "",
            location = "",
            price = 0.0,
            distance = 0,
            engineTypeIce = true,
            engineTypeBev = true,
            engineTypeFcev = true
        )
    }
}
