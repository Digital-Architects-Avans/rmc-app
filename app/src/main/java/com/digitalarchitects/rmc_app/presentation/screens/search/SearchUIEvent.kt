package com.digitalarchitects.rmc_app.presentation.screens.search

import com.digitalarchitects.rmc_app.domain.model.PlaceItem
import kotlinx.datetime.LocalDate

sealed interface SearchUIEvent {
    object FetchFilterPreference : SearchUIEvent
    data class DateChanged(val date: LocalDate) : SearchUIEvent
    data class PriceChanged(val price: Float) : SearchUIEvent
    data class DistanceChanged(val distance: Float) : SearchUIEvent
    data class EngineTypeIceChanged(val selected: Boolean) : SearchUIEvent
    data class EngineTypeBevChanged(val selected: Boolean) : SearchUIEvent
    data class EngineTypeFcevChanged(val selected: Boolean) : SearchUIEvent
    object ApplyFiltersButtonClicked : SearchUIEvent
    object ClearFiltersButtonClicked : SearchUIEvent
    object OnAddressAutoCompleteClear : SearchUIEvent
    data class OnAddressChange(val address: String) : SearchUIEvent
    data class OnAddressSelected(val selectedPlaceItem: PlaceItem) : SearchUIEvent
}