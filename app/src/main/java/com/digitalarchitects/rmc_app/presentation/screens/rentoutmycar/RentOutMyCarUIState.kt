package com.digitalarchitects.rmc_app.presentation.screens.rentoutmycar

import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.domain.model.Rental
import com.digitalarchitects.rmc_app.domain.model.User
import com.digitalarchitects.rmc_app.domain.model.Vehicle

data class RentOutMyCarUIState(
    var selectedTab: RentalTab = RentalTab.PENDING,
    val isLoading: Boolean = false,
    var listOfRentalsForUser: List<Triple<Rental, Vehicle, User>> = emptyList(),
    var pendingRentalsList: List<Triple<Rental, Vehicle, User>> = emptyList(),
    var openRentalsList: List<Triple<Rental, Vehicle, User>> = emptyList(),
    var historyRentalsList: List<Triple<Rental, Vehicle, User>> = emptyList(),
    val selectedRentalItem: Triple<Rental, Vehicle, User>? = null
)

enum class RentalTab(val tabNameResourceId: Int) {
    PENDING(R.string.pending),
    OPEN(R.string.open),
    HISTORY(R.string.history)
}