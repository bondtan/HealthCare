package com.example.uiexample.ui.screens.pressure.models

import java.time.LocalDate
import java.time.LocalTime

data class PressureViewState(
    val pressureList: List<PressureUi> = emptyList(),
    val selectPressureItem: PressureUi = PressureUi(
        id = -1,
        time = LocalTime.now(),
        date = LocalDate.now(),
        systolicPressure = 0,
        diastolicPressure = 0
    ),
    val selectIdItem: Int = -1
)

data class PressureUi(
    val id: Int?,
    val time: LocalTime,
    val date: LocalDate,
    val systolicPressure: Int,
    val diastolicPressure: Int
)