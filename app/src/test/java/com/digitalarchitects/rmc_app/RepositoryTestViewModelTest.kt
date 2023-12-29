package com.digitalarchitects.rmc_app

import FakeLocalDataSource
import com.digitalarchitects.rmc_app.data.mapper.toUserListFromLocal
import com.digitalarchitects.rmc_app.data.repositorytest.RepositoryTestViewModel
import com.digitalarchitects.rmc_app.data.repositorytest.ResultUiState
import com.digitalarchitects.rmc_app.fake.FakeRentalRepository
import com.digitalarchitects.rmc_app.fake.FakeUserRepository
import com.digitalarchitects.rmc_app.fake.FakeVehicleRepository
import com.digitalarchitects.rmc_app.rules.TestDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RepositoryTestViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val viewModel = RepositoryTestViewModel(
        userRepository = FakeUserRepository(),
        vehicleRepository = FakeVehicleRepository(),
        rentalRepository = FakeRentalRepository(),
        dispatcher = testDispatcher.testDispatcher
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `viewModel getUsers verify ResultUiState Success`() = runTest {
        // Trigger the action to get users
        viewModel.getUsers()

        // Advance the clock by 2 seconds
        advanceTimeBy(2000) // advance time by 2 seconds

        // Ensure the result matches the expected Success state
        val expectedListResult = FakeLocalDataSource.userList.toUserListFromLocal()

        val expectedState = ResultUiState.Success(
            formatSuccessMessage(expectedListResult),
            expectedListResult
        )
        Assertions.assertEquals(expectedState, viewModel.resultUiState)
    }

    private fun <T> formatSuccessMessage(listResult: List<T>): String {
        val listCount = listResult.size
        val typeName = if (listCount > 0) listResult[0]!!::class.simpleName else "Item"
        return "Success: $listCount ${if (listCount == 1) typeName else typeName + "s"} retrieved"
    }
}