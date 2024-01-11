package com.digitalarchitects.rmc_app.presentation.screens.registervehicle

import com.digitalarchitects.rmc_app.domain.model.PlaceItem

sealed interface RegisterVehicleUIEvent {
    object FetchUserId : RegisterVehicleUIEvent
    object EngineTypeICEButtonClicked : RegisterVehicleUIEvent
    object EngineTypeBEVButtonClicked : RegisterVehicleUIEvent
    object EngineTypeFCEVButtonClicked : RegisterVehicleUIEvent
    object AvailabilityToggleButtonClicked : RegisterVehicleUIEvent
    object ConfirmRegisterVehicleButtonClicked : RegisterVehicleUIEvent
    object CancelRegisterVehicleButtonClicked:RegisterVehicleUIEvent
    data class SetBrand(val brand: String) : RegisterVehicleUIEvent
    data class SetModel(val model: String) : RegisterVehicleUIEvent
    data class SetYear(val year: Int) : RegisterVehicleUIEvent
    data class SetVehicleClass(val vehicleClass: String) : RegisterVehicleUIEvent
    data class SetLicensePlate(val licensePlate: String) : RegisterVehicleUIEvent
    data class SetImgLink(val imgLink: Int) : RegisterVehicleUIEvent
    data class SetDescription(val description: String) : RegisterVehicleUIEvent
    data class SetPrice(val price: Double) : RegisterVehicleUIEvent
    object ResetVehicleUpdated: RegisterVehicleUIEvent
    object OnAddressAutoCompleteClear : RegisterVehicleUIEvent
    data class OnAddressChange(val address: String) : RegisterVehicleUIEvent
    data class OnAddressSelected(val selectedPlaceItem: PlaceItem) : RegisterVehicleUIEvent
}