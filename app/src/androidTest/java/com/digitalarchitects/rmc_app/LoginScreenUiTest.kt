package com.digitalarchitects.rmc_app


import com.digitalarchitects.rmc_app.data.auth.AuthResult
import com.digitalarchitects.rmc_app.fake.FakeUserRepository
import com.digitalarchitects.rmc_app.presentation.screens.login.LoginUIEvent
import com.digitalarchitects.rmc_app.presentation.screens.login.LoginViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LoginScreenUiTest {

    private lateinit var userRepository: FakeUserRepository
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        userRepository = FakeUserRepository()
        viewModel = LoginViewModel(userRepository)
    }

    @Test
    fun test_signIn_success() = runTest {
        // Given
        val email = "test@example.com"
        val password = "password123"
        val authResult = AuthResult.Authorized(Unit)

        // Set up the mock result for signIn
        userRepository.setSignInResult(email, password, authResult)

        // When
        viewModel.onEvent(LoginUIEvent.EmailChanged(email))
        viewModel.onEvent(LoginUIEvent.PasswordChanged(password))
        viewModel.onEvent(LoginUIEvent.LoginButtonClicked)

        // Then
        assertEquals(authResult, viewModel.authResult.first())
    }

    @Test
    fun test_signIn_failure() = runTest {
        // Given
        val email = "test@example.com"
        val password = "password123"
        val authResult = AuthResult.Unauthorized<Unit>()

        // Set up the mock result for signIn
        userRepository.setSignInResult(email, password, authResult)

        // When
        viewModel.onEvent(LoginUIEvent.EmailChanged(email))
        viewModel.onEvent(LoginUIEvent.PasswordChanged(password))
        viewModel.onEvent(LoginUIEvent.LoginButtonClicked)

        // Then
        assertEquals(authResult, viewModel.authResult.first())
    }
}
