package com.example.uiexample.ui.screens.settings.models

import com.example.uiexample.ui.theme.ExampleColorStyle
import com.example.uiexample.ui.theme.ExampleShapeStyle
import com.example.uiexample.ui.theme.ExampleSize

sealed class SettingsEvent {
    data class SelectedMode(val value: Boolean): SettingsEvent()
    data class SelectedColor(val value: ExampleColorStyle): SettingsEvent()
    data class SelectedSizeText (val value: ExampleSize): SettingsEvent()
    data class SelectedShape (val value: ExampleShapeStyle): SettingsEvent()
    data class SelectedTimeShowNotification(val value: String): SettingsEvent()
}