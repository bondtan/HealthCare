package com.example.uiexample.ui.screens.pulse.models

sealed class PulseEvent {
    data class SelectPulseItem (val value: Int): PulseEvent()
}