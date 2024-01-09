package com.digitalarchitects.rmc_app.presentation.screens.search

// TODO: Hoist all search settings to Rent A Car screen to show results

data class SearchUIState(
    val date: String = "", // TODO: Make LocalDate, maybe with LocalDate.now()
    val location: String = "",
    val price: Double = 0.0,
    val distance: Int = 0,
    val engineTypeIce: Boolean = true,
    val engineTypeBev: Boolean = true,
    val engineTypeFcev: Boolean = true
)
