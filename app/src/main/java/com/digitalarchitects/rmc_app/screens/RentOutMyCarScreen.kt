package com.digitalarchitects.rmc_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.swipeable
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.components.RmcSpacer
import com.digitalarchitects.rmc_app.components.RmcUserIcon
import com.digitalarchitects.rmc_app.components.SmallHeadingTextComponent
import com.digitalarchitects.rmc_app.data.editmyaccount.EditMyAccountUIEvent
import com.digitalarchitects.rmc_app.data.editmyaccount.EditMyAccountViewModel
import com.digitalarchitects.rmc_app.data.rentoutmycar.RentOutMyCarUIEvent
import com.digitalarchitects.rmc_app.data.rentoutmycar.RentOutMyCarViewModel
import com.digitalarchitects.rmc_app.data.rentoutmycar.RentalTab
import com.digitalarchitects.rmc_app.model.Rental
import com.digitalarchitects.rmc_app.model.RentalStatus
import com.digitalarchitects.rmc_app.model.User
import com.digitalarchitects.rmc_app.model.Vehicle
import java.time.LocalDate

@Composable
fun RentOutMyCarScreen(
    viewModel: RentOutMyCarViewModel,
    navigateToScreen: (String) -> Unit
) {
    val navigateToScreenEvent by viewModel.navigateToScreen.collectAsState()
    if (navigateToScreenEvent != null) {
        navigateToScreen(navigateToScreenEvent!!.name)
    }
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
// TODO
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TabRow(
            selectedTabIndex = uiState.selectedTab.ordinal,
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = colorResource(R.color.white),
            contentColor = colorResource(R.color.purple_500)
        ) {
            RentalTab.values().forEachIndexed { index, tab ->
                Tab(
                    text = { Text(stringResource(id = tab.tabNameResourceId)) },
                    selected = uiState.selectedTab == tab,
                    onClick = { uiState.selectedTab = tab },
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        RmcSpacer()

        LazyColumn {
//           TODO  val filteredList = getFilteredRentals(list, selectedTab)

//            items(filteredList) { rental ->
//                vehicles.find { it.id == rental.vehicleId }
//                    ?.let { RentalItem(rental = rental, vehicle = it, user = user) }
//            }
        }
    }
}



//fun getFilteredRentals(list: List<Rental>, selectedTab: RentalTab): List<Rental> {
//    return when (selectedTab) {
//        RentalTab.PENDING -> list.filter { it.status == RentalStatus.PENDING }
//        RentalTab.OPEN -> list.filter { it.status == RentalStatus.APPROVED && it.date.isAfter(LocalDate.now()) }
//        RentalTab.HISTORY -> list.filter {
//            it.status == RentalStatus.DENIED || it.status == RentalStatus.CANCELLED || (it.status == RentalStatus.APPROVED && it.date.isBefore(LocalDate.now()))
//        }
//    }
//}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentalItem(rental: Rental, vehicle: Vehicle, user: User) {
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable {
                isSheetOpen = true
            },
        verticalArrangement = Arrangement.SpaceEvenly,
        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(text = stringResource(id = R.string.vehicle))
                Text(text = ": ${rental.vehicleId}")
            }
            Row {
                Text(text = stringResource(id = R.string.date))
                Text(text = ": ${rental.date}")
            }
            Row {
                Text(text = stringResource(id = R.string.price))
                Text(text = ": ${rental.price}")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(text = stringResource(id = R.string.renter))
                Text(text = ": ${rental.userId}")
            }
            Row {
                Text(text = stringResource(id = R.string.status))
                Text(text = ": ${rental.status}")
            }
        }
    }
    Divider(modifier = Modifier.padding(8.dp))
    if(isSheetOpen){
        ModalBottomSheet(
            modifier = Modifier.height(500.dp),
            sheetState = sheetState,
            onDismissRequest = { isSheetOpen = false },
        ) {
            Column(
                modifier = Modifier
                    .height(500.dp)
                    .padding(16.dp, 0.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
            Text(
                text = "${rental.date}",
                color = Color.Red,
                fontSize = 24.sp,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
            Row {
                RmcUserIcon(userIcon = R.drawable.usericon, size = dimensionResource(R.dimen.image_size_medium), onClick = {})
                SmallHeadingTextComponent(value = "${user.firstName} ${user.lastName}")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Box(
                    modifier = Modifier
                        .height(24.dp)
                        .background(
                            color = Color.Red,
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 18.dp),
                        text = stringResource(id = R.string.reject_rental),
                        color = Color.White,
                    )
                }
                Box(
                    modifier = Modifier
                        .height(24.dp)
                        .background(
                            color = Color.Green,
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        text = stringResource(id = R.string.accept_rental),
                        color = Color.White,
                    )
                }
            }
            RmcSpacer()
            Column {
                VehicleListItem(vehicle = vehicle)
            }
            RmcSpacer()
            RmcSpacer()
        }
    }
    }
}
