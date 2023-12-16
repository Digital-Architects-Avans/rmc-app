package com.digitalarchitects.rmc_app.room

import androidx.room.Entity
//import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.digitalarchitects.rmc_app.model.RentalStatus
import java.time.LocalDate

@Entity
@TypeConverters(LocalDateConverter::class)
class RentalTable(
    @TypeConverters(LocalDateConverter::class)
    val date: LocalDate,
    val price: Double,
    val latitude: Float = 50.50f,
    val longitude: Float = 50.50f,
    val status: RentalStatus = RentalStatus.PENDING,
    val distanceTravelled: Double,
    val score: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
//    @ForeignKey(
//        entity = VehicleTable::class,
//        parentColumns = ["id"],
//        childColumns = ["vehicleId"],
//        onDelete = ForeignKey.CASCADE
//    )
    val vehicleId: Int,
//    @ForeignKey()
    val userId: Int,
)
