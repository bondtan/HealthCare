package com.example.uiexample.data.localdatasourse.converters

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.ZoneId

class DateConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? =
        value?.let { LocalDate.ofEpochDay(it) }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): Long? {
        val zoneId: ZoneId = ZoneId.systemDefault()
        return date?.let { it.atStartOfDay(zoneId).toEpochSecond()/ (24*60*60) }
    }
}



