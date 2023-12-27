package com.digitalarchitects.rmc_app.data.myrentals

import com.digitalarchitects.rmc_app.R

data class MyRentalsUIState (
    var selectedTab: MyRentalTab = MyRentalTab.OPEN
)

enum class MyRentalTab(val tabNameResourceId: Int){
    OPEN(R.string.open),
    HISTORY(R.string.history)
}