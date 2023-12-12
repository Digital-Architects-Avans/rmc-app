package com.digitalarchitects.rmc_app.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.digitalarchitects.rmc_app.model.RentalStatus
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity
@TypeConverters(LocalDateConverter::class, BigDecimalConverter::class)
class RentalTable (
    val vehicleId: Int,
    val userId: Int,
    @TypeConverters(LocalDateConverter::class)
    val date: LocalDate,
    val price: Double,
    @TypeConverters(BigDecimalConverter::class)
    val latitude: BigDecimal = BigDecimal("50.25"),
    @TypeConverters(BigDecimalConverter::class)
    val longitude: BigDecimal = BigDecimal("50.25"),
    val status: RentalStatus = RentalStatus.PENDING,
    val distanceTravelled: Double,
    val score: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int
)
