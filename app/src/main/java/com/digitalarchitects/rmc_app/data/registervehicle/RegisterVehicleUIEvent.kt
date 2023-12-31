package com.digitalarchitects.rmc_app.data.registervehicle

import com.digitalarchitects.rmc_app.model.EngineType

sealed interface RegisterVehicleUIEvent {
    object EngineTypeICEButtonClicked : RegisterVehicleUIEvent
    object EngineTypeBEVButtonClicked : RegisterVehicleUIEvent
    object EngineTypeFCEVButtonClicked : RegisterVehicleUIEvent
    object AvailabilityToggleButtonClicked : RegisterVehicleUIEvent
    object NavigateUpButtonClicked : RegisterVehicleUIEvent
    object ConfirmRegisterVehicleButtonClicked : RegisterVehicleUIEvent

    //    object CancelRegisterVehicleButtonClicked:RegisterVehicleUIEvent
    data class SetId(val id: Int) : RegisterVehicleUIEvent
    data class SetUserId(val userId: Int) : RegisterVehicleUIEvent
    data class SetBrand(val brand: String) : RegisterVehicleUIEvent
    data class SetModel(val model: String) : RegisterVehicleUIEvent
    data class SetYear(val year: Int) : RegisterVehicleUIEvent
    data class SetVehicleClass(val vehicleClass: String) : RegisterVehicleUIEvent
    data class SetEngineType(val engineType: EngineType) : RegisterVehicleUIEvent
    data class SetLicensePlate(val licensePlate: String) : RegisterVehicleUIEvent
    data class SetImgLink(val imgLink: String) : RegisterVehicleUIEvent
    data class SetLatitude(val latitude: Float) : RegisterVehicleUIEvent
    data class SetLongitude(val longitude: Float) : RegisterVehicleUIEvent
    data class SetPrice(val price: Double) : RegisterVehicleUIEvent
    data class SetAvailability(val availability: Boolean) : RegisterVehicleUIEvent
}