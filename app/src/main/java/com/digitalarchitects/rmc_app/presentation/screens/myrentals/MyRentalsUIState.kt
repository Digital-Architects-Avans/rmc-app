package com.digitalarchitects.rmc_app.presentation.screens.myrentals

import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.domain.model.Rental
import com.digitalarchitects.rmc_app.domain.model.User
import com.digitalarchitects.rmc_app.domain.model.Vehicle

data class MyRentalsUIState(
    var selectedTab: MyRentalTab = MyRentalTab.OPEN,
    val isLoading: Boolean = false,
    var myRentalsList: List<Triple<Rental, Vehicle, User>> = emptyList(),
    var myOpenRentalsList: List<Triple<Rental, Vehicle, User>> = emptyList(),
    var myHistoryRentalList: List<Triple<Rental, Vehicle, User>> = emptyList(),
    val selectedRentalItem: Triple<Rental, Vehicle, User>? = null,
    val routeToRental: String? = null,
)

enum class MyRentalTab(val tabNameResourceId: Int) {
    OPEN(R.string.open),
    HISTORY(R.string.history)
}