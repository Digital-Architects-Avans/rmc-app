package com.digitalarchitects.rmc_app.presentation.screens.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.data.di.IoDispatcher
import com.digitalarchitects.rmc_app.domain.repo.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUIState())
    val uiState: StateFlow<SearchUIState> = _uiState.asStateFlow()

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
                    distance = event.distance.toDouble()
                )
            }

            is SearchUIEvent.EngineTypeIceChanged -> {
                _uiState.value = _uiState.value.copy(
                    engineTypeIce = !event.selected
                )
            }

            is SearchUIEvent.EngineTypeBevChanged -> {
                _uiState.value = _uiState.value.copy(
                    engineTypeBev = !event.selected
                )
            }

            is SearchUIEvent.EngineTypeFcevChanged -> {
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

            is SearchUIEvent.FetchFilterPreference -> {
                viewModelScope.launch(dispatcher) {
                    try{
                        withContext(Dispatchers.IO) {
                            val filterPreferences = userPreferencesRepository.getFilterPreference()

                            _uiState.value = _uiState.value.copy(
                                date = filterPreferences.date,
                                location = filterPreferences.location,
                                price = filterPreferences.price,
                                distance = filterPreferences.distance,
                                engineTypeIce = filterPreferences.engineTypeICE,
                                engineTypeBev = filterPreferences.engineTypeBEV,
                                engineTypeFcev = filterPreferences.engineTypeFCEV
                            )
                        }
                        Log.d("SearchViewModel", "Fetched preferences successfully")

                    } catch (e: Exception){
                        Log.d("SearchViewModel", "Error fetching filter preference: $e")
                    }
                }
            }
        }
    }

    private fun applyFilters() {
        val state = _uiState.value
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {

                    userPreferencesRepository.saveFilterPreference(
                        date = state.date,
                        location = state.location,
                        price = state.price,
                        distance = state.distance,
                        engineTypeICE = state.engineTypeIce,
                        engineTypeBEV = state.engineTypeBev,
                        engineTypeFCEV = state.engineTypeFcev
                    )
                }
                Log.d("SearchViewModel", "Saved preferences successfully")

            } catch (e: Exception) {
                Log.d("SearchViewModel", "Error applying filter preference: $e")
            }
        }
    }

    private fun clearFilters() {
        _uiState.value = _uiState.value.copy(
            date = "",
            location = "",
            price = 0.0,
            distance = 0.0,
            engineTypeIce = true,
            engineTypeBev = true,
            engineTypeFcev = true
        )
    }
}
