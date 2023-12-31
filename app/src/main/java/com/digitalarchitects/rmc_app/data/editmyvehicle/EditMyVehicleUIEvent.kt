package com.digitalarchitects.rmc_app.data.editmyvehicle

import com.digitalarchitects.rmc_app.model.EngineType

sealed interface EditMyVehicleUIEvent {
    object ShowVehicle: EditMyVehicleUIEvent
    object EngineTypeICEButtonClicked: EditMyVehicleUIEvent
    object EngineTypeBEVButtonClicked: EditMyVehicleUIEvent
    object EngineTypeFCEVButtonClicked: EditMyVehicleUIEvent
    object AvailabilityToggleButtonClicked: EditMyVehicleUIEvent

    object NavigateUpButtonClicked: EditMyVehicleUIEvent
    object ConfirmEditMyVehicleButtonClicked:EditMyVehicleUIEvent
    object CancelEditMyVehicleButtonClicked:EditMyVehicleUIEvent
    object DeleteMyVehicleButtonClicked:EditMyVehicleUIEvent
    data class SetId(val id: Int) : EditMyVehicleUIEvent
    data class SetUserId(val userId: Int) : EditMyVehicleUIEvent
    data class SetBrand(val brand: String) : EditMyVehicleUIEvent
    data class SetModel(val model: String) : EditMyVehicleUIEvent
    data class SetYear(val year: Int) : EditMyVehicleUIEvent
    data class SetVehicleClass(val vehicleClass: String) : EditMyVehicleUIEvent
    data class SetEngineType(val engineType: EngineType) : EditMyVehicleUIEvent
    data class SetLicensePlate(val licensePlate: String) : EditMyVehicleUIEvent
    data class SetImgLink(val imgLink: String) : EditMyVehicleUIEvent
    data class SetLatitude(val latitude: Float) : EditMyVehicleUIEvent
    data class SetLongitude(val longitude: Float) : EditMyVehicleUIEvent
    data class SetPrice(val price: Double) : EditMyVehicleUIEvent
    data class SetAvailability(val availability: Boolean) : EditMyVehicleUIEvent
}