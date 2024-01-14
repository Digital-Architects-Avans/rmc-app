package com.digitalarchitects.rmc_app.presentation.screens.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.data.di.IoDispatcher
import com.digitalarchitects.rmc_app.domain.model.PlaceItem
import com.digitalarchitects.rmc_app.domain.repo.PlacesRepository
import com.digitalarchitects.rmc_app.domain.repo.UserPreferencesRepository
import com.digitalarchitects.rmc_app.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val placesRepository: PlacesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUIState())
    val uiState: StateFlow<SearchUIState> = _uiState.asStateFlow()

    private val _placePredictions: MutableStateFlow<List<PlaceItem>> =
        MutableStateFlow(arrayListOf())
    val placePredictions = _placePredictions.asStateFlow()

    private fun getPlacePredictions(query: String) {
        viewModelScope.launch(dispatcher) {

            when (val placePredictionsResult = placesRepository.getPlacePredictions(query)) {
                is Result.Success -> {
                    val placePredictions = placePredictionsResult.data

                    _placePredictions.value = placePredictions
                }

                is Result.Error -> {
                    Log.e(
                        "RegisterVehicleViewModel",
                        "An error occurred when retrieving the predictions for $query",
                        placePredictionsResult.exception
                    )
                }

                else -> {}
            }
        }
    }

    private suspend fun setLocationPrediction(placeItem: PlaceItem) {
        Log.d("RegisterVehicleViewModel", "placeItem: $placeItem")

        viewModelScope.launch(dispatcher) {
            when (val addressResult = placesRepository.getLocationFromPlace(placeItem.id!!)) {

                is Result.Success -> {
                    val addressFromPlace = addressResult.data
                    if (addressFromPlace != null) {
                        Log.d("RegisterVehicleViewModel", "addressFromPlace: $addressFromPlace")

                        val query = if (addressFromPlace.streetAddress == " ") {
                            addressFromPlace.city + ", " + addressFromPlace.country
                        } else {
                            addressFromPlace.streetAddress + ", " + addressFromPlace.city
                        }

                        _uiState.update {
                            it.copy(
                                latitude = addressFromPlace.latitude.toFloat(),
                                longitude = addressFromPlace.longitude.toFloat(),
                                query = query
                            )
                        }
                    }
                    clearPredictions()
                }

                is Result.Error -> {
                    Log.e(
                        "RegisterVehicleViewModel",
                        "An error occurred when retrieving the address from Place  ${placeItem.id}",
                        addressResult.exception
                    )
                }
                else -> {}
            }
        }
    }

    private fun onLocationAutoCompleteClear() {
        Log.d("RegisterVehicleViewModel", "onLocationAutoCompleteClear")
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    query = ""
                )
            }
            clearPredictions()
        }
    }

    private fun clearPredictions() {
        _placePredictions.value = mutableListOf()
    }

    fun onEvent(event: SearchUIEvent) {
        when (event) {
            is SearchUIEvent.DateChanged -> {
                _uiState.value = _uiState.value.copy(
                    date = event.date
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
                    try {
                        withContext(dispatcher) {
                            val filterPreferences =
                                userPreferencesRepository.getFilterPreference()
                            Log.d("SearchViewModel", "date: ${filterPreferences.date}")

                            // If user has set a date preference, convert it to LocalDate else null
                            val dateAsLocalDate: LocalDate? =
                                if (filterPreferences.date.isNullOrEmpty()) {
                                    LocalDate.parse(filterPreferences.date)
                                } else {
                                    null
                                }

                            _uiState.value = _uiState.value.copy(
                                date = dateAsLocalDate,
                                latitude = filterPreferences.latitude,
                                longitude = filterPreferences.longitude,
                                price = filterPreferences.price,
                                distance = filterPreferences.distance,
                                engineTypeIce = filterPreferences.engineTypeICE,
                                engineTypeBev = filterPreferences.engineTypeBEV,
                                engineTypeFcev = filterPreferences.engineTypeFCEV
                            )
                        }
                        Log.d("Etten", "Fetched preferences successfully")

                    } catch (e: Exception) {

                        Log.d("SearchViewModel", "Error fetching filter preference: $e")
                    }
                }
            }

            is SearchUIEvent.OnAddressAutoCompleteClear -> {
                viewModelScope.launch {
                    onLocationAutoCompleteClear()
                }
            }

            is SearchUIEvent.OnAddressChange -> {
                viewModelScope.launch {
                    _uiState.update {
                        it.copy(
                            query = event.address
                        )
                    }
                    getPlacePredictions(event.address)
                }
            }

            is SearchUIEvent.OnAddressSelected -> {
                viewModelScope.launch {
                    setLocationPrediction(event.selectedPlaceItem)
                }
            }
        }
    }

    private fun applyFilters() {
        val state = _uiState.value
        viewModelScope.launch(dispatcher) {
            try {
                userPreferencesRepository.saveFilterPreference(
                    date = state.date.toString(),
                    latitude = state.latitude,
                    longitude = state.longitude,
                    price = state.price,
                    distance = state.distance,
                    engineTypeICE = state.engineTypeIce,
                    engineTypeBEV = state.engineTypeBev,
                    engineTypeFCEV = state.engineTypeFcev
                )
                Log.d("SearchViewModel", "Saved preferences successfully, $state")

                if (state.latitude != 0.0F && state.longitude != 0.0F) {
                    userPreferencesRepository.saveShowSearchLocation(true)
                    Log.d("SearchViewModel", "Set ShowSearchLocation to true")
                }
            } catch (e: Exception) {
                Log.d("SearchViewModel", "Error applying filter preference: $e")
            }
        }
    }

    private fun clearFilters() {
        _uiState.update {
            it.copy(
                date = null,
                query = "",
                latitude = 0.0F,
                longitude = 0.0F,
                price = 0.0,
                distance = 0.0,
                engineTypeIce = true,
                engineTypeBev = true,
                engineTypeFcev = true
            )
        }

        viewModelScope.launch(dispatcher) {
            try {
                userPreferencesRepository.saveShowSearchLocation(false)
                Log.d("SearchViewModel", "Set ShowSearchLocation to true")
            } catch (e: Exception) {
                Log.d("SearchViewModel", "Error clearing filter preference: $e")
            }
        }
    }
}