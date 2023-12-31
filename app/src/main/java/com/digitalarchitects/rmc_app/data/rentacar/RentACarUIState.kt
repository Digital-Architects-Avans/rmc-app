package com.digitalarchitects.rmc_app.data.rentacar

import com.google.android.gms.maps.model.LatLng

data class RentACarUIState(
    val startLocation: LatLng = LatLng(51.587959, 4.775130),
    val userLocation: LatLng = LatLng(51.469890, 5.546670),
    var showVehicleList: Boolean = false,
    // var showVehicleDetails: Boolean = false,
    var detailsVehicleId: Int = 0,
    var placeholder: String =  ""
)