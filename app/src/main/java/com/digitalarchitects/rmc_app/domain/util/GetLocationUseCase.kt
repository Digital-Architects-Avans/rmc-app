package com.digitalarchitects.rmc_app.domain.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.digitalarchitects.rmc_app.data.remote.ILocationService
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val locationService: ILocationService
) {
    @RequiresApi(Build.VERSION_CODES.S)
    operator fun invoke(): Flow<LatLng?> = locationService.requestLocationUpdates()

}