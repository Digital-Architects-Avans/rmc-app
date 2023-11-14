package com.example.rmc_app.screens

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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rmc_app.R
import com.example.rmc_app.components.MyTextFieldComponent
import com.example.rmc_app.model.User

@Composable
fun RegisterScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(Modifier.fillMaxWidth()) {
                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.first_name),
                        modifier = Modifier.weight(1f) // Apply weight to distribute horizontal space
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.last_name),
                        modifier = Modifier.weight(1f) // Apply weight to distribute horizontal space
                    )
                }

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    Modifier.fillMaxWidth()
                )
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.telephone),
                    Modifier.fillMaxWidth()
                )
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    Modifier.fillMaxWidth()
                )
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.address),
                    Modifier.fillMaxWidth()
                )

                Row(Modifier.fillMaxWidth()) {
                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.postal_code),
                        modifier = Modifier.weight(1f) // Apply weight to distribute horizontal space
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.building_number),
                        modifier = Modifier.weight(1f) // Apply weight to distribute horizontal space
                    )
                }

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.city),
                    Modifier.fillMaxWidth()
                )

            }
        }
    }
}


@Preview
@Composable
fun DefaultPreviewOfSignUpScreen() {
    RegisterScreen()
}


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