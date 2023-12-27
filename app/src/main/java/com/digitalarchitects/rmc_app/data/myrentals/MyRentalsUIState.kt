package com.digitalarchitects.rmc_app.data.myrentals

import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.data.rentoutmycar.RentalTab

data class MyRentalsUIState (
    var selectedTab: RentalTab = RentalTab.PENDING
)

enum class MyRentalTab(val tabNameResourceId: Int){
    OPEN(R.string.open),
    HISTORY(R.string.history)
}