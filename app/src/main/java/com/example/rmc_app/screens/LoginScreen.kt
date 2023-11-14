package com.example.rmc_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rmc_app.R
import com.example.rmc_app.components.ButtonComponent
import com.example.rmc_app.components.ClickableLoginTextComponent
import com.example.rmc_app.components.DividerTextComponent
import com.example.rmc_app.components.HeadingTextComponent
import com.example.rmc_app.components.MyTextFieldComponent
import com.example.rmc_app.components.NormalTextComponent
import com.example.rmc_app.components.PasswordTextFieldComponent
import com.example.rmc_app.components.UnderLinedTextComponent
import com.example.rmc_app.navigation.RmcAppRouter
import com.example.rmc_app.navigation.Screen
import com.example.rmc_app.navigation.SystemBackButtonHandler

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            NormalTextComponent(value = stringResource(id = R.string.login))
            HeadingTextComponent(value = stringResource(id = R.string.welcome))
            Spacer(modifier = Modifier.height(20.dp))

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                icon = Icons.Default.Email,
                Modifier.fillMaxWidth()
            )
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                icon = Icons.Default.Lock,
                Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
            )

            Spacer(modifier = Modifier.height(40.dp))

            UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

            ButtonComponent(value = stringResource(id = R.string.login), onButtonClicked = { /*TODO*/ })

            Spacer(modifier = Modifier.height(20.dp))

            DividerTextComponent()

            ClickableLoginTextComponent(
                tryingToLogin = false,
                onTextSelected = {
                RmcAppRouter.navigateTo(Screen.RegisterScreen)
            })
        }

    }
    SystemBackButtonHandler {
        RmcAppRouter.navigateTo(Screen.RegisterScreen)
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}

