package com.digitalarchitects.rmc_app.presentation.screens.search

import kotlinx.datetime.LocalDate

// TODO: Hoist all search settings to Rent A Car screen to show results

data class SearchUIState(
    var date: LocalDate? = null,
    val latitude: Float = 0.0F,
    val longitude: Float = 0.0F,
    val price: Double = 0.0,
    val distance: Double = 0.0,
    val engineTypeIce: Boolean = true,
    val engineTypeBev: Boolean = true,
    val engineTypeFcev: Boolean = true
)
