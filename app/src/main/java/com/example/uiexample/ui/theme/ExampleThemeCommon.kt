package com.example.uiexample.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

data class ExampleColors (
    val primaryBackground: Color,
    val secondaryBackground: Color,
    val primaryText: Color,
    val secondaryText: Color,
    val accentColor: Color,
    val errorColor: Color,
    val inversionText: Color
)

data class ExampleTypography (
    val header1: TextStyle,
    val header: TextStyle,
    val subtitle: TextStyle,
    val largeBody: TextStyle,
    val ordinaryBody: TextStyle,
    val smallBody: TextStyle,
    val bottomNav: TextStyle
)

data class ExampleShape (
    val cornerStyle: Shape
)

enum class ExampleMode {
    LIGHT, DARK
}

enum class ExampleColorStyle {
    BLUE, GRAY, YELLOW, PURPLE
}

enum class ExampleSize {
    SMALL, BIG
}

enum class ExampleShapeStyle {
     ROUNDED, SQUIRE
}

object ExampleTheme {
    val colors: ExampleColors
    @Composable
    get() = LocalExampleColors.current

    val typography: ExampleTypography
    @Composable
    get() = LocalExampleTypography.current

    val shape: ExampleShape
    @Composable
    get() = LocalExampleShape.current
}

val LocalExampleColors = staticCompositionLocalOf <ExampleColors> {
    error("No colors provided")
}

val LocalExampleTypography = staticCompositionLocalOf <ExampleTypography> {
    error("No typography provided")
}

val LocalExampleShape = staticCompositionLocalOf <ExampleShape> {
    error("No shape provided")
}