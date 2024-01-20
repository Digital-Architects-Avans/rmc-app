package com.digitalarchitects.rmc_app


import android.app.Application
import android.net.Uri
import com.digitalarchitects.rmc_app.fake.FakeFileRepository
import com.digitalarchitects.rmc_app.fake.FakeUserRepository
import com.digitalarchitects.rmc_app.presentation.screens.editmyaccount.EditMyAccountUIEvent
import com.digitalarchitects.rmc_app.presentation.screens.editmyaccount.EditMyAccountViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test


class EditMyAccountScreenUiTest {

    private val viewModel = EditMyAccountViewModel(
        application = Application(),
        userRepository = FakeUserRepository(),
        fileRepository = FakeFileRepository()
    )

    private fun createMockUri(): Uri {
        return Uri.parse("content://mock")
    }


    @Test
    fun test_editAccount_success() = runTest {
        val firstName = "John"
        val lastName = "Doe"
        val email = "test@example.com"
        val password = "password123"
        val phone = "06"
        val street = "Bredalaan"
        val postalCode = "3200kk"
        val buildingNumber = "120"
        val city = "Breda"
        val profileImageSrc = "src"
        val imageUri = createMockUri()

        viewModel.onEvent(EditMyAccountUIEvent.SetFirstName(firstName))
        viewModel.onEvent(EditMyAccountUIEvent.SetLastName(lastName))
        viewModel.onEvent(EditMyAccountUIEvent.SetEmail(email))
        viewModel.onEvent(EditMyAccountUIEvent.SetPhone(phone))
        viewModel.onEvent(EditMyAccountUIEvent.SetPassword(password))
        viewModel.onEvent(EditMyAccountUIEvent.SetStreet(street))
        viewModel.onEvent(EditMyAccountUIEvent.SetBuildingNumber(buildingNumber))
        viewModel.onEvent(EditMyAccountUIEvent.SetCity(city))
        viewModel.onEvent(EditMyAccountUIEvent.SetZipCode(postalCode))
        viewModel.onEvent(EditMyAccountUIEvent.SetImageUri(imageUri))


        assertEquals(firstName, viewModel.uiState.value.firstName)
        assertEquals(lastName, viewModel.uiState.value.lastName)
        assertEquals(email, viewModel.uiState.value.email)
        assertEquals(phone, viewModel.uiState.value.phone)
        assertEquals(password, viewModel.uiState.value.password)
        assertEquals(street, viewModel.uiState.value.street)
        assertEquals(postalCode, viewModel.uiState.value.zipCode)
        assertEquals(buildingNumber, viewModel.uiState.value.buildingNumber)
        assertEquals(city, viewModel.uiState.value.city)
        assertEquals(profileImageSrc, viewModel.uiState.value.profileImageSrc)
        assertEquals(imageUri, viewModel.uiState.value.imageUri)
    }


    @Test
    fun test_editAccount_false() = runTest {
        val firstName = "John"
        val lastName = "Doe"
        val email = "test@example.com"
        val password = "password123"
        val phone = "06"
        val street = "Bredalaan"
        val postalCode = "3200kk"
        val buildingNumber = "120"
        val city = "Breda"
        val profileImageSrc = "src"
        val imageUri = createMockUri()

        viewModel.onEvent(EditMyAccountUIEvent.SetFirstName(firstName))
        viewModel.onEvent(EditMyAccountUIEvent.SetLastName(lastName))
        viewModel.onEvent(EditMyAccountUIEvent.SetEmail(email))
        viewModel.onEvent(EditMyAccountUIEvent.SetPhone(phone))
        viewModel.onEvent(EditMyAccountUIEvent.SetPassword(password))
        viewModel.onEvent(EditMyAccountUIEvent.SetStreet(street))
        viewModel.onEvent(EditMyAccountUIEvent.SetBuildingNumber(buildingNumber))
        viewModel.onEvent(EditMyAccountUIEvent.SetCity(city))
        viewModel.onEvent(EditMyAccountUIEvent.SetZipCode(postalCode))
        viewModel.onEvent(EditMyAccountUIEvent.SetImageUri(imageUri))


        assertEquals(firstName, viewModel.uiState.value.firstName)
        assertEquals(lastName, viewModel.uiState.value.lastName)
        assertEquals(email, viewModel.uiState.value.email)
        assertEquals(phone, viewModel.uiState.value.phone)
        assertEquals(password, viewModel.uiState.value.password)
        assertEquals(street, viewModel.uiState.value.street)
        assertEquals(postalCode, viewModel.uiState.value.zipCode)
        assertEquals(buildingNumber, viewModel.uiState.value.buildingNumber)
        assertEquals(city, viewModel.uiState.value.city)
        assertEquals(profileImageSrc, viewModel.uiState.value.profileImageSrc)
        assertEquals(imageUri, viewModel.uiState.value.imageUri)
    }
}
