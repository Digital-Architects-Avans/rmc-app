package com.digitalarchitects.rmc_app.data.rentacar

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.app.RmcScreen
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
import com.digitalarchitects.rmc_app.dummyDTO.DummyVehicleDTO
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RentACarViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {
    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()

    // Rent A Car UI state
    private val _state = MutableStateFlow(RentACarUIState())
    private val _uiState = _state
    val uiState: StateFlow<RentACarUIState> get() = _uiState.asStateFlow()

    // Get search settings
    // Get all vehicles
    // Filter all vehicles on search settings
    // pass vehicleList to screen
    val listOfVehicles = DummyVehicleDTO()

    fun onEvent(event: RentACarUIEvent) {
        when (event) {
            is RentACarUIEvent.MyAccountButtonClicked -> {
                _navigateToScreen.value = RmcScreen.MyAccount
            }
            is RentACarUIEvent.MyRentalsButtonClicked -> {
                _navigateToScreen.value = RmcScreen.MyRentals
            }
            is RentACarUIEvent.RentOutMyVehicleButtonClicked -> {
                _navigateToScreen.value = RmcScreen.RentOutMyCar
            }
            is RentACarUIEvent.SearchButtonClicked -> {
                _navigateToScreen.value = RmcScreen.Search
            }
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