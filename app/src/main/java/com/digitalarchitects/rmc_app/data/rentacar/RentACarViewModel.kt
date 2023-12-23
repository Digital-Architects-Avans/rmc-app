package com.digitalarchitects.rmc_app.data.rentacar

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.dummyDTO.DummyVehicleDTO
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RentACarViewModel : ViewModel() {

    // Rent A Car UI state
    private val _uiState = MutableStateFlow(RentACarUIState())
    val uiState: StateFlow<RentACarUIState> = _uiState.asStateFlow()

    // Get search settings
    // Get all vehicles
    // Filter all vehicles on search settings
    // pass vehicleList to screen
    val listOfVehicles = DummyVehicleDTO()

    fun onEvent(event: RentACarUIEvent) {
        when (event) {
            is RentACarUIEvent.ShowListView -> {
                _uiState.value = _uiState.value.copy(
                    showVehicleList = event.show
                )
            }

            is RentACarUIEvent.RmcMapVehicleItemClicked -> {
                _uiState.value = _uiState.value.copy(
                    detailsVehicleId = event.id - 1,
                    showVehicleList = false,
                )
            }
        }
    }

    fun getVehicleMapItems(): SnapshotStateList<VehicleMapItem> {
        val items = mutableStateListOf<VehicleMapItem>()
        listOfVehicles.forEach { vehicle ->
            items.add(
                VehicleMapItem(
                    vehicleId = vehicle.id,
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
        return items
    }
}

data class VehicleMapItem(
    val vehicleId: Int,
    val vehiclePosition: LatLng,
    val vehicleTitle: String,
    val vehicleSnippet: String,
    val vehicleZIndex: Float,
) : ClusterItem {
    fun getId(): Int =
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