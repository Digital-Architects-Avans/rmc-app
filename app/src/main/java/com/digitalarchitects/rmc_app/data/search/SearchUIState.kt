package com.digitalarchitects.rmc_app.data.search

// TODO: Hoist all search settings to Rent A Car screen to show results

data class SearchUIState(
    val date: String? = "Date!", // TODO: Make LocalDate, maybe with LocalDate.now()
    val location: String? = "Location!",
    val price: Double? = 55.0,
    val distance: Int? = 53,
    val engineTypeIce: Boolean = true,
    val engineTypeCev: Boolean = true,
    val engineTypeFbev: Boolean = true
)
