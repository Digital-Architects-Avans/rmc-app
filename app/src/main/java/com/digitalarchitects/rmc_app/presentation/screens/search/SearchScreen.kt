package com.digitalarchitects.rmc_app.presentation.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PriceChange
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import com.digitalarchitects.rmc_app.presentation.components.AddressEdit
import com.digitalarchitects.rmc_app.presentation.components.RmcAppBar
import com.digitalarchitects.rmc_app.presentation.components.RmcDateTextField
import com.digitalarchitects.rmc_app.presentation.components.RmcFilledButton
import com.digitalarchitects.rmc_app.presentation.components.RmcOutlinedButton
import com.digitalarchitects.rmc_app.presentation.components.RmcSpacer

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    navigateToScreen: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val placesPredictions by viewModel.placePredictions.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(SearchUIEvent.FetchFilterPreference)
    }

    Scaffold(
        topBar = {
            RmcAppBar(
                title = R.string.screen_title_search,
                navigationIcon = Icons.Rounded.Close,
                navigateUp = {
                    navigateToScreen(RmcScreen.RentACar.name)
                }
            )
        }
    ) { innerPadding ->
        androidx.compose.material3.Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = dimensionResource(R.dimen.padding_large))
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {

                    RmcDateTextField(
                        date = uiState.date,
                        onValueChange = { date ->
                            viewModel.onEvent(SearchUIEvent.DateChanged(date))
                        }
                    )

                    RmcSpacer(16)

                    AddressEdit(
                        query = uiState.query,
                        modifier = Modifier,
                        addressPlaceItemPredictions = placesPredictions,
                        onQueryChanged = { query ->
                            viewModel.onEvent(SearchUIEvent.OnAddressChange(query))
                        },
                        onClearClick = {
                            viewModel.onEvent(SearchUIEvent.OnAddressAutoCompleteClear)
                        },
                        onDoneClick = if (placesPredictions.isNotEmpty()) {
                            {
                                viewModel.onEvent(
                                    SearchUIEvent.OnAddressSelected(
                                        placesPredictions.first()
                                    )
                                )
                            }
                        } else {
                            { }
                        },
                        onItemClick = { placeItem ->

                            viewModel.onEvent(SearchUIEvent.OnAddressSelected(placeItem))
                        }
                    )

                    RmcSpacer()

                    RmcSlider(
                        label = stringResource(id = R.string.price),
                        icon = Icons.Filled.PriceChange,
                        sliderPosition = uiState.price.toFloat(),
                        maxValue = 250.0F,
                        onValueChange = {
                            viewModel.onEvent(SearchUIEvent.PriceChanged(it))

                        }
                    )

                    RmcSlider(
                        label = stringResource(id = R.string.distance),
                        icon = Icons.Filled.LocationOn,
                        sliderPosition = uiState.distance.toFloat(),
                        maxValue = 250.0F,
                        onValueChange = {
                            viewModel.onEvent(SearchUIEvent.DistanceChanged(it))

                        }
                    )

                    RmcSpacer(16)

                    RmcFilterLabel(label = stringResource(id = R.string.engine_type))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large))
                    ) {
                        Column(Modifier.weight(1f)) {
                            RmcFilterChip(
                                label = stringResource(id = R.string.engine_type_ice),
                                selected = uiState.engineTypeIce,
                                onClick = {
                                    viewModel.onEvent(SearchUIEvent.EngineTypeIceChanged(it))
                                }
                            )
                        }
                        Column(Modifier.weight(1f)) {
                            RmcFilterChip(
                                label = stringResource(id = R.string.engine_type_bev),
                                selected = uiState.engineTypeBev,
                                onClick = {
                                    viewModel.onEvent(SearchUIEvent.EngineTypeBevChanged(it))

                                }
                            )
                        }
                        Column(Modifier.weight(1f)) {
                            RmcFilterChip(
                                label = stringResource(id = R.string.engine_type_fcev),
                                selected = uiState.engineTypeFcev,
                                onClick = {
                                    viewModel.onEvent(SearchUIEvent.EngineTypeFcevChanged(it))
                                }
                            )
                        }
                    }
                    RmcSpacer(32)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = dimensionResource(R.dimen.padding_large)),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                ) {
                    Column(Modifier.weight(1f)) {
                        RmcOutlinedButton(
                            value = stringResource(id = R.string.clear),
                            onClick = {
                                viewModel.onEvent(SearchUIEvent.ClearFiltersButtonClicked)
                            }
                        )
                    }
                    Column(Modifier.weight(1f)) {
                        RmcFilledButton(
                            value = stringResource(id = R.string.apply),
                            onClick = {
                                keyboardController?.hide()
                                viewModel.onEvent(SearchUIEvent.ApplyFiltersButtonClicked)
                                navigateToScreen(RmcScreen.RentACar.name)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RmcSliderLabel(
    label: String,
    icon: ImageVector,
    sliderPosition: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = dimensionResource(R.dimen.padding_small))
                    .size(dimensionResource(R.dimen.icon_size_small)),
                tint = MaterialTheme.colorScheme.secondary
            )
            if (sliderPosition == 0) {
                Text(
                    text = stringResource(id = R.string.label_any),
                    style = MaterialTheme.typography.labelSmall
                )
            } else {
                when (label) {
                    "Distance" -> {
                        Text(
                            text = stringResource(id = R.string.label_distance_km, sliderPosition),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }

                    "Price" -> {
                        Text(
                            text = stringResource(id = R.string.label_price_euro, sliderPosition),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }

                    else -> {
                        Text(
                            text = "$sliderPosition",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RmcSlider(
    label: String,
    icon: ImageVector,
    sliderPosition: Float,
    maxValue: Float,
    onValueChange: (Float) -> Unit
) {
    RmcSliderLabel(
        label = label,
        icon = icon,
        sliderPosition = sliderPosition.toInt()
    )
    Slider(
        value = sliderPosition,
        valueRange = 0f..maxValue,
        onValueChange = onValueChange
    )
}

@Composable
fun RmcFilterLabel(
    label: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(R.dimen.padding_small))
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun RmcFilterChip(
    label: String,
    selected: Boolean,
    onClick: (Boolean) -> Unit
) {
    FilterChip(
        onClick = { onClick(selected) },
        label = {
            Text(text = label)
        },
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "$label selected",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
        modifier = Modifier
            .fillMaxWidth()
    )
}