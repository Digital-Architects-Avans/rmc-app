package com.digitalarchitects.rmc_app

import com.digitalarchitects.rmc_app.data.UserUiState
import com.digitalarchitects.rmc_app.data.UserViewModel
import com.digitalarchitects.rmc_app.fake.FakeDataSource
import com.digitalarchitects.rmc_app.fake.FakeNetworkUserRepository
import com.digitalarchitects.rmc_app.rules.TestDispatcherRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
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
                assertEquals(
                    UserUiState.Success("Success: ${FakeDataSource.userList.size} user " +
                            "retrieved"),
                    userViewModel.userUiState
                )
            }
}