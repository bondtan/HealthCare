package com.example.uiexample.ui.theme

import androidx.compose.ui.graphics.Color

val baseLightPalette = ExampleColors(
    primaryBackground = Color(0xFFFFFFFF),
    secondaryBackground = Color(0xFF999595),
    primaryText = Color(0xFF000000),
    secondaryText = Color(0xFF000000),
    accentColor = Color(0xFF2C40B1),
    errorColor = Color(0xFFF44336),
    inversionText = Color(0xFFFFFFFF)
)

val baseDarkPalette = ExampleColors(
    primaryBackground = Color(0xFF362E46),
    secondaryBackground = Color(0xFF1C152E),
    primaryText = Color(0xFFCCB8F0),
    secondaryText = Color(0xFFAF87F8),
    accentColor = Color(0xFF691FEC),
    errorColor = Color(0xFFF44336),
    inversionText = Color(0xFF221D2B)
)

val blueLightPalette = baseLightPalette.copy(
    primaryBackground = Color(0xFFBFE3E7),
    secondaryBackground = Color(0xFF3FA0EC),
)

val greyLightPalette = baseLightPalette.copy(
    primaryBackground = Color(0xFFF0F3F3),
)

val purpleLightPalette = baseLightPalette.copy(
    primaryBackground = Color(0xFFC6B4E7),
    secondaryBackground = Color(0xFF845DC7),
    accentColor = Color(0xFF412575),
)

val yellowLightPalette = baseLightPalette.copy(
    primaryBackground = Color(0xFFCFC5A7),
    secondaryBackground = Color(0xFFB4932F),
    secondaryText = Color(0xFF915C0E),
    accentColor = Color(0xFF81520D),
)

val blueDarkPalette = baseDarkPalette.copy(
    primaryBackground = Color(0xFF1F3D55),
    secondaryBackground = Color(0xFF082136),
    primaryText = Color(0xFFBCDCF5),
    secondaryText = Color(0xFF83C2F3),
    accentColor = Color(0xFF2196F3),
)
val greyDarkPalette = baseDarkPalette.copy(
    primaryBackground = Color(0xFF424042),
    secondaryBackground = Color(0xFF242324),
    primaryText = Color(0xFFD6D3DB),
    secondaryText = Color(0xFF79787A),
    accentColor = Color(0xFF515188),
)

val purpleDarkPalette = baseDarkPalette

val yellowDarkPalette = baseDarkPalette.copy(
    primaryBackground = Color(0xFF6D5430),
    secondaryBackground = Color(0xFF3A2403),
    primaryText = Color(0xFFF1EFE9),
    secondaryText = Color(0xFFF1DCA0),
    accentColor = Color(0xFFFFC107),
)