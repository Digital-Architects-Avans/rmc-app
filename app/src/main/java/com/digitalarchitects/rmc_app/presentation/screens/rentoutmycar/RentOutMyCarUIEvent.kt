package com.digitalarchitects.rmc_app.presentation.screens.rentoutmycar

sealed interface RentOutMyCarUIEvent {
    object FetchRentals : RentOutMyCarUIEvent
    data class ShowRentalDetails(val tab: RentalTab, val index: Int) : RentOutMyCarUIEvent
    data class RejectRental(val rentalId: String) : RentOutMyCarUIEvent
    data class AcceptRental(val rentalId: String) : RentOutMyCarUIEvent
    object CancelShowRentalDetails : RentOutMyCarUIEvent
    data class SelectTab(val tab: RentalTab) : RentOutMyCarUIEvent
}