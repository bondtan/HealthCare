package com.example.uiexample.ui.screens.home.models

import java.time.LocalDate
import java.time.LocalTime

sealed class HomeEvent {
    data class TimeChanged (val value: LocalTime): HomeEvent()
    data class DataChanged (val value: LocalDate): HomeEvent()
    data class SystolicPressureChanged (val value: String): HomeEvent()
    data class DiastolicPressureChanged (val value: String): HomeEvent()
    data class PulseChanged (val value: String): HomeEvent()
    object SaveClicked: HomeEvent()
    data class DeleteSelectHealthCardItem (val value: HealthUI): HomeEvent()
}