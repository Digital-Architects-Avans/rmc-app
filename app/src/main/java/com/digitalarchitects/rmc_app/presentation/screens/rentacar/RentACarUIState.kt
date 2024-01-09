package com.digitalarchitects.rmc_app.presentation.screens.rentacar

import com.digitalarchitects.rmc_app.domain.model.Vehicle
import com.google.android.gms.maps.model.LatLng
import java.time.LocalDate

enum class PermissionsStatus(val status: String) {
    PENDING(status = "PENDING"),
    GRANTED(status = "GRANTED"),
    REVOKED(status = "REVOKED"),
}

// Location permissions UI state
sealed interface LocationPermissionsUIState {
    object PendingPermissions : LocationPermissionsUIState
    data class GrantedPermissions(val location: LatLng?) : LocationPermissionsUIState
    object RevokedPermissions : LocationPermissionsUIState
}

// Rent A Car UI state
data class RentACarUIState(
    // Permissions
    var permissionStatus: PermissionsStatus = PermissionsStatus.PENDING,
    var showRationaleDialog: Boolean = true,

    // Google Maps
    val startLocation: LatLng = LatLng(51.587959, 4.775130),
    var userLocation: LatLng? = null,

    // Vehicle data
    var listOfVehicles: List<Vehicle> = emptyList(),
    var vehicleMapItems: List<VehicleMapItem> = emptyList(),
    var showVehicleList: Boolean = false,

    // Rental data
    var activeVehicleId: String? = null,
    var date: String = LocalDate.now().plusDays(1).toString(),
    var userId: String = "",
    var placingReservation: Boolean = false
)