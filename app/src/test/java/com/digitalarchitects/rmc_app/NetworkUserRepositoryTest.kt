package com.digitalarchitects.rmc_app

import com.digitalarchitects.rmc_app.data.NetworkUserRepository
import com.digitalarchitects.rmc_app.fake.FakeDataSource
import com.digitalarchitects.rmc_app.fake.FakeRmcApiService
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

// Test the NetworkUserRepository class in a coroutine environment
class NetworkUserRepositoryTest {
    @Test
    fun networkUserRepository_getUsers_verifyUserList() = runTest {

        /* By passing the fake API service, any calls to the rmcApiService property in the repository
     * result in a call to the FakeRmcApiService. By passing fake classes for dependencies, you can
     * control exactly what the dependency returns. This approach ensures that the code you are
     * testing doesn't depend on untested code or APIs that could change or have unforeseen problems
     */
        val repository = NetworkUserRepository(
            rmcApiService = FakeRmcApiService()
        )

        assertEquals(FakeDataSource.userList, repository.getUsers())
    }
}