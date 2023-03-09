package com.example.uiexample.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val smallTypography = ExampleTypography(
    header1 = TextStyle(
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold
    ),
    header = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold
    ),
    subtitle = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium
    ),
    largeBody = TextStyle(fontSize = 16.sp),
    ordinaryBody = TextStyle(fontSize = 14.sp),
    smallBody = TextStyle(fontSize = 12.sp),
    bottomNav = TextStyle(fontSize = 12.sp)
)

val bigTypography = ExampleTypography(
    header1 = TextStyle(
        fontSize = 50.sp,
        fontWeight = FontWeight.Bold
    ),
    header = TextStyle(
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold
    ),
    subtitle = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Medium
    ),
    largeBody = TextStyle(fontSize = 18.sp),
    ordinaryBody = TextStyle(fontSize = 16.sp),
    smallBody = TextStyle(fontSize = 14.sp),
    bottomNav = TextStyle(fontSize = 14.sp)
)
