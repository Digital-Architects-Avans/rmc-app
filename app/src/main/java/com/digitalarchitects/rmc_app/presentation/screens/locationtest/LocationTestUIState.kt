package com.digitalarchitects.rmc_app.presentation.screens.locationtest

import com.google.android.gms.maps.model.LatLng

sealed interface LocationTestUIState {
    object Loading : LocationTestUIState
    data class Success(val location: LatLng?) : LocationTestUIState
    object RevokedPermissions : LocationTestUIState
}