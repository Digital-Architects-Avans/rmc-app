package com.digitalarchitects.rmc_app.data.search

import com.digitalarchitects.rmc_app.data.myrentals.MyRentalsUIEvent

sealed class SearchUIEvent {
    data class DateChanged(val date: String) : SearchUIEvent()
    data class LocationChanged(val location: String) : SearchUIEvent()
    data class PriceChanged(val price: Float) : SearchUIEvent()
    data class DistanceChanged(val distance: Float) : SearchUIEvent()
    data class EngineTypeIceChanged(val selected: Boolean) : SearchUIEvent()
    data class EngineTypeCevChanged(val selected: Boolean) : SearchUIEvent()
    data class EngineTypeFbevChanged(val selected: Boolean) : SearchUIEvent()
    object NavigateUpButtonClicked: SearchUIEvent()
    object ApplyFiltersButtonClicked: SearchUIEvent()
}