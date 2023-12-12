package com.digitalarchitects.rmc_app

import com.digitalarchitects.rmc_app.data.UserUiState
import com.digitalarchitects.rmc_app.data.UserViewModel
import com.digitalarchitects.rmc_app.fake.FakeDataSource
import com.digitalarchitects.rmc_app.fake.FakeNetworkUserRepository
import com.digitalarchitects.rmc_app.rules.TestDispatcherRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Rule
import org.junit.Test

class UserViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun userViewModel_getUsers_verifyUserUiStateSuccess() =
        runTest {
            val userViewModel = UserViewModel(
                userRepository = FakeNetworkUserRepository()
            )

            // Get the list of users from the Rmc API Retrofit service
            val listResult = FakeDataSource.userList

            // Convert each User to its JSON representation with pretty print
            val formattedJsonList = listResult.map { user ->
                Json { prettyPrint = true }.encodeToString(user)
            }

            assertEquals(
                UserUiState.Success(
                    "Success: ${listResult.size} user retrieved",
                    formattedJsonList.joinToString("\n") // Combine the JSON strings with line breaks
                ),
                userViewModel.userUiState
            )
        }
}