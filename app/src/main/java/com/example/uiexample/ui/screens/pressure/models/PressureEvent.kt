package com.example.uiexample.ui.screens.pressure.models

sealed class PressureEvent {
    data class SelectItem (val value: Int): PressureEvent()
}