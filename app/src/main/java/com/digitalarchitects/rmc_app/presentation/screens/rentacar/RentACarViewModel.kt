package com.digitalarchitects.rmc_app.presentation.screens.rentacar

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.data.di.IoDispatcher
import com.digitalarchitects.rmc_app.domain.model.Vehicle
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RentACarViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    // Rent A Car UI state
    private val _uiState = MutableStateFlow(RentACarUIState())
    val uiState: StateFlow<RentACarUIState> get() = _uiState.asStateFlow()

    init {
        getVehicles()
    }

    // Fix me!
    // ☐ Get search settings from DataStore
    // ✓ Get all vehicles
    // ☐ Filter all vehicles on search settings in getVehicles
    // ✓ Pass vehicleList to screen via UIState

    fun onEvent(event: RentACarUIEvent) {
        when (event) {
            is RentACarUIEvent.ShowListView -> {
                _uiState.value = _uiState.value.copy(
                    showVehicleList = event.show
                )
            }

            is RentACarUIEvent.RmcMapVehicleItemClicked -> {
                _uiState.value = _uiState.value.copy(
                    activeVehicleId = event.id,
                    showVehicleList = false,
                )
            }
        }
    }

    private fun getVehicles() {
        viewModelScope.launch(dispatcher) {
            val result: Result<List<Vehicle>> = runCatching {
                vehicleRepository.getAllVehicles()
            }
            result.onSuccess { listOfVehicles ->
                // Get all available vehicles
                _uiState.value.listOfVehicles = listOfVehicles.filter { vehicle ->
                    vehicle.availability
                }

                // Get vehicle map items
                _uiState.value.vehicleMapItems = createVehicleMapItems()
            }.onFailure { e ->
                e.printStackTrace()
            }
        }
    }

    // Create vehicleMapItems for Google Maps composable
    private fun createVehicleMapItems(): SnapshotStateList<VehicleMapItem> {
        val mapItems = mutableStateListOf<VehicleMapItem>()
        _uiState.value.listOfVehicles.forEach { vehicle ->
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