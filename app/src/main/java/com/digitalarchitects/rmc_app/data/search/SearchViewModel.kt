package com.digitalarchitects.rmc_app.data.search

import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.app.RmcScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchViewModel : ViewModel() {
    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()

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
            engineTypeCev = true,
            engineTypeFbev = true
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
                    engineTypeCev = !event.selected
                )
            }

            is SearchUIEvent.EngineTypeFbevChanged -> {
                _uiState.value = _uiState.value.copy(
                    engineTypeFbev = !event.selected
                )
            }

            is SearchUIEvent.ApplyFiltersButtonClicked -> {
                // TODO apply filter logic
                _navigateToScreen.value = RmcScreen.RentACar
            }

            is SearchUIEvent.NavigateUpButtonClicked -> {
                _navigateToScreen.value = RmcScreen.RentACar
            }
        }
    }

    fun applyFilters() {
        TODO("Implement callback to NavHost with filters to Rent A Car view for search")
    }

    fun clearFilters() {
        _uiState.value = _uiState.value.copy(
            date = "",
            location = "",
            price = 0.0,
            distance = 0,
            engineTypeIce = true,
            engineTypeCev = true,
            engineTypeFbev = true
        )
    }
}
