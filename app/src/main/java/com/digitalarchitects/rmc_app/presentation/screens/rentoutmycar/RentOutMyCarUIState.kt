package com.digitalarchitects.rmc_app.presentation.screens.rentoutmycar

import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.domain.model.RentalStatus

data class RentOutMyCarUIState(
    var selectedTab: RentalTab = RentalTab.PENDING
)

enum class RentalTab(val tabNameResourceId: Int) {
    PENDING(R.string.pending),
    OPEN(R.string.open),
    HISTORY(R.string.history)
}