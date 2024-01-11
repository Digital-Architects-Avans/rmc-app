package com.digitalarchitects.rmc_app.domain.util

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun millisToLocalDateConverter(millis: Long): LocalDate {
    val instant = Instant.fromEpochMilliseconds(millis)
    return instant.toLocalDateTime(TimeZone.currentSystemDefault()).date
}

// Validation function to check if the entered date is valid
fun validateDate(dateString: String): Boolean {
    return try {
        LocalDate.parse(dateString)
        true
    } catch (e: Exception) {
        false
    }
}