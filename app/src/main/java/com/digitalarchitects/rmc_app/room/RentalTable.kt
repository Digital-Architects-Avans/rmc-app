package com.digitalarchitects.rmc_app.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.digitalarchitects.rmc_app.model.RentalStatus
import java.time.LocalDate

@Entity
@TypeConverters(LocalDateConverter::class)
class RentalTable (
    val vehicleId: Int,
    val userId: Int,
    @TypeConverters(LocalDateConverter::class)
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
