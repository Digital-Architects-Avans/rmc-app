package com.digitalarchitects.rmc_app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.digitalarchitects.rmc_app.domain.model.RentalStatus
import kotlinx.datetime.LocalDate

@Entity
@TypeConverters(Converters::class)
class LocalRental(
    @PrimaryKey(autoGenerate = false)
    val rentalId: String,
    val vehicleId: String,
    val userId: String,
    val date: LocalDate,
    val price: Double,
    val latitude: Float,
    val longitude: Float,
    val status: RentalStatus,
    val distanceTravelled: Double,
    val score: Int
)