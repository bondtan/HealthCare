package com.example.uiexample.data.localdatasourse

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.uiexample.data.localdatasourse.converters.DateConverters
import com.example.uiexample.data.localdatasourse.converters.TimeConverters
import com.example.uiexample.domain.models.HealthDomain
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "health")
data class HealthEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @TypeConverters(TimeConverters::class)
    @ColumnInfo(name = "time") val time: LocalTime,
    @TypeConverters(DateConverters::class)
    @ColumnInfo(name = "date")val date: LocalDate,
    @ColumnInfo(name = "systolic_pressure") val systolicPressure: Int,
    @ColumnInfo(name = "diastolic-pressure") val diastolicPressure: Int,
    @ColumnInfo(name = "pulse") val pulse: Int,
    @ColumnInfo(name = "total_time") val totalTime: Long
)

fun HealthEntity.fromDataToDomain(): HealthDomain =
    HealthDomain(
        id = this.id,
        time = this.time,
        date = this.date,
        systolicPressure = this.systolicPressure,
        diastolicPressure = this.diastolicPressure,
        pulse = this.pulse
    )