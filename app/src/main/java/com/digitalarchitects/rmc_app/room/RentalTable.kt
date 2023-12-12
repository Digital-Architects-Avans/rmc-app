package com.digitalarchitects.rmc_app.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.digitalarchitects.rmc_app.model.RentalStatus
import java.math.BigDecimal
import java.time.LocalDate

@Entity
class RentalTable (
    val vehicleId: Int,
    val userId: Int,
    val date: LocalDate,
    val price: Double,
    val latitude: BigDecimal = BigDecimal("50.25"),
    val longitude: BigDecimal = BigDecimal("50.25"),
    val status: RentalStatus = RentalStatus.PENDING,
    val distanceTravelled: Double,
    val score: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int
)