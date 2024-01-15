package com.digitalarchitects.rmc_app.presentation.screens.editmyaccount

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import com.digitalarchitects.rmc_app.presentation.components.RmcAppBar
import com.digitalarchitects.rmc_app.presentation.components.RmcFilledButton
import com.digitalarchitects.rmc_app.presentation.components.RmcFilledTonalIconButton
import com.digitalarchitects.rmc_app.presentation.components.RmcOutlinedButton
import com.digitalarchitects.rmc_app.presentation.components.RmcSpacer
import com.digitalarchitects.rmc_app.presentation.components.RmcTextField
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun EditMyAccountScreen(
    viewModel: EditMyAccountViewModel,
    navigateToScreen: (String) -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()
    val userUpdated by viewModel.userUpdated.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(EditMyAccountUIEvent.FetchUser)
    }

    Scaffold(
        topBar = {
            RmcAppBar(
                title = R.string.screen_title_edit_account,
                navigationIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                navigateUp = {
                    navigateToScreen(RmcScreen.MyAccount.name)
                }
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
                    .padding(
                        start = dimensionResource(R.dimen.padding_large),
                        end = dimensionResource(R.dimen.padding_large)
                    )
                    .verticalScroll(rememberScrollState())
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ProfileImage(
                        profileImageSrc = if (!uiState.imgUpdated) {
                            uiState.profileImageSrc
                        } else {
                            uiState.imageUri.toString()
                        },
                        onImageSelected = { selectedUri ->
                            viewModel.onEvent(EditMyAccountUIEvent.SetImageUri(selectedUri))
                            Log.d("EditMyAccountScreen", "selectedUri: $selectedUri")
                        }
                    )
                }
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                ) {
                    RmcTextField(
                        label = stringResource(id = R.string.first_name),
                        leadingIcon = Icons.Filled.Person,
                        value = uiState.firstName,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = {
                            viewModel.onEvent(EditMyAccountUIEvent.SetFirstName(it))
                        },
                        modifier = Modifier.weight(1f)
                    )
                    RmcTextField(
                        label = stringResource(id = R.string.last_name),
                        value = uiState.lastName,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = {
                            viewModel.onEvent(EditMyAccountUIEvent.SetLastName(it))
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                RmcSpacer(8)

                RmcTextField(
                    label = stringResource(id = R.string.email),
                    leadingIcon = Icons.Filled.Email,
                    value = uiState.email,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        viewModel.onEvent(EditMyAccountUIEvent.SetEmail(it))
                    }
                )

                RmcSpacer(8)

                RmcTextField(
                    label = stringResource(id = R.string.telephone),
                    leadingIcon = Icons.Filled.Call,
                    value = uiState.phone,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        viewModel.onEvent(EditMyAccountUIEvent.SetPhone(it))
                    }
                )

                RmcSpacer(8)

                RmcTextField(
                    label = stringResource(id = R.string.address),
                    leadingIcon = Icons.Filled.Home,
                    value = uiState.street,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        viewModel.onEvent(EditMyAccountUIEvent.SetStreet(it))
                    }
                )

                RmcSpacer(8)

                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                ) {
                    RmcTextField(
                        label = stringResource(id = R.string.building_number),
                        leadingIcon = Icons.Filled.Numbers,
                        value = uiState.buildingNumber,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = {
                            viewModel.onEvent(EditMyAccountUIEvent.SetBuildingNumber(it))
                        },
                        modifier = Modifier.weight(1f)
                    )
                    RmcTextField(
                        label = stringResource(id = R.string.postal_code),
                        value = uiState.zipCode,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = {
                            viewModel.onEvent(EditMyAccountUIEvent.SetZipCode(it))
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                RmcSpacer(8)

                RmcTextField(
                    label = stringResource(id = R.string.city),
                    leadingIcon = Icons.Filled.LocationCity,
                    value = uiState.city,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        viewModel.onEvent(EditMyAccountUIEvent.SetCity(it))
                    }
                )

                RmcSpacer(8)

                RmcTextField(
                    label = stringResource(id = R.string.password),
                    leadingIcon = Icons.Filled.Lock,
                    value = uiState.password,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    isPassword = true,
                    onValueChange = {
                        viewModel.onEvent(EditMyAccountUIEvent.SetPassword(it))
                    }
                )

                RmcSpacer(16)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                ) {
                    Column(Modifier.weight(1f)) {
                        RmcOutlinedButton(
                            value = stringResource(id = R.string.cancel),
                            onClick = {
                                navigateToScreen(RmcScreen.MyAccount.name)
                            }
                        )
                    }
                    Column(Modifier.weight(1f)) {
                        RmcFilledButton(
                            value = stringResource(id = R.string.apply),
                            isEnabled = uiState.firstName.isNotEmpty() &&
                                    uiState.lastName.isNotEmpty() &&
                                    uiState.email.isNotEmpty() &&
                                    uiState.phone.isNotEmpty() &&
                                    uiState.street.isNotEmpty() &&
                                    uiState.zipCode.isNotEmpty() &&
                                    uiState.buildingNumber.isNotEmpty() &&
                                    uiState.city.isNotEmpty() &&
                                    uiState.password.isNotEmpty(),
                            onClick = {
                                viewModel.onEvent(EditMyAccountUIEvent.ConfirmEditMyAccountButtonClicked)
                            }
                        )
                    }
                }
                val context = LocalContext.current
                val toastMessage = if (userUpdated) {
                    stringResource(R.string.changes_saved)
                } else {
                    stringResource(R.string.unable_to_save_changes_try_again)
                }
                if (userUpdated) {
                    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
                    navigateToScreen(RmcScreen.MyAccount.name)
                    viewModel.onEvent(EditMyAccountUIEvent.ResetUserUpdated)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileImage(
    profileImageSrc: String?,
    onImageSelected: (Uri) -> Unit
) {
    var imageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var showDialog by rememberSaveable { mutableStateOf(false) }

    val painter = rememberAsyncImagePainter(
        profileImageSrc ?: Uri.parse(R.drawable.usericon.toString())
    )

    val context = LocalContext.current
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageUri = it
            onImageSelected(it)
        }
    }

    // Permission is granted, Initialize the cameraLauncher
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { isSuccess: Boolean ->
        if (isSuccess) {
            // Callback to handle the captured image
            imageUri?.let { onImageSelected(it) }
            showDialog = false
        }
    }

    val cameraPermissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Get a new image URI
                val newImageUri = createImageUri(context)
                imageUri = newImageUri

                // Launch the camera
                cameraLauncher.launch(newImageUri)
                showDialog = false
            } else {
                // Permission is denied, close the dialog
                showDialog = false
            }
        }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Card(
                shape = CircleShape,
                modifier = Modifier
                    .padding(8.dp)
                    .size(120.dp)
            ) {
                Image(
                    painter = painter,
                    contentDescription = stringResource(id = R.string.profile_picture),
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            // Open the dialog asking for image source
                            showDialog = true
                        },
                    contentScale = ContentScale.Crop
                )
            }
            RmcFilledTonalIconButton(
                icon = Icons.Filled.CameraAlt,
                label = R.string.camera,
                onClick = {
                    showDialog = true
                }
            )
        }
    }

    if (showDialog) {
        BasicAlertDialog(
            onDismissRequest = { showDialog = false },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
            content = {
                Surface(
                    shape = MaterialTheme.shapes.large,
                    tonalElevation = AlertDialogDefaults.TonalElevation,
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                ) {
                    Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_large))) {
                        Text(text = stringResource(R.string.select_image_source))
                        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                        ) {
                            Column(Modifier.weight(1f)) {
                                RmcFilledButton(
                                    value = stringResource(id = R.string.gallery),
                                    icon = Icons.Filled.Photo,
                                    onClick = {
                                        galleryLauncher.launch("image/*")
                                        showDialog = false
                                    }
                                )
                            }
                            Column(Modifier.weight(1f)) {
                                RmcFilledButton(
                                    value = stringResource(id = R.string.camera),
                                    icon = Icons.Filled.Camera,
                                    onClick = {
                                        // Before opening the camera, check for permission
                                        cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                                        showDialog = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}

private fun createImageUri(context: Context): Uri {
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
    )
}