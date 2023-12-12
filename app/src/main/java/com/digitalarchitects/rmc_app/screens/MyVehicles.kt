package com.digitalarchitects.rmc_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.model.Vehicle

// BottomSheets and scaffolds, used resources below:
// https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#BottomSheetScaffold(kotlin.Function1,androidx.compose.ui.Modifier,androidx.compose.material.BottomSheetScaffoldState,kotlin.Function0,kotlin.Function1,kotlin.Function0,androidx.compose.material.FabPosition,kotlin.Boolean,androidx.compose.ui.graphics.Shape,androidx.compose.ui.unit.Dp,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.unit.Dp,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,kotlin.Function1)
// https://developer.android.com/jetpack/compose/components/bottom-sheets
// https://developer.android.com/jetpack/compose/components/scaffold
@Composable
fun VehicleList() {
    Column(modifier = Modifier.padding(vertical = 24.dp)) {
        Row(
            modifier = Modifier
                .height(40.dp)
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { /* TODO */ }
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.White, shape = CircleShape)
                        .border(1.dp, Color.LightGray, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = stringResource(R.string.close),
                        modifier = Modifier.fillMaxSize(),
                        tint = Color.Gray
                    )
                }
            }
            Text(
                text = stringResource(R.string.my_vehicles),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(start = 12.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .padding(24.dp)

        ) {

            val vehicle1 = Vehicle(
                id = 1,
                userId = 1,
                brand = "Toyota",
                model = "Yaris",
                year = 2011,
                vehicleClass = "Hatchback",
                engineType = "Gasoline",
                licensePlate = "YW - 790 - 2",
                imgLink = R.drawable.yaris,
                latitude = 2.0f,
                longitude = 2.0f,
                price = 95.0,
                availability = true
            )

            val vehicle2 = Vehicle(
                id = 2,
                userId = 2,
                brand = "Honda",
                model = "Civic",
                year = 2020,
                vehicleClass = "Sedan",
                engineType = "Gasoline",
                licensePlate = "ABC - 123",
                imgLink = R.drawable.civic,
                latitude = 2.0f,
                longitude = 2.0f,
                price = 120.0,
                availability = false
            )

            val vehicle3 = Vehicle(
                id = 3,
                userId = 3,
                brand = "Ford",
                model = "Focus",
                year = 2019,
                vehicleClass = "Hatchback",
                engineType = "Gasoline",
                licensePlate = "XYZ - 456",
                imgLink = R.drawable.focus,
                latitude = 2.0f,
                longitude = 2.0f,
                price = 80.0,
                availability = true
            )

            val vehicle4 = Vehicle(
                id = 4,
                userId = 4,
                brand = "Chevrolet",
                model = "Malibu",
                year = 2022,
                vehicleClass = "Sedan",
                engineType = "Gasoline",
                licensePlate = "DEF - 789",
                imgLink = R.drawable.malibu,
                latitude = 2.0f,
                longitude = 2.0f,
                price = 110.0,
                availability = true
            )


            val list = listOf(vehicle1, vehicle2, vehicle3, vehicle4)
            list.forEachIndexed { index, vehicle ->
                VehicleListItem(vehicle)
                if (index < list.size - 1) {
                    Divider(modifier = Modifier.padding(8.dp))
                }
            }
        }
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

@Composable
fun VehicleListItem(vehicle: Vehicle) {

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