package com.digitalarchitects.rmc_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.components.RmcFilledIconButton
import com.digitalarchitects.rmc_app.components.RmcFilledTonalIconButton
import com.digitalarchitects.rmc_app.components.RmcFloatingActionButton
import com.digitalarchitects.rmc_app.components.RmcImgFilledIconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentACarScreen(
    onSearchButtonClicked: () -> Unit,
    onRentMyCarButtonClicked: () -> Unit,
    onMyRentalsButtonClicked: () -> Unit,
    onMyAccountButtonClicked: () -> Unit,
) {
    BottomSheetScaffold(
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = dimensionResource(R.dimen.padding_large))
            ) {
                Text(text = "Vehicle info here")
            }
        },
        sheetPeekHeight = 58.dp,
    ) {
        Surface(
            modifier = Modifier
                .systemBarsPadding(),
//                .fillMaxSize(),
            color = Color.Gray
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            listOf(
                                MaterialTheme.colorScheme.secondaryContainer,
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.secondary
                            )
                        ),
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Map here",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 16.dp, bottom = 72.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                RmcFloatingActionButton(
                    icon = Icons.Filled.List,
                    label = R.string.view_list,
                    onClick = { /* TODO*/ }
                )
            }
            Row {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    RmcFilledIconButton(
                        icon = Icons.Filled.Search,
                        label = R.string.search,
                        onClick = onSearchButtonClicked,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                ) {
                    RmcFilledTonalIconButton(
                        icon = Icons.Filled.Key,
                        label = R.string.rent_out_my_car,
                        onClick = onRentMyCarButtonClicked,
                    )
                    RmcFilledTonalIconButton(
                        icon = Icons.Filled.CarRental,
                        label = R.string.my_rentals,
                        onClick = onMyRentalsButtonClicked,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    RmcImgFilledIconButton(
                        image = R.drawable.civic,
                        label = R.string.my_rentals,
                        onClick = onMyAccountButtonClicked,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RentACarScreenPreview() {
    RentACarScreen(
        onSearchButtonClicked = {},
        onRentMyCarButtonClicked = {},
        onMyRentalsButtonClicked = {},
        onMyAccountButtonClicked = {}
    )
}