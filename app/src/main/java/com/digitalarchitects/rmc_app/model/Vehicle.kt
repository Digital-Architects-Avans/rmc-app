package com.digitalarchitects.rmc_app.model

import kotlinx.serialization.Serializable

@Serializable
data class Vehicle(
    val plateNumber: String,
    val status: String,
    val model: String,
    val location: String,
    val price: String,
    val img: Int
)