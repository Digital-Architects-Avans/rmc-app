package com.digitalarchitects.rmc_app.presentation.screens.locationtest

sealed interface LocationTestUIEvent {
    object Granted : LocationTestUIEvent
    object Revoked : LocationTestUIEvent
}