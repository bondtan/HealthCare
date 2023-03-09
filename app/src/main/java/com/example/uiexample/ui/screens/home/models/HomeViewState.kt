package com.example.uiexample.ui.screens.home.models

import androidx.compose.runtime.Stable
import com.example.uiexample.domain.models.HealthDomain
import java.time.LocalDate
import java.time.LocalTime

@Stable
data class HomeViewState(
    val currentTime: LocalTime = LocalTime.now(),
    val currentDate: LocalDate = LocalDate.now(),
    val currentSystolicPressure: Int = 0,
    val isSystolicPressureValid: Boolean = false,
    val currentDiastolicPressure: Int = 0,
    val isDiastolicPressureValid: Boolean = false,
    val currentPulse: Int = 0,
    val isPulseValid: Boolean = false,
    val isEnableSaveButton: Boolean = false,
    val healthList: List<HealthUI> = emptyList(),
    //val selectItemList: Int = -1
)

data class HealthUI(
    val id: Int? = null,
    val time: LocalTime,
    val date: LocalDate,
    val systolicPressure: Int,
    val diastolicPressure: Int,
    val pulse: Int,
)

fun HealthUI.fromUiToDomain(): HealthDomain =
    HealthDomain(
        id = this.id,
        time = this.time,
        date = this.date,
        systolicPressure = this.systolicPressure,
        diastolicPressure = this.diastolicPressure,
        pulse = this.pulse
    )