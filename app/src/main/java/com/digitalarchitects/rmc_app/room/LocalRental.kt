package com.digitalarchitects.rmc_app.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.digitalarchitects.rmc_app.model.RentalStatus
import kotlinx.datetime.LocalDate

@Entity
@TypeConverters(Converters::class)
class LocalRental(
    @PrimaryKey(autoGenerate = false)
    val rentalId: String,
    val vehicleId: Int,
    val userId: Int,
    val date: LocalDate,
    val price: Double,
    val latitude: Float,
    val longitude: Float,
    val status: RentalStatus,
    val distanceTravelled: Double,
    val score: Int
)