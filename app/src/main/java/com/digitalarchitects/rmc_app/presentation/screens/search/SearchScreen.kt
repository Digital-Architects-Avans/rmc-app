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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PriceChange
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import com.digitalarchitects.rmc_app.presentation.components.RmcAppBar
import com.digitalarchitects.rmc_app.presentation.components.RmcFilledButton
import com.digitalarchitects.rmc_app.presentation.components.RmcOutlinedButton
import com.digitalarchitects.rmc_app.presentation.components.RmcSpacer
import com.digitalarchitects.rmc_app.presentation.components.RmcTextField

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    navigateToScreen: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
// TODO Default state values
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
                    .padding(dimensionResource(R.dimen.padding_large))
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    RmcTextField(
                        label = stringResource(id = R.string.date),
                        icon = Icons.Filled.CalendarMonth,
                        value = "searchUiState.date ?: ",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = {
                            // TODO searchViewModel.onEvent(SearchUIEvent.DateChanged(it))
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    RmcSpacer()

                    RmcTextField(
                        label = stringResource(id = R.string.location),
                        icon = Icons.Filled.LocationOn,
                        value = "searchUiState.location ?: ",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.None
                        ),
                        onValueChange = {
                            // TODO searchViewModel.onEvent(SearchUIEvent.LocationChanged(it))
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    RmcSpacer(32)

                    RmcSlider(
                        label = stringResource(id = R.string.price),
                        icon = Icons.Filled.PriceChange,
                        sliderPosition = /*searchUiState.price?.toFloat() ?:*/ 0.0F,
                        maxValue = 250.0F,
                        onValueChange = {
                            //TODO     searchViewModel.onEvent(SearchUIEvent.PriceChanged(it))
                        }
                    )

                    RmcSpacer()

                    RmcSlider(
                        label = stringResource(id = R.string.distance),
                        icon = Icons.Filled.LocationOn,
                        sliderPosition = /*searchUiState.distance?.toFloat() ?:*/ 0.0F,
                        maxValue = 250.0F,
                        onValueChange = {
                            //TODO  searchViewModel.onEvent(SearchUIEvent.DistanceChanged(it))
                        }
                    )

                    RmcSpacer()

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
                                    //TODO
                                }
                            )
                        }
                        Column(Modifier.weight(1f)) {
                            RmcFilterChip(
                                label = stringResource(id = R.string.engine_type_bev),
                                selected = uiState.engineTypeBev,
                                onClick = {
                                    //   TODO
                                }
                            )
                        }
                        Column(Modifier.weight(1f)) {
                            RmcFilterChip(
                                label = stringResource(id = R.string.engine_type_fcev),
                                selected = uiState.engineTypeFcev,
                                onClick = {
                                    // TODO
                                }
                            )
                        }
                    }
                    RmcSpacer(32)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                ) {
                    Column(Modifier.weight(1f)) {
                        RmcOutlinedButton(
                            value = stringResource(id = R.string.clear),
                            onClick = {
                                // TODO: clearFilters
                            }
                        )
                    }
                    Column(Modifier.weight(1f)) {
                        RmcFilledButton(
                            value = stringResource(id = R.string.apply),
                            onClick = {
                                // TODO: applyFilters
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

@OptIn(ExperimentalMaterial3Api::class)
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

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        viewModel = viewModel(),
        navigateToScreen = { }
    )
}