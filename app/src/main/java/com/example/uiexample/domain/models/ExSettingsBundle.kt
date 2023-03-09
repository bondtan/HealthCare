package com.example.uiexample.domain.models

import com.example.uiexample.ui.theme.ExampleColorStyle
import com.example.uiexample.ui.theme.ExampleShapeStyle
import com.example.uiexample.ui.theme.ExampleSize

data class ExSettingsBundle(
    val isDarkMode: Boolean,
    val color: ExampleColorStyle,
    val sizeText: ExampleSize,
    val shape: ExampleShapeStyle,
)