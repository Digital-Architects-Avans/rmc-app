package com.digitalarchitects.rmc_app.presentation.screens.rentacar

import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.domain.model.Vehicle
import com.google.android.gms.maps.model.LatLng
import kotlinx.datetime.LocalDate


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
    var zoomLevel: Float = 10f,
    var cameraPosition: LatLng = startLocation,

    // Vehicle data
    var listOfVehicles: List<Vehicle> = emptyList(),
    var vehicleMapItems: List<VehicleMapItem> = emptyList(),
    var showVehicleList: Boolean = false,

    // User & Rental data
    var activeVehicleId: String? = null,
    var date: LocalDate? = null,
    var userId: String = "",
    var userAvatar: Int = R.drawable.usericon,

    // Filter preference
    val datePreference: LocalDate = LocalDate(2021, 1, 1),
    val latitude: Float = 0.0F,
    val longitude: Float = 0.0F,
    val price: Double = 0.0,
    val distance: Double = 0.0,
    val engineTypeIce: Boolean = true,
    val engineTypeBev: Boolean = true,
    val engineTypeFcev: Boolean = true,
    val showSearchLocation: Boolean = false,

    // Stats
    var showStats: Boolean = true,
    var searchResults: Int = 0,
    var statsRenterTotalRentals: Int = 0,
    var statsRenterPendingRentals: Int = 0,
    var statsRenterOpenRentals: Int = 0,
    var statsOwnerTotalRentals: Int = 0,
    var statsOwnerOpenRentals: Int = 0,
    var statsOwnerPendingRentals: Int = 0,
)