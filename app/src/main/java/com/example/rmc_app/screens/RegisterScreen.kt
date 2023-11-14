package com.example.rmc_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rmc_app.R
import com.example.rmc_app.model.User
import com.example.rmc_app.model.Vehicle


// OutlinedTextField, used resources below:
// https://www.jetpackcompose.net/textfield-in-jetpack-compose
// https://tinyurl.com/OutlinedTextField
// NOTE: Use: implementation("androidx.compose.material3:material3:1.1.2") || Do not use the opt in experimental class
@Composable
fun RegisterSection() {
    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var lastName by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var telephone by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var address by remember { mutableStateOf(TextFieldValue("")) }
    var postalCode by remember { mutableStateOf(TextFieldValue("")) }
    var buildingNumber by remember { mutableStateOf(TextFieldValue("")) }
    var city by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.padding(top = 24.dp)) {
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
                text = stringResource(R.string.register),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(start = 12.dp)
            )
        }

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(24.dp)
                .fillMaxHeight()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = firstName,
                    label = { Text(text = stringResource(R.string.first_name)) },
                    onValueChange = {
                        firstName = it
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 6.dp)
                )
                OutlinedTextField(
                    value = lastName,
                    label = { Text(text = stringResource(R.string.last_name)) },
                    onValueChange = {
                        lastName = it
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 6.dp)
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = email,
                label = { Text(text = stringResource(R.string.email)) },
                onValueChange = {
                    email = it
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = telephone,
                label = { Text(text = stringResource(R.string.telephone)) },
                onValueChange = {
                    telephone = it
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = password,
                visualTransformation = PasswordVisualTransformation(),
                label = { Text(text = stringResource(R.string.password)) },
                onValueChange = {
                    password = it
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = address,
                label = { Text(text = stringResource(R.string.address)) },
                onValueChange = {
                    address = it
                }
            )
            Row {
                OutlinedTextField(
                    value = postalCode,
                    label = { Text(text = stringResource(R.string.postal_code)) },
                    onValueChange = {
                        postalCode = it
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 6.dp)
                )
                OutlinedTextField(
                    value = buildingNumber,
                    label = { Text(text = stringResource(R.string.building_number)) },
                    onValueChange = {
                        buildingNumber = it
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 6.dp)
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = city,
                label = { Text(text = stringResource(R.string.city)) },
                onValueChange = {
                    city = it
                }
            )

            Button(
                onClick = {
                    val user = User(
                        firstName = firstName.text,
                        lastName = lastName.text,
                        email = email.text,
                        telephone = telephone.text,
                        password = password.text,
                        address = address.text,
                        postalCode = postalCode.text,
                        buildingNumber = buildingNumber.text,
                        city = city.text
                    )
                },
                modifier = Modifier
                    .align(Alignment.End),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(stringResource(R.string.register))
            }
        }
    }
}


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
                plateNumber = "YW - 790 - 2",
                status = "Available",
                model = "2011 - Toyota Yaris",
                location = "Eindhoven",
                price = "95,-",
                img = R.drawable.yaris
            )

            val vehicle2 = Vehicle(
                plateNumber = "ABC - 123",
                status = "Not Available",
                model = "2020 - Honda Civic",
                location = "Amsterdam",
                price = "120,-",
                img = R.drawable.civic
            )

            val vehicle3 = Vehicle(
                plateNumber = "XYZ - 456",
                status = "Available",
                model = "2019 - Ford Focus",
                location = "Rotterdam",
                price = "80,-",
                img = R.drawable.focus
            )

            val vehicle4 = Vehicle(
                plateNumber = "DEF - 789",
                status = "Available",
                model = "2022 - Chevrolet Malibu",
                location = "Utrecht",
                price = "110,-",
                img = R.drawable.malibu
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
            painter = painterResource(id = vehicle.img),
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
                    text = vehicle.plateNumber,
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
                    Text(text = vehicle.location)
                }
                Row {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = stringResource(R.string.price)
                    )
                    Text(
                        text = vehicle.price,
                        modifier = Modifier
                            .padding(end = 24.dp)
                            .width(54.dp)
                    )
                }

            }
        }
    }
}