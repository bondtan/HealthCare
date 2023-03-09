package com.example.uiexample.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun UIExampleApplicationTheme(
    colorStyle: ExampleColorStyle,
    textSize: ExampleSize,
    shapeCorners: ExampleShapeStyle,
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colors = when (darkTheme) {
       true -> {
           when (colorStyle){
               ExampleColorStyle.BLUE -> blueDarkPalette
               ExampleColorStyle.GRAY -> greyDarkPalette
               ExampleColorStyle.PURPLE -> purpleDarkPalette
               ExampleColorStyle.YELLOW -> yellowDarkPalette
           }
        }
        false -> {
            when (colorStyle){
                ExampleColorStyle.BLUE -> blueLightPalette
                ExampleColorStyle.GRAY -> greyLightPalette
                ExampleColorStyle.PURPLE -> purpleLightPalette
                ExampleColorStyle.YELLOW -> yellowLightPalette
            }
        }
    }

    val typography = when (textSize) {
        ExampleSize.SMALL -> smallTypography
        ExampleSize.BIG -> bigTypography
    }

    val shapes = when (shapeCorners) {
        ExampleShapeStyle.ROUNDED -> shapeRounded
        ExampleShapeStyle.SQUIRE -> shapeSquire
    }

    CompositionLocalProvider(
        LocalExampleColors provides colors,
        LocalExampleTypography provides typography,
        LocalExampleShape provides shapes,
        content = content
    )
}