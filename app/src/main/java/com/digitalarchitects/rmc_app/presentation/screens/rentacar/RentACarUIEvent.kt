package com.digitalarchitects.rmc_app.presentation.screens.rentacar

import com.google.android.gms.maps.model.LatLng
import kotlinx.datetime.LocalDate

sealed interface RentACarUIEvent {
    // Intro
    class ShowIntro(val show: Boolean) : RentACarUIEvent

    // Map controls
    data class ZoomLevelChanged(val zoom: Float) : RentACarUIEvent
    data class CameraPositionChanged(val position: LatLng) : RentACarUIEvent
    class ShowListView(val show: Boolean) : RentACarUIEvent
    class RmcMapVehicleItemClicked(val id: String) : RentACarUIEvent

    // Rental
    data class DateChanged(val date: LocalDate) : RentACarUIEvent
    object ReserveVehicleButtonClicked : RentACarUIEvent

    // Permissions
    object PermissionsGranted : RentACarUIEvent
    object PermissionsRevoked : RentACarUIEvent
    class ShowPermissionDialog(val show: Boolean) : RentACarUIEvent

    // Filter preference
    object FetchFilterPreference : RentACarUIEvent
}