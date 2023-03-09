package com.example.uiexample.domain.models

import com.example.uiexample.data.localdatasourse.HealthEntity
import com.example.uiexample.ui.screens.home.models.HealthUI
import com.example.uiexample.ui.screens.pressure.models.PressureUi
import com.example.uiexample.ui.screens.pulse.models.PulseUi
import java.time.LocalDate
import java.time.LocalTime

data class HealthDomain(
    val id: Int? = null,
    val time: LocalTime,
    val date: LocalDate,
    val systolicPressure: Int,
    val diastolicPressure: Int,
    val pulse: Int
)

fun HealthDomain.fromDomainToData(): HealthEntity =
    HealthEntity(
        id = this.id,
        time = this.time,
        //this.time.toSecondOfDay().toLong(),
        date = this.date,
        //this.date.let { it.atStartOfDay(ZoneId.systemDefault()).toEpochSecond()/(24*60*60) },
        systolicPressure = this.systolicPressure,
        diastolicPressure = this.diastolicPressure,
        pulse = this.pulse,
        totalTime = time.toSecondOfDay() + date.toEpochDay() * 24 * 60 * 60
    )

fun HealthDomain.fromDomainToUi(): HealthUI =
    HealthUI(
        id = this.id,
        time = this.time,
        date = this.date,
        systolicPressure = this.systolicPressure,
        diastolicPressure = this.diastolicPressure,
        pulse = this.pulse
    )

fun HealthDomain.fromDomainToPulseUi(): PulseUi =
    PulseUi(
        id = this.id,
        time = this.time,
        date = this.date,
        pulse = this.pulse
    )

fun HealthDomain.fromDomainToPressureUi(): PressureUi =
    PressureUi(
        id = this.id,
        time = this.time,
        date = this.date,
        systolicPressure = this.systolicPressure,
        diastolicPressure = this.diastolicPressure
    )