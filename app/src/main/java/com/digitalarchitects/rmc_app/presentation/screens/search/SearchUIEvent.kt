package com.digitalarchitects.rmc_app.presentation.screens.search


sealed class SearchUIEvent {
    object FetchFilterPreference : SearchUIEvent()
    data class DateChanged(val date: String) : SearchUIEvent()
    data class LocationChanged(val location: String) : SearchUIEvent()
    data class PriceChanged(val price: Float) : SearchUIEvent()
    data class DistanceChanged(val distance: Float) : SearchUIEvent()
    data class EngineTypeIceChanged(val selected: Boolean) : SearchUIEvent()
    data class EngineTypeBevChanged(val selected: Boolean) : SearchUIEvent()
    data class EngineTypeFcevChanged(val selected: Boolean) : SearchUIEvent()
    object ApplyFiltersButtonClicked : SearchUIEvent()
    object ClearFiltersButtonClicked : SearchUIEvent()
}