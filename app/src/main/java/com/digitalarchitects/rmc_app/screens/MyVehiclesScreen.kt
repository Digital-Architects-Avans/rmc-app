package com.digitalarchitects.rmc_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.components.RmcAppBar
import com.digitalarchitects.rmc_app.data.myvehicles.MyVehiclesUIEvent
import com.digitalarchitects.rmc_app.data.myvehicles.MyVehiclesViewModel
import com.digitalarchitects.rmc_app.room.VehicleTable

// BottomSheets and scaffolds, used resources below:
// https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#BottomSheetScaffold(kotlin.Function1,androidx.compose.ui.Modifier,androidx.compose.material.BottomSheetScaffoldState,kotlin.Function0,kotlin.Function1,kotlin.Function0,androidx.compose.material.FabPosition,kotlin.Boolean,androidx.compose.ui.graphics.Shape,androidx.compose.ui.unit.Dp,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.unit.Dp,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,kotlin.Function1)
// https://developer.android.com/jetpack/compose/components/bottom-sheets
// https://developer.android.com/jetpack/compose/components/scaffold
@Composable
fun MyVehiclesScreen(
    viewModel: MyVehiclesViewModel,
    navigateToScreen: (String) -> Unit
) {
    val navigateToScreenEvent by viewModel.navigateToScreen.collectAsState()
    if (navigateToScreenEvent != null) {
        navigateToScreen(navigateToScreenEvent!!.name)
    }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
//        viewModel.onEvent(MyVehiclesUIEvent.ShowVehicles)
    }
//    val vehicleList by viewModel.vehicleList.observeAsState<List<Vehicle>>()

    Scaffold(
        topBar = {
            RmcAppBar(
                title = R.string.screen_title_my_vehicles,
                navigationIcon = Icons.Rounded.ArrowBack,
                navigateUp = {
                    viewModel.onEvent(MyVehiclesUIEvent.NavigateUpButtonClicked)
                },
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {

            Column(modifier = Modifier.padding(vertical = 24.dp)) {

                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .padding(24.dp)

                ) {
//                    LazyColumn(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(24.dp)
//                    ) {
//                        items(vehicleList ?: emptyList()) {vehicle ->
//                            VehicleListItem(vehicle)
//                        }
//                    }
//                }
                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxSize()
                    ) {
                        Button(
                            modifier = Modifier.align(Alignment.End),
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = stringResource(R.string.add_vehicle)
                            )
                            Text(stringResource(R.string.add_vehicle))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun VehicleListItem(vehicle: VehicleTable) {

    Row(modifier = Modifier.height(92.dp)) {

        Image(
            painter = painterResource(id = vehicle.imgLink),
            contentDescription = stringResource(R.string.vehicle),
            modifier = Modifier
                .padding(end = 6.dp)
                .height(92.dp)
                .width(92.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(start = 6.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = vehicle.licensePlate,
                    color = Color.Red,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
                Box(
                    modifier = Modifier
                        .height(24.dp)
                        .background(
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        text = stringResource(R.string.available),
                        color = Color.White,
                    )
                }
            }
            Text(
                text = vehicle.model, fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Default.Place,
                        contentDescription = stringResource(R.string.location)
                    )
                    Text(text = vehicle.latitude.toString())
                }
                Row {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = stringResource(R.string.price)
                    )
                    Text(
                        text = vehicle.price.toString(),
                        modifier = Modifier
                            .padding(end = 24.dp)
                            .width(54.dp)
                    )
                }

            }
        }
    }
}


