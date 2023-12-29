package com.digitalarchitects.rmc_app.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.digitalarchitects.rmc_app.model.RentalStatus
import kotlinx.datetime.LocalDate

@Entity
@TypeConverters(Converters::class)
class LocalRental(
    val vehicleId: Int,
    val userId: Int,
    val date: LocalDate,
    val price: Double,
    val latitude: Float = 50.50f,
    val longitude: Float = 50.50f,
    val status: RentalStatus = RentalStatus.PENDING,
    val distanceTravelled: Double,
    val score: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int
)