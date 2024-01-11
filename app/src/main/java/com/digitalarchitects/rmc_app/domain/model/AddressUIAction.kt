package com.digitalarchitects.rmc_app.domain.model

sealed class AddressUIAction {

    object OnAddressAutoCompleteDone : AddressUIAction()
    object OnAddressAutoCompleteClear : AddressUIAction()
    data class OnAddressChange(val address: String) : AddressUIAction()
    data class OnAddressSelected(val selectedPlaceItem: PlaceItem) : AddressUIAction()
}