package com.digitalarchitects.rmc_app.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Database(
    version = 1,
    entities = [UserTable::class, VehicleTable::class, RentalTable::class]
)
abstract class RmcRoomDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val vehicleDao: VehicleDao
    abstract val rentalDao: RentalDao
}

class LocalDateConverter {

    private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE

    @TypeConverter
    fun fromLocalDate(value: LocalDate?): String? {
        return value?.format(formatter)
    }

    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it, formatter) }
    }
}
