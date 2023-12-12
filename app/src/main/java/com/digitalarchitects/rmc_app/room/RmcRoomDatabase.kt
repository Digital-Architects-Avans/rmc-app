package com.digitalarchitects.rmc_app.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Database(
    entities = [UserTable::class, /*VehicleTable::class, RentalTable::class*/],
    version= 1
)
abstract class RmcRoomDatabase:RoomDatabase() {
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


class BigDecimalConverter {
    @TypeConverter
    fun fromBigDecimal(value: BigDecimal?): String? {
        return value?.toString()
    }
    @TypeConverter
    fun toBigDecimal(value: String?): BigDecimal? {
        return value?.let { BigDecimal(it) }
    }
}
