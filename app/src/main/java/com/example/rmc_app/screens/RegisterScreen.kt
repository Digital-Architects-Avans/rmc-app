package com.example.rmc_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rmc_app.R
import com.example.rmc_app.components.ButtonComponent
import com.example.rmc_app.components.CheckboxComponent
import com.example.rmc_app.components.ClickableLoginTextComponent
import com.example.rmc_app.components.DividerTextComponent
import com.example.rmc_app.components.MyTextFieldComponent
import com.example.rmc_app.components.PasswordTextFieldComponent
import com.example.rmc_app.navigation.RmcAppRouter
import com.example.rmc_app.navigation.Screen

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
                        icon = Icons.Default.Person,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.weight(1f) // Apply weight to distribute horizontal space
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.last_name),
                        icon = Icons.Default.Person,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.weight(1f) // Apply weight to distribute horizontal space
                    )
                }

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    icon = Icons.Default.Email,
                    Modifier.fillMaxWidth()
                )
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.telephone),
                    icon = Icons.Default.Call,
                    Modifier.fillMaxWidth()
                )
                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    icon = Icons.Default.Lock,
                    Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    )
                )
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.address),
                    icon = Icons.Filled.Home,
                    Modifier.fillMaxWidth()
                )

                Row(Modifier.fillMaxWidth()) {
                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.postal_code),
                        icon = Icons.Default.Numbers,
                        modifier = Modifier.weight(1f) // Apply weight to distribute horizontal space
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.building_number),
                        icon = Icons.Default.Numbers,
                        modifier = Modifier.weight(1f) // Apply weight to distribute horizontal space
                    )
                }

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.city),
                    icon = Icons.Default.LocationCity,
                    Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    )
                )

                CheckboxComponent(value = stringResource(id = R.string.terms_and_conditions),
                    onTextSelected = {
                        RmcAppRouter.navigateTo(Screen.TermsAndConditionsScreen)
                    }
                )

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.register),
                    onButtonClicked = { /*TODO*/ }
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(
                    tryingToLogin = true,
                    onTextSelected = {
                        RmcAppRouter.navigateTo(Screen.LoginScreen)
                    })

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
//@Composable
//fun RegisterSection() {
//    var firstName by remember { mutableStateOf(TextFieldValue("")) }
//    var lastName by remember { mutableStateOf(TextFieldValue("")) }
//    var email by remember { mutableStateOf(TextFieldValue("")) }
//    var telephone by remember { mutableStateOf(TextFieldValue("")) }
//    var password by remember { mutableStateOf(TextFieldValue("")) }
//    var address by remember { mutableStateOf(TextFieldValue("")) }
//    var postalCode by remember { mutableStateOf(TextFieldValue("")) }
//    var buildingNumber by remember { mutableStateOf(TextFieldValue("")) }
//    var city by remember { mutableStateOf(TextFieldValue("")) }
//
//    Column(modifier = Modifier.padding(top = 24.dp)) {
//        Row(
//            modifier = Modifier
//                .height(40.dp)
//                .padding(horizontal = 24.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(
//                onClick = { /* TODO */ }
//            ) {
//                Box(
//                    modifier = Modifier
//                        .size(40.dp)
//                        .background(Color.White, shape = CircleShape)
//                        .border(1.dp, Color.LightGray, shape = CircleShape)
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Clear,
//                        contentDescription = stringResource(R.string.close),
//                        modifier = Modifier.fillMaxSize(),
//                        tint = Color.Gray
//                    )
//                }
//            }
//            Text(
//                text = stringResource(R.string.register),
//                style = TextStyle(
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.Bold
//                ),
//                modifier = Modifier.padding(start = 12.dp)
//            )
//        }
//
//        Column(
//            verticalArrangement = Arrangement.SpaceAround,
//            modifier = Modifier
//                .padding(24.dp)
//                .fillMaxHeight()
//        ) {
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                OutlinedTextField(
//                    value = firstName,
//                    label = { Text(text = stringResource(R.string.first_name)) },
//                    onValueChange = {
//                        firstName = it
//                    },
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(end = 6.dp)
//                )
//                OutlinedTextField(
//                    value = lastName,
//                    label = { Text(text = stringResource(R.string.last_name)) },
//                    onValueChange = {
//                        lastName = it
//                    },
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(start = 6.dp)
//                )
//            }
//            OutlinedTextField(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                value = email,
//                label = { Text(text = stringResource(R.string.email)) },
//                onValueChange = {
//                    email = it
//                }
//            )
//            OutlinedTextField(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                value = telephone,
//                label = { Text(text = stringResource(R.string.telephone)) },
//                onValueChange = {
//                    telephone = it
//                }
//            )
//            OutlinedTextField(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                value = password,
//                visualTransformation = PasswordVisualTransformation(),
//                label = { Text(text = stringResource(R.string.password)) },
//                onValueChange = {
//                    password = it
//                }
//            )
//            OutlinedTextField(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                value = address,
//                label = { Text(text = stringResource(R.string.address)) },
//                onValueChange = {
//                    address = it
//                }
//            )
//            Row {
//                OutlinedTextField(
//                    value = postalCode,
//                    label = { Text(text = stringResource(R.string.postal_code)) },
//                    onValueChange = {
//                        postalCode = it
//                    },
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(end = 6.dp)
//                )
//                OutlinedTextField(
//                    value = buildingNumber,
//                    label = { Text(text = stringResource(R.string.building_number)) },
//                    onValueChange = {
//                        buildingNumber = it
//                    },
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(start = 6.dp)
//                )
//            }
//            OutlinedTextField(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                value = city,
//                label = { Text(text = stringResource(R.string.city)) },
//                onValueChange = {
//                    city = it
//                }
//            )
//
//            Button(
//                onClick = {
//                    val user = User(
//                        firstName = firstName.text,
//                        lastName = lastName.text,
//                        email = email.text,
//                        telephone = telephone.text,
//                        password = password.text,
//                        address = address.text,
//                        postalCode = postalCode.text,
//                        buildingNumber = buildingNumber.text,
//                        city = city.text
//                    )
//                },
//                modifier = Modifier
//                    .align(Alignment.End),
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                Text(stringResource(R.string.register))
//            }
//        }
//    }
//}