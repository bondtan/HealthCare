package com.example.uiexample.data.localdatasourse.converters

import androidx.room.TypeConverter
import java.time.LocalTime

class TimeConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalTime? =
        value?. let { LocalTime.ofSecondOfDay(it) }

    @TypeConverter
    fun timeToTimestamp(time: LocalTime?): Long? {
        return time?.toSecondOfDay()?.toLong()
    }
}
