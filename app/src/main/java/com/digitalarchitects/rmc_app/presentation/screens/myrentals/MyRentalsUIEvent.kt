package com.digitalarchitects.rmc_app.presentation.screens.myrentals

sealed interface MyRentalsUIEvent {
    object FetchMyRentals : MyRentalsUIEvent
    data class ShowRentalDetails(val tab: MyRentalTab, val index: Int) : MyRentalsUIEvent
    data class CancelRental(val rentalId: String) : MyRentalsUIEvent
    data class RouteToRental(val rentalId: String) : MyRentalsUIEvent
    object CancelShowRentalDetails : MyRentalsUIEvent
    data class SelectTab(val tab: MyRentalTab) : MyRentalsUIEvent
}