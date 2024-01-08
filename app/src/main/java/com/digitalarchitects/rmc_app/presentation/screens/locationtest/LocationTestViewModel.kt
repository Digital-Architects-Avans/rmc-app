package com.digitalarchitects.rmc_app.presentation.screens.locationtest

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.data.remote.ILocationService
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.S)
@HiltViewModel
class LocationTestViewModel @Inject constructor(
    private val locationService: ILocationService
) : ViewModel() {
    operator fun invoke(): Flow<LatLng?> = locationService.requestLocationUpdates()

    private val _uiState: MutableStateFlow<LocationTestUIState> =
        MutableStateFlow(LocationTestUIState.Loading)
    val uiState: StateFlow<LocationTestUIState> = _uiState.asStateFlow()

    /* This function is responsible for updating the ViewState based
       on the event coming from the view */
    fun handle(event: LocationTestUIEvent) {
        when (event) {
            LocationTestUIEvent.Granted -> {
                viewModelScope.launch {
                    invoke().collect { location ->
                        _uiState.value = LocationTestUIState.Success(location)
                    }
                }
            }

            LocationTestUIEvent.Revoked -> {
                _uiState.value = LocationTestUIState.RevokedPermissions
            }
        }
    }
}
