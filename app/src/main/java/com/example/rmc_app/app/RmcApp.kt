package com.example.rmc_app.app

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.rmc_app.R
import com.example.rmc_app.components.LargeHeadingTextComponent
import com.example.rmc_app.data.login.LoginViewModel
import com.example.rmc_app.screens.LoginScreen
import com.example.rmc_app.screens.RegisterScreen
import com.example.rmc_app.screens.TermsAndConditionsScreen
import com.example.rmc_app.ui.theme.RmcAppTheme

enum class RmcScreen(@StringRes val title: Int) {
    Login(title = R.string.login),
    Register(title = R.string.register),
    TermsAndConditions(title = R.string.terms_and_conditions_header)
}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RmcAppBar(
    @StringRes currentScreenTitle: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { LargeHeadingTextComponent(stringResource(currentScreenTitle)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun RmcApp(
    viewModel: LoginViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = RmcScreen.valueOf(
        backStackEntry?.destination?.route ?: RmcScreen.Login.name
    )

    Scaffold(
        topBar = {
            RmcAppBar(
                currentScreenTitle = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = RmcScreen.Login.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = RmcScreen.Login.name) {
                LoginScreen(navController = navController)
            }
            composable(route = RmcScreen.Register.name) {
                RegisterScreen(navController = navController)
            }
            composable(route = RmcScreen.TermsAndConditions.name) {
                TermsAndConditionsScreen(navController = navController)
            }
        }
    }
}


@Preview
@Composable
fun Preview() {
    RmcAppTheme {
        RmcApp()
    }
}