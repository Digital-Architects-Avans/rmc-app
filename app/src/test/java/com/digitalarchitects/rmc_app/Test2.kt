package com.digitalarchitects.rmc_app

import com.digitalarchitects.rmc_app.data.auth.AuthResult
import com.digitalarchitects.rmc_app.data.auth.SignUpRequest
import com.digitalarchitects.rmc_app.data.local.RmcRoomDatabaseRepo
import com.digitalarchitects.rmc_app.data.remote.RmcApiService
import com.digitalarchitects.rmc_app.data.repo.UserRepositoryImpl
import com.digitalarchitects.rmc_app.domain.model.UserType
import com.digitalarchitects.rmc_app.domain.repo.UserPreferencesRepository
import com.digitalarchitects.rmc_app.rules.TestDispatcherRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.HttpException
import retrofit2.Response

class UserRepositoryImplTest {

    private lateinit var rmcRoomDatabase: RmcRoomDatabaseRepo
    private lateinit var userRepository: UserRepositoryImpl
    private lateinit var rmcApiService: RmcApiService
    private lateinit var userPreferencesRepository: UserPreferencesRepository
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun setup() {
        rmcApiService = mock(RmcApiService::class.java)
        rmcRoomDatabase = mock(RmcRoomDatabaseRepo::class.java)
        userPreferencesRepository = mock(UserPreferencesRepository::class.java)

        userRepository = UserRepositoryImpl(
            rmcRoomDatabase= rmcRoomDatabase,
            rmcApiService = rmcApiService,
            userPreferencesRepository = userPreferencesRepository,
            dispatcher = testDispatcher.testDispatcher
        )
    }

    @Test
    fun signUp_Success() = runTest {
        val signUpRequest = SignUpRequest(
            email = "test@example.com",
            password = "password",
            userType = UserType.CLIENT,
            firstName = "John",
            lastName = "Doe",
            phone = "123456789",
            street = "123 Main St",
            buildingNumber = "456",
            zipCode = "ABC123",
            city = "City"
        )

//        `when`(rmcApiService.signup(signUpRequest)).thenReturn(Response(Unit))
//
//        `when`(userRepository.signIn(signUpRequest.email, signUpRequest.password)).thenReturn(
//            AuthResult.Authorized())

        val result = userRepository.signUp(signUpRequest)

        assertEquals(AuthResult.Authorized(null), result)

    }
    @Test
    fun `signUp failure - HTTP 401 Unauthorized`() = runTest {
        val signUpRequest = SignUpRequest(
            email = "test@example.com",
            password = "password",
            userType = UserType.CLIENT,
            firstName = "John",
            lastName = "Doe",
            phone = "123456789",
            street = "123 Main St",
            buildingNumber = "456",
            zipCode = "ABC123",
            city = "City"
        )

        `when`(rmcApiService.signup(signUpRequest)).thenThrow(HttpException(Response.error<Unit>(401, okhttp3.ResponseBody.create("application/json".toMediaTypeOrNull(), ""))))

        val result = userRepository.signUp(signUpRequest)

//        assertEquals(AuthResult.Unauthorized(), result)
    }

}