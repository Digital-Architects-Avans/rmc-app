package com.digitalarchitects.rmc_app.presentation.screens.rentacar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.data.di.IoDispatcher
import com.digitalarchitects.rmc_app.data.remote.ILocationService
import com.digitalarchitects.rmc_app.domain.model.Vehicle
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.S)
@HiltViewModel
class RentACarViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository,
    private val locationService: ILocationService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    // Rent A Car UI state
    private val _rentACarUiState = MutableStateFlow(RentACarUIState())
    val rentACarUiState: StateFlow<RentACarUIState> get() = _rentACarUiState.asStateFlow()

    // Location permissions UI state
    private val _locationPermissionsUiState: MutableStateFlow<LocationPermissionsUIState> =
        MutableStateFlow(LocationPermissionsUIState.PendingPermissions)
    val locationPermissionsUiState: StateFlow<LocationPermissionsUIState> =
        _locationPermissionsUiState.asStateFlow()

    // Fix me!
    // ☐ Get search settings from DataStore
    // ✓ Get all vehicles
    // ☐ Filter all vehicles on search settings in getVehicles
    // ✓ Pass vehicleList to screen via UIState

    fun onEvent(event: RentACarUIEvent) {
        when (event) {
            is RentACarUIEvent.ShowListView -> {
                _rentACarUiState.value = _rentACarUiState.value.copy(
                    showVehicleList = event.show
                )
            }

            is RentACarUIEvent.RmcMapVehicleItemClicked -> {
                _rentACarUiState.value = _rentACarUiState.value.copy(
                    activeVehicleId = event.id,
                    showVehicleList = false,
                )
            }

            // Invoke locationService when permissions are granted
            is RentACarUIEvent.PermissionsGranted -> {
                viewModelScope.launch {
                    invoke().collect { location ->
                        _rentACarUiState.value.userLocation = location
                        _locationPermissionsUiState.value =
                            LocationPermissionsUIState.GrantedPermissions(location)
                    }
                    _rentACarUiState.value.permissionStatus = PermissionsStatus.GRANTED
                }
            }

            is RentACarUIEvent.PermissionsRevoked -> {
                _locationPermissionsUiState.value = LocationPermissionsUIState.RevokedPermissions
                _rentACarUiState.value.permissionStatus = PermissionsStatus.REVOKED
            }

            is RentACarUIEvent.ShowPermissionDialog -> {
                _rentACarUiState.value = _rentACarUiState.value.copy(
                    showRationaleDialog = event.show
                )
            }
        }
    }

    fun setMapData() {
        getVehicles()
    }

    private fun getVehicles() {
        viewModelScope.launch(dispatcher) {
            val result: Result<List<Vehicle>> = runCatching {
                vehicleRepository.getAllVehicles()
            }
            result.onSuccess { listOfVehicles ->
                // Get all available vehicles
                _rentACarUiState.value.listOfVehicles = listOfVehicles.filter { vehicle ->
                    vehicle.availability
                }

                // Get vehicle map items
                _rentACarUiState.value.vehicleMapItems = createVehicleMapItems()
            }.onFailure { e ->
                e.printStackTrace()
            }
        }
    }

    // Create vehicleMapItems for Google Maps composable
    private fun createVehicleMapItems(): SnapshotStateList<VehicleMapItem> {
        val mapItems = mutableStateListOf<VehicleMapItem>()
        _rentACarUiState.value.listOfVehicles.forEach { vehicle ->
            mapItems.add(
                VehicleMapItem(
                    vehicleId = vehicle.vehicleId,
                    LatLng(
                        vehicle.latitude.toDouble(),
                        vehicle.longitude.toDouble()
                    ),
                    vehicleSnippet = "${vehicle.year} - ${vehicle.brand} ${vehicle.model}",
                    vehicleTitle = vehicle.licensePlate,
                    vehicleZIndex = 0f
                )
            )
        }
        return mapItems
    }

    // Location service
    operator fun invoke(): Flow<LatLng?> = locationService.requestLocationUpdates()
}

data class VehicleMapItem(
    val vehicleId: String,
    val vehiclePosition: LatLng,
    val vehicleTitle: String,
    val vehicleSnippet: String,
    val vehicleZIndex: Float,
) : ClusterItem {
    fun getId(): String =
        vehicleId

    override fun getPosition(): LatLng =
        vehiclePosition

    override fun getTitle(): String =
        vehicleTitle

    override fun getSnippet(): String =
        vehicleSnippet

    override fun getZIndex(): Float =
        vehicleZIndex
}