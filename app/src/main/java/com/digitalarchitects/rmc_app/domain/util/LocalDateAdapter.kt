package com.digitalarchitects.rmc_app.domain.util

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import kotlinx.datetime.LocalDate

class LocalDateAdapter : JsonAdapter<LocalDate>() {

    @ToJson
    override fun toJson(writer: JsonWriter, value: LocalDate?) {
        value?.let {
            writer.value(it.toString())
        }
    }

    @FromJson
    override fun fromJson(reader: JsonReader): LocalDate {
        return LocalDate.parse(reader.nextString())
    }
}