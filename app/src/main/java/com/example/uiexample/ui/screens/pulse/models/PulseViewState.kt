package com.example.uiexample.ui.screens.pulse.models

import java.time.LocalDate
import java.time.LocalTime

data class PulseViewState(
    val pulseList: List<PulseUi> = emptyList(),
    val selectPulseItem: PulseUi? = null,
    val isSelectedItem: Boolean = false
)

data class PulseUi(
    val id: Int?,
    val time: LocalTime,
    val date: LocalDate,
    val pulse: Int
)

