package com.example.uiexample.ui.screens.settings.models

import com.example.uiexample.ui.theme.ExampleColorStyle
import com.example.uiexample.ui.theme.ExampleMode
import com.example.uiexample.ui.theme.ExampleShapeStyle
import com.example.uiexample.ui.theme.ExampleSize

data class SettingsViewState(
    val selectedMode: ExampleMode,
    val isSelectedDarkMode: Boolean,
    val selectedColor: ExampleColorStyle,
    val selectedSizeText: ExampleSize,
    val selectedShape: ExampleShapeStyle,
    val selectedTimeShowNotification: String
)

