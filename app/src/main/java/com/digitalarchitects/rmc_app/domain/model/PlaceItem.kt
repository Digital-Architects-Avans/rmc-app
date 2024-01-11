package com.digitalarchitects.rmc_app.domain.model

data class PlaceItem(
    val id: String?,
    var name: String?,
    val icon: String?,
    val latitude: Double,
    val longitude: Double,
    val distanceFromLocation: Float,
    val address: String
) {
    
    override fun toString(): String {
        return address
    }
}