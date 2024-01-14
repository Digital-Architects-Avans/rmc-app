package com.digitalarchitects.rmc_app

import com.digitalarchitects.rmc_app.fake.FakeRentalRepository
import com.digitalarchitects.rmc_app.fake.FakeUserRepository
import com.digitalarchitects.rmc_app.fake.FakeVehicleRepository
import com.digitalarchitects.rmc_app.presentation.screens.register.RegisterUIEvent
import com.digitalarchitects.rmc_app.presentation.screens.register.RegisterViewModel
import com.digitalarchitects.rmc_app.rules.TestDispatcherRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RegisterScreenUiTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var userRepository: FakeUserRepository
    private lateinit var vehicleRepository: FakeVehicleRepository
    private lateinit var rentalRepository: FakeRentalRepository
    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setUp() {
        userRepository = FakeUserRepository()
        vehicleRepository = FakeVehicleRepository()
        rentalRepository = FakeRentalRepository()
        viewModel = RegisterViewModel(
            dispatcher = testDispatcher.testDispatcher,
            userRepository = userRepository,
            vehicleRepository = vehicleRepository,
            rentalRepository = rentalRepository
        )
    }

    @Test
    fun test_register_success() = runTest {
        val firstName = "John"
        val lastName = "Doe"
        val email = "test@example.com"
        val password = "password123"
        val phone = "06"
        val address = "Bredalaan"
        val postalCode = "3200kk"
        val buildingNumber = "120"
        val city = "Breda"
        val privacyPolicyCheckBoxClicked = true

        viewModel.onEvent(RegisterUIEvent.FirstNameChanged(firstName))
        viewModel.onEvent(RegisterUIEvent.LastNameChanged(lastName))
        viewModel.onEvent(RegisterUIEvent.EmailChanged(email))
        viewModel.onEvent(RegisterUIEvent.TelephoneChanged(phone))
        viewModel.onEvent(RegisterUIEvent.PasswordChanged(password))
        viewModel.onEvent(RegisterUIEvent.AddressChanged(address))
        viewModel.onEvent(RegisterUIEvent.PostalCodeChanged(postalCode))
        viewModel.onEvent(RegisterUIEvent.BuildingNumberChanged(buildingNumber))
        viewModel.onEvent(RegisterUIEvent.CityChanged(city))
        viewModel.onEvent(RegisterUIEvent.PrivacyPolicyCheckBoxClicked(privacyPolicyCheckBoxClicked))

        assertEquals(firstName, viewModel.uiState.value.firstName)
        assertEquals(lastName, viewModel.uiState.value.lastName)
        assertEquals(email, viewModel.uiState.value.email)
        assertEquals(phone, viewModel.uiState.value.telephone)
        assertEquals(password, viewModel.uiState.value.password)
        assertEquals(address, viewModel.uiState.value.address)
        assertEquals(postalCode, viewModel.uiState.value.postalCode)
        assertEquals(buildingNumber, viewModel.uiState.value.buildingNumber)
        assertEquals(city, viewModel.uiState.value.city)
        assertTrue(viewModel.uiState.value.privacyPolicyAccepted)
    }
}
