package com.example.rmc_app.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rmc_app.R
import com.example.rmc_app.ui.theme.RmcappTheme


@Preview(showBackground = true)
@Composable
fun RmcApp(modifier: Modifier = Modifier) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.rmc_logo_app),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(250.dp)
            )
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 34.sp
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    RmcappTheme {
        RmcApp()
//        VehicleList()
//        RegisterSection()
    }
}