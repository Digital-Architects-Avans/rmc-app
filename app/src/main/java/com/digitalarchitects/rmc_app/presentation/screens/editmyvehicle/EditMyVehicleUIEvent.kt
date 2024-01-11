package com.digitalarchitects.rmc_app.presentation.screens.editmyvehicle

import com.digitalarchitects.rmc_app.domain.model.EngineType
import com.digitalarchitects.rmc_app.domain.model.PlaceItem

sealed interface EditMyVehicleUIEvent {

    object EngineTypeICEButtonClicked: EditMyVehicleUIEvent
    object EngineTypeBEVButtonClicked: EditMyVehicleUIEvent
    object EngineTypeFCEVButtonClicked: EditMyVehicleUIEvent
    object AvailabilityToggleButtonClicked: EditMyVehicleUIEvent
    object ConfirmEditMyVehicleButtonClicked: EditMyVehicleUIEvent
    data class SetId(val id: String) : EditMyVehicleUIEvent
    data class SetUserId(val userId: String) : EditMyVehicleUIEvent
    data class SetBrand(val brand: String) : EditMyVehicleUIEvent
    data class SetModel(val model: String) : EditMyVehicleUIEvent
    data class SetYear(val year: Int) : EditMyVehicleUIEvent
    data class SetVehicleClass(val vehicleClass: String) : EditMyVehicleUIEvent
    data class SetEngineType(val engineType: EngineType) : EditMyVehicleUIEvent
    data class SetLicensePlate(val licensePlate: String) : EditMyVehicleUIEvent
    data class SetImgLink(val imgLink: Int) : EditMyVehicleUIEvent
    data class SetDescription(val description: String) : EditMyVehicleUIEvent
    data class SetLatitude(val latitude: Float) : EditMyVehicleUIEvent
    data class SetLongitude(val longitude: Float) : EditMyVehicleUIEvent
    data class SetPrice(val price: Double) : EditMyVehicleUIEvent
    data class SetAvailability(val availability: Boolean) : EditMyVehicleUIEvent
    object ResetVehicleUpdated: EditMyVehicleUIEvent
    object OnAddressAutoCompleteClear : EditMyVehicleUIEvent
    data class OnAddressChange(val address: String) : EditMyVehicleUIEvent
    data class OnAddressSelected(val selectedPlaceItem: PlaceItem) : EditMyVehicleUIEvent
}