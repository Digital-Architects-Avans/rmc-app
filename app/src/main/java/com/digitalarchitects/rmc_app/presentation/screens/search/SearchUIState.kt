package com.digitalarchitects.rmc_app.presentation.screens.search

import kotlinx.datetime.LocalDate

data class SearchUIState(
    var date: LocalDate? = null,
    val query: String = "",
    val latitude: Float = 0.0F,
    val longitude: Float = 0.0F,
    val price: Double = 0.0,
    val distance: Double = 0.0,
    val engineTypeIce: Boolean = true,
    val engineTypeBev: Boolean = true,
    val engineTypeFcev: Boolean = true
)
