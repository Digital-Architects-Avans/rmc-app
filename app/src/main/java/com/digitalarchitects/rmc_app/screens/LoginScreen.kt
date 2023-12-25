package com.digitalarchitects.rmc_app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.components.ClickableLoginTextComponent
import com.digitalarchitects.rmc_app.components.DividerTextComponent
import com.digitalarchitects.rmc_app.components.RmcAppBar
import com.digitalarchitects.rmc_app.components.RmcFilledButton
import com.digitalarchitects.rmc_app.components.RmcSpacer
import com.digitalarchitects.rmc_app.components.RmcTextField
import com.digitalarchitects.rmc_app.components.UnderLinedTextComponent
import com.digitalarchitects.rmc_app.data.editmyaccount.EditMyAccountUIEvent
import com.digitalarchitects.rmc_app.data.editmyaccount.EditMyAccountViewModel
import com.digitalarchitects.rmc_app.data.login.LoginUIEvent
import com.digitalarchitects.rmc_app.data.login.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navigateToScreen: (String) -> Unit
) {
    val navigateToScreenEvent by viewModel.navigateToScreen.collectAsState()
    if (navigateToScreenEvent != null) {
        navigateToScreen(navigateToScreenEvent!!.name)
    }
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
// TODO Default state
    }

    Scaffold(
        topBar = {
            RmcAppBar(
                title = R.string.screen_title_login,
                navigationIcon = Icons.Rounded.ArrowBack,
                navigateUp = { viewModel.onEvent(LoginUIEvent.NavigateUpButtonClicked) }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.padding_large))
                    .verticalScroll(rememberScrollState())
            ) {
                RmcTextField(
                    label = stringResource(id = R.string.email),
                    icon = Icons.Filled.Email,
                    value = "loginUiState.email",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        // TODO loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    }
                )

                RmcSpacer()

                RmcTextField(
                    label = stringResource(id = R.string.password),
                    icon = Icons.Filled.Lock,
                    value = "loginUiState.password",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    isPassword = true,
                    onValueChange = {
//                        TODO loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    }
                )

                RmcSpacer()

                UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

                RmcFilledButton(
                    value = stringResource(id = R.string.login),
                    onClick = { viewModel.onEvent(LoginUIEvent.LoginButtonClicked) }
                )

                DividerTextComponent()

                ClickableLoginTextComponent(
                    tryingToLogin = false,
                    onTextSelected = { viewModel.onEvent(LoginUIEvent.RegisterButtonClicked) }
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        viewModel = viewModel(),
        navigateToScreen = { }
    )
}

