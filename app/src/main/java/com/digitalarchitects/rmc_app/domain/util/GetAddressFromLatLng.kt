package com.digitalarchitects.rmc_app.domain.util

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun Geocoder.getAddressFromLatLng(
    latitude: Double,
    longitude: Double,
): Address? = withContext(Dispatchers.IO) {
    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            suspendCoroutine { cont ->
                getFromLocation(latitude, longitude, 1) {
                    cont.resume(it.firstOrNull())
                }
            }
        } else {
            suspendCoroutine { cont ->
                @Suppress("DEPRECATION")
                val address = getFromLocation(latitude, longitude, 1)?.firstOrNull()
                cont.resume(address)
            }
        }
    } catch (e: Exception) {
        Log.e("Geocoder", "getAddressFromLatLng: ${e.message}")
        null
    }
}

suspend fun getAddressFromLatLng(
    context: Context,
    latitude: Double,
    longitude: Double,
    detailed: Boolean
): String {
    return try {
        val geoCoder = Geocoder(context)
        val address = geoCoder.getAddressFromLatLng(latitude, longitude)

        if (detailed) {
            return address?.getAddressLine(0) ?: ""
        }

        // Exclude country and postalCode from the address when detailed is false
        val addressLine = address?.getAddressLine(0) ?: ""
        val country = address?.countryName.orEmpty()
        val postalCode = address?.postalCode.orEmpty()

        // Remove the country and postalCode from the address
        val formattedAddress = addressLine
            .replace(", $country", "")
            .replace(", $postalCode", "")

        formattedAddress
    } catch (e: Exception) {
        Log.d("MyVehiclesViewModel", "getAddressFromLatLng: ${e.message}")
        e.printStackTrace()
        ""
    }
}