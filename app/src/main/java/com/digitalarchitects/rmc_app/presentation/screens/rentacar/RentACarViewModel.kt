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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.toLocalDate
import java.time.LocalDate
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
                // Disable form
                setReserveVehicleUiState(true)

                // Get Vehicle info
                val vehicle = _rentACarUiState.value.listOfVehicles.first { vehicle ->
                    vehicle.vehicleId == _rentACarUiState.value.activeVehicleId
                }

                // Create rental DTO
                val vehicleId = vehicle.vehicleId
                val userId = _rentACarUiState.value.userId
                val date = _rentACarUiState.value.date.toLocalDate()
                val price = vehicle.price
                val latitude = vehicle.latitude
                val longitude = vehicle.longitude
                val status = RentalStatus.PENDING
                val distanceTravelled = 0.0
                val score = 0

                val newRentalDTO = CreateRentalDTO(
                    vehicleId = vehicleId,
                    userId = userId,
                    date = date,
                    price = price,
                    latitude = latitude,
                    longitude = longitude,
                    status = status,
                    distanceTravelled = distanceTravelled,
                    score = score
                )

                viewModelScope.launch {
                    try {
                        withContext(Dispatchers.IO) {
                            rentalRepository.addRental(createRentalDTO = newRentalDTO)

                            withContext(Dispatchers.Main) {
                                setReserveVehicleUiState(false)
                            }
                        }
                        Log.d("RegisterRentalViewModel", "Created reservation successfully")

                    } catch (e: Exception) {
                        // Re-enable reserve button
                        Log.d("RegisterRentalViewModel", "Error creating reservation: $e")
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

            is RentACarUIEvent.FetchFilterPreference ->{
                viewModelScope.launch() {
                    try{
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
                        Log.d("RentACar", "Fetched preferences successfully")

                    } catch (e: Exception){
                        Log.d("RentACar", "Error fetching filter preference: $e")
                    }
                }
            }
        }
    }

    fun setMapData() {
        getVehicles()
    }

    private fun getVehicles() {
        try {
            viewModelScope.launch(dispatcher) {
                val filterPreferences = _rentACarUiState.value
                // Get all vehicles
                val result: Result<List<Vehicle>> = runCatching {
                    vehicleRepository.getAllVehicles()
                }
                result.onSuccess { listOfVehicles ->
                    // filter on availability and according to preferences
                    val filteredVehicles = listOfVehicles.filter { vehicle ->
                        vehicle.availability
                                &&
                        vehicle.price <= filterPreferences.price

    //                    vehicle.date == filterPreferences.datePreference &&
    //                    vehicle.location == filterPreferences.location &&
    //                    vehicle.distance <= filterPreferences.distance &&
    //                    checkEngineType(vehicle, filterPreferences)

                    }
                    _rentACarUiState.value.listOfVehicles = filteredVehicles

                    // Get vehicle map items
                    _rentACarUiState.value.vehicleMapItems = createVehicleMapItems()
                }.onFailure { e ->
                    e.printStackTrace()
                }
            }
        }catch (e:Exception){

        }
    }

    // helper function to check enginetype
    private fun checkEngineType(vehicle: Vehicle, filterPreferences: RentACarUIState): Boolean {
        return (filterPreferences.engineTypeIce && vehicle.engineType == EngineType.ICE) ||
                (filterPreferences.engineTypeBev && vehicle.engineType == EngineType.BEV) ||
                (filterPreferences.engineTypeFcev && vehicle.engineType == EngineType.FCEV)
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
                Log.d("MyAccountViewModel", "error: $e")
            }
        }
    }

    private fun setReserveVehicleUiState(state: Boolean) {
        _rentACarUiState.value = _rentACarUiState.value.copy(
            placingReservation = state
        )
        if (!state) {
            _rentACarUiState.value = _rentACarUiState.value.copy(
                date = LocalDate.now().plusDays(1).toString()
            )
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