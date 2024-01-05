package com.digitalarchitects.rmc_app.presentation.screens.rentacar

import com.digitalarchitects.rmc_app.domain.model.Vehicle
import com.google.android.gms.maps.model.LatLng

data class RentACarUIState(
    // Google Maps
    val startLocation: LatLng = LatLng(51.587959, 4.775130),
    val userLocation: LatLng = LatLng(51.469890, 5.546670),

    var listOfVehicles: List<Vehicle> = emptyList(),
    var vehicleMapItems: List<VehicleMapItem> = emptyList(),

    var showVehicleList: Boolean = false,
    var activeVehicleId: String? = null
)