package com.example.rmc_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rmc_app.R
import com.example.rmc_app.components.NormalTextComponent
import com.example.rmc_app.components.SmallHeadingTextComponent

@Composable
fun TermsAndConditionsScreen(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column {
            NormalTextComponent(stringResource(id = R.string.tac_1))
            Spacer(modifier = Modifier.height(20.dp))
            SmallHeadingTextComponent(
                value = stringResource(id = R.string.tac_acceptance_of_terms_header)
            )
            NormalTextComponent(
                value = stringResource(id = R.string.tac_acceptance_of_terms_content)
            )
            Spacer(modifier = Modifier.height(20.dp))
            SmallHeadingTextComponent(
                value = stringResource(id = R.string.tac_use_header)
            )
            NormalTextComponent(
                value = stringResource(id = R.string.tac_use_content)
            )
            Spacer(modifier = Modifier.height(20.dp))
            SmallHeadingTextComponent(
                value = stringResource(id = R.string.tac_user_account_header)
            )
            NormalTextComponent(
                value = stringResource(id = R.string.tac_user_account_content)
            )
            Spacer(modifier = Modifier.height(20.dp))
            SmallHeadingTextComponent(
                value = stringResource(id = R.string.tac_privacy_header)
            )
            NormalTextComponent(
                value = stringResource(id = R.string.tac_privacy_content)
            )
            Spacer(modifier = Modifier.height(20.dp))
            SmallHeadingTextComponent(
                value = stringResource(id = R.string.tac_termination_header)
            )
            NormalTextComponent(
                value = stringResource(id = R.string.tac_termination_content)
            )
            Spacer(modifier = Modifier.height(20.dp))
            SmallHeadingTextComponent(
                value = stringResource(id = R.string.tac_changes_header)
            )
            NormalTextComponent(
                value = stringResource(id = R.string.tac_changes_content)
            )
            Spacer(modifier = Modifier.height(20.dp))
            SmallHeadingTextComponent(
                value = stringResource(id = R.string.tac_contact_header)
            )
            NormalTextComponent(
                value = stringResource(id = R.string.tac_contact_content)
            )
        }
    }
}

@Preview
@Composable
fun TermsAndConditionsScreenPreview() {
    TermsAndConditionsScreen(navController = rememberNavController())
}