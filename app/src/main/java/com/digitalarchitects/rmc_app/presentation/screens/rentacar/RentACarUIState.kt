package com.digitalarchitects.rmc_app.presentation.screens.rentacar

import com.digitalarchitects.rmc_app.domain.model.Vehicle
import com.google.android.gms.maps.model.LatLng

// Location permissions UI state
sealed interface LocationPermissionsUIState {
    object Loading : LocationPermissionsUIState
    data class Success(val location: LatLng?) : LocationPermissionsUIState
    object RevokedPermissions : LocationPermissionsUIState
}

// Rent A Car UI state
data class RentACarUIState(
    // Google Maps
    val startLocation: LatLng = LatLng(51.587959, 4.775130),
    var userLocation: LatLng? = null,

    // Vehicle data
    var listOfVehicles: List<Vehicle> = emptyList(),
    var vehicleMapItems: List<VehicleMapItem> = emptyList(),

    // UI trackers
    var showVehicleList: Boolean = false,
    var activeVehicleId: String? = null,
    var showRationaleDialog: Boolean = true
)