package com.digitalarchitects.rmc_app.presentation.screens.termsandconditions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import com.digitalarchitects.rmc_app.presentation.components.RmcAppBar
import com.digitalarchitects.rmc_app.presentation.components.RmcSpacer

@Composable
fun TermsAndConditionsScreen(
    navigateToScreen: (String) -> Unit
) {
    Scaffold(
        topBar = {
            RmcAppBar(
                title = R.string.screen_title_terms,
                navigationIcon = Icons.Filled.Close,
                navigateUp = { navigateToScreen(RmcScreen.Register.name) }
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
                    .padding(horizontal = dimensionResource(R.dimen.padding_large))
                    .verticalScroll(rememberScrollState())
            ) {
                Column {
                    Text(
                        text = stringResource(id = R.string.tac_1),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    RmcSpacer()
                    Text(
                        text = stringResource(id = R.string.tac_acceptance_of_terms_header),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    RmcSpacer(8)
                    Text(
                        text = stringResource(id = R.string.tac_acceptance_of_terms_content),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    RmcSpacer()
                    Text(
                        text = stringResource(id = R.string.tac_use_header),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    RmcSpacer(8)
                    Text(
                        text = stringResource(id = R.string.tac_use_content),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    RmcSpacer()
                    Text(
                        text = stringResource(id = R.string.tac_user_account_header),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    RmcSpacer(8)
                    Text(
                        text = stringResource(id = R.string.tac_user_account_content),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    RmcSpacer()
                    Text(
                        text = stringResource(id = R.string.tac_privacy_header),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    RmcSpacer(8)
                    Text(
                        text = stringResource(id = R.string.tac_privacy_content),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    RmcSpacer()
                    Text(
                        text = stringResource(id = R.string.tac_termination_header),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    RmcSpacer(8)
                    Text(
                        text = stringResource(id = R.string.tac_termination_content),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    RmcSpacer()
                    Text(
                        text = stringResource(id = R.string.tac_changes_header),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    RmcSpacer(8)
                    Text(
                        text = stringResource(id = R.string.tac_changes_content),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    RmcSpacer()
                    Text(
                        text = stringResource(id = R.string.tac_contact_header),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    RmcSpacer(8)
                    Text(
                        text = stringResource(id = R.string.tac_contact_content),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TermsAndConditionsScreenPreview() {
    TermsAndConditionsScreen(
        navigateToScreen = { }
    )
}