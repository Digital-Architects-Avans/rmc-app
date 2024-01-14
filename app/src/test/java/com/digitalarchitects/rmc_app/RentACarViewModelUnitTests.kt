
import com.digitalarchitects.rmc_app.data.remote.ILocationService
import com.digitalarchitects.rmc_app.domain.model.EngineType
import com.digitalarchitects.rmc_app.domain.model.Vehicle
import com.digitalarchitects.rmc_app.domain.repo.RentalRepository
import com.digitalarchitects.rmc_app.domain.repo.UserPreferencesRepository
import com.digitalarchitects.rmc_app.domain.repo.UserRepository
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
import com.digitalarchitects.rmc_app.presentation.screens.rentacar.RentACarViewModel
import com.digitalarchitects.rmc_app.rules.TestDispatcherRule
import com.google.android.gms.maps.model.LatLng
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class RentACarViewModelTest {

    @Mock
    private lateinit var vehicleRepository: VehicleRepository

    @Mock
    private lateinit var rentalRepository: RentalRepository

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var locationService: ILocationService

    @Mock
    private lateinit var userPreferencesRepository: UserPreferencesRepository

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var rentACarViewModel: RentACarViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        rentACarViewModel = RentACarViewModel(
            vehicleRepository,
            rentalRepository,
            userRepository,
            locationService,
            userPreferencesRepository,
            testDispatcher.testDispatcher
        )
    }

    @Test
    fun testCalculateDistance() {
        val userLocation = LatLng(51.9225, 4.47917) // rotterdam
        val vehicleLocation = LatLng(51.5904, 4.9286) // breda

        val expectedDistance = 50.6

        val calculatedDistance = rentACarViewModel.calculateDistance(userLocation, vehicleLocation)

        val tolerance = 3.0
        assertEquals(expectedDistance, calculatedDistance, tolerance)
    }

    @Test
    fun setUserId_updatesUserIdInUiState() {
        rentACarViewModel._rentACarUiState.value.userId = "user123"

        rentACarViewModel.setUserId()
        assertEquals("user123", rentACarViewModel.rentACarUiState.value.userId)
    }

    @Test
    fun createVehicleMapItems_createsCorrectMapItems() {

        val vehicles = listOf(
            Vehicle(
                "1",
                "user1",
                "Brand1",
                "Model1",
                2022,
                "Class1",
                EngineType.ICE,
                "ABC123",
                0,
                "Description1",
                "Address1",
                1.0f,
                2.0f,
                50.0,
                true
            ),
        )
        rentACarViewModel._rentACarUiState.value.listOfVehicles = vehicles
        val mapItems = rentACarViewModel.createVehicleMapItems()

        assertEquals(vehicles.size, mapItems.size)
    }

}

