package com.digitalarchitects.rmc_app.screens

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
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PriceChange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.components.RmcFilledButton
import com.digitalarchitects.rmc_app.components.RmcOutlinedButton
import com.digitalarchitects.rmc_app.components.RmcSpacer
import com.digitalarchitects.rmc_app.components.RmcTextField
import com.digitalarchitects.rmc_app.data.search.SearchUIEvent
import com.digitalarchitects.rmc_app.data.search.SearchViewModel

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = viewModel()
    // navController: NavController,
) {
    val searchUiState by searchViewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            RmcTextField(
                label = stringResource(id = R.string.date),
                icon = Icons.Filled.CalendarMonth,
                value = searchUiState.date ?: "",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                onValueChange = { searchViewModel.onEvent(SearchUIEvent.DateChanged(it)) },
                modifier = Modifier.fillMaxWidth()
            )

            RmcSpacer()

            RmcTextField(
                label = stringResource(id = R.string.location),
                icon = Icons.Filled.LocationOn,
                value = searchUiState.location ?: "",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.None
                ),
                onValueChange = { searchViewModel.onEvent(SearchUIEvent.LocationChanged(it)) },
                modifier = Modifier.fillMaxWidth()
            )

            RmcSpacer(32)

            RmcSlider(
                label = stringResource(id = R.string.price),
                icon = Icons.Filled.PriceChange,
                sliderPosition = searchUiState.price?.toFloat() ?: 0.0F,
                maxValue = 250.0F,
                onValueChange = { searchViewModel.onEvent(SearchUIEvent.PriceChanged(it)) }
            )

            RmcSpacer()

            RmcSlider(
                label = stringResource(id = R.string.distance),
                icon = Icons.Filled.LocationOn,
                sliderPosition = searchUiState.distance?.toFloat() ?: 0.0F,
                maxValue = 250.0F,
                onValueChange = { searchViewModel.onEvent(SearchUIEvent.DistanceChanged(it)) }
            )

            RmcSpacer()

            RmcFilterLabel(label = stringResource(id = R.string.engine_type))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Column(Modifier.weight(1f)) {
                    RmcFilterChip(
                        label = stringResource(id = R.string.engine_type_ice),
                        selected = searchUiState.engineTypeIce,
                        onClick = { searchViewModel.onEvent(SearchUIEvent.EngineTypeIceChanged(it)) }
                    )
                }
                Column(Modifier.weight(1f)) {
                    RmcFilterChip(
                        label = stringResource(id = R.string.engine_type_cev),
                        selected = searchUiState.engineTypeCev,
                        onClick = { searchViewModel.onEvent(SearchUIEvent.EngineTypeCevChanged(it)) }
                    )
                }
                Column(Modifier.weight(1f)) {
                    RmcFilterChip(
                        label = stringResource(id = R.string.engine_type_fbev),
                        selected = searchUiState.engineTypeFbev,
                        onClick = { searchViewModel.onEvent(SearchUIEvent.EngineTypeFbevChanged(it)) }
                    )
                }
            }

            RmcSpacer()

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Column(Modifier.weight(1f)) {
                    RmcOutlinedButton(
                        value = stringResource(id = R.string.clear),
                        onClick = { searchViewModel.clearFilters() }
                    )
                }
                Column(Modifier.weight(1f)) {
                    RmcFilledButton(
                        value = stringResource(id = R.string.apply),
                        onClick = { /*TODO: Close screen, go back to Rent A Car */ }
                    )
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
                    .padding(end = 8.dp)
                    .size(16.dp),
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
            .padding(bottom = 8.dp)
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