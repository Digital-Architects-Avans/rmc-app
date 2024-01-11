package com.digitalarchitects.rmc_app.presentation.screens.rentacar

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.data.di.IoDispatcher
import com.digitalarchitects.rmc_app.data.remote.ILocationService
import com.digitalarchitects.rmc_app.data.remote.dto.rental.CreateRentalDTO
import com.digitalarchitects.rmc_app.domain.model.EngineType
import com.digitalarchitects.rmc_app.domain.model.RentalStatus
import com.digitalarchitects.rmc_app.domain.model.Vehicle
import com.digitalarchitects.rmc_app.domain.repo.RentalRepository
import com.digitalarchitects.rmc_app.domain.repo.UserPreferencesRepository
import com.digitalarchitects.rmc_app.domain.repo.UserRepository
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.S)
@HiltViewModel
class RentACarViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository,
    private val rentalRepository: RentalRepository,
    private var userRepository: UserRepository,
    private val locationService: ILocationService,
    private val userPreferencesRepository: UserPreferencesRepository,
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

    // Set user ID
    init {
        setUserId()
    }

    fun onEvent(event: RentACarUIEvent) {
        when (event) {

            // Map controls
            is RentACarUIEvent.ZoomLevelChanged -> {
                _rentACarUiState.value = _rentACarUiState.value.copy(
                    zoomLevel = event.zoom
                )
            }

            is RentACarUIEvent.CameraPositionChanged -> {
                _rentACarUiState.value = _rentACarUiState.value.copy(
                    cameraPosition = event.position
                )
            }

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

            // Rental
            is RentACarUIEvent.DateChanged -> {
                _rentACarUiState.value = _rentACarUiState.value.copy(
                    date = event.date
                )
            }

            is RentACarUIEvent.ReserveVehicleButtonClicked -> {
                // Get Vehicle info
                val vehicle = _rentACarUiState.value.listOfVehicles.firstOrNull { it.vehicleId == _rentACarUiState.value.activeVehicleId }

                if (vehicle == null) {
                    Log.e("RegisterRentalViewModel", "Active vehicle not found")
                    return
                }

                // Create rental DTO
                val date = _rentACarUiState.value.date ?: run {
                    Log.e("RegisterRentalViewModel", "Date is null")
                    return
                }

                val newRentalDTO = CreateRentalDTO(
                    vehicleId = vehicle.vehicleId,
                    userId = _rentACarUiState.value.userId,
                    date = date,
                    price = vehicle.price,
                    latitude = vehicle.latitude,
                    longitude = vehicle.longitude,
                    status = RentalStatus.PENDING,
                    distanceTravelled = 0.0,
                    score = 0
                )

                viewModelScope.launch(dispatcher) {
                    try {
                        withContext(Dispatchers.IO) {
                            rentalRepository.addRental(createRentalDTO = newRentalDTO)
                        }
                        Log.d("RentACarViewModel","Created rental successfully")

                        // Reset uiState
                        _rentACarUiState.update {
                            it.copy(
                                activeVehicleId = null,
                                date = LocalDate(2024, 1, 1)
                            )
                        }

                    } catch (e: Exception) {
                        Log.e("RentACarViewModel", "Error creating rental")
                    }
                }
            }


            // Permissions
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

            // Show/hide permission dialog
            is RentACarUIEvent.ShowPermissionDialog -> {
                _rentACarUiState.value = _rentACarUiState.value.copy(
                    showRationaleDialog = event.show
                )
            }

            is RentACarUIEvent.FetchFilterPreference -> {
                viewModelScope.launch() {
                    try {
                        withContext(Dispatchers.IO) {
                            val filterPreferences = userPreferencesRepository.getFilterPreference()

                            _rentACarUiState.value = _rentACarUiState.value.copy(
                                datePreference = filterPreferences.date,
                                location = filterPreferences.location,
                                price = filterPreferences.price,
                                distance = filterPreferences.distance,
                                engineTypeIce = filterPreferences.engineTypeICE,
                                engineTypeBev = filterPreferences.engineTypeBEV,
                                engineTypeFcev = filterPreferences.engineTypeFCEV
                            )
                        }
                        Log.d("RentACarViewModel", "Fetched preferences successfully")

                    } catch (e: Exception) {
                        Log.d("RentACarViewModel", "Error fetching filter preference: $e")
                    }
                }
            }
        }
    }

    fun setMapData() {
        getVehicles()
    }

    private fun getVehicles() {
        viewModelScope.launch(dispatcher) {
            val filterPreferences = _rentACarUiState.value
            // Get all vehicles
            val result: Result<List<Vehicle>> = runCatching {
                vehicleRepository.getAllVehicles()
            }
            result.onSuccess { listOfVehicles ->
                // filter on availability and according to preferences
                val filteredVehicles = listOfVehicles.filter { vehicle ->
                    vehicle.availability &&
                    (filterPreferences.price <= 0 || vehicle.price <= filterPreferences.price)&&
//                        vehicle.date == filterPreferences.datePreference &&
//                        vehicle.location == filterPreferences.location &&
//                        vehicle.distance <= filterPreferences.distance &&
                    (filterPreferences.engineTypeIce && vehicle.engineType == EngineType.ICE ||
                    filterPreferences.engineTypeBev && vehicle.engineType == EngineType.BEV ||
                    filterPreferences.engineTypeFcev && vehicle.engineType == EngineType.FCEV)
                }
                _rentACarUiState.value.listOfVehicles = filteredVehicles

                // Get vehicle map items
                if (filteredVehicles.isNotEmpty()) {
                    _rentACarUiState.value.vehicleMapItems = createVehicleMapItems()
                }
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

    private fun setUserId() {
        viewModelScope.launch(dispatcher) {
            try {
                val userId = userRepository.getCurrentUserIdFromDataStore()
                withContext(Dispatchers.Main) {
                    _rentACarUiState.value.userId = userId!!
                }
            } catch (e: Exception) {
                Log.d("RentACarViewModel", "error: $e")
            }
        }
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