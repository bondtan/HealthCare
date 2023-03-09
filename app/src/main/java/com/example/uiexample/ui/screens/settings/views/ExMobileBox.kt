package com.example.uiexample.ui.screens.settings.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import com.example.uiexample.ui.theme.ExampleTheme

@Composable
fun ExMobileBox(
    modifier: Modifier = Modifier
) {

    val mobileColor = ExampleTheme.colors.secondaryBackground
    val screenColor = ExampleTheme.colors.primaryBackground
    val fabColor = ExampleTheme.colors.accentColor

    Canvas(
        modifier = modifier
            .size(width = 160.dp, height = 250.dp)
            .background(ExampleTheme.colors.primaryBackground)
    ) {
        drawRoundRect(
            color = mobileColor,
            size = Size(width = size.width, height = size.height),
            cornerRadius = CornerRadius(x = 40f, y = 40f)
        )
        drawRoundRect(
            color = screenColor,
            topLeft = Offset(x = 20f, y = 30f),
            size = Size(width = size.width - 40f, height = size.height - 80f),
            cornerRadius = CornerRadius(x = 30f, y = 30f)
        )
        drawCircle(
            color = mobileColor,
            radius = 50f,
            center = Offset(x = 100f, y = 110f)
        )
         drawRoundRect(
             color = mobileColor,
             topLeft = Offset(x = 170f, y = 65f),
            size =  Size(width = 220f, height = 90f),
             cornerRadius = CornerRadius(x = 15f, y = 15f)
         )
            drawRoundRect(
                color = mobileColor,
                topLeft = Offset(x = 45f, y = 190f),
                size =  Size(width = 350f, height = 110f),
                cornerRadius = CornerRadius(x = 15f, y = 15f)
            )
        drawRoundRect(
            color = mobileColor,
            topLeft = Offset(x = 45f, y = 335f),
            size =  Size(width = 350f, height = 110f),
            cornerRadius = CornerRadius(x = 15f, y = 15f)
        )
        drawRoundRect(
            color = mobileColor,
            topLeft = Offset(x = 45f, y = 480f),
            size =  Size(width = 350f, height = 110f),
            cornerRadius = CornerRadius(x = 15f, y = 15f)
        )
        drawCircle(
            color = fabColor,
            radius = 30f,
            center = Offset(x = size.width - 70f, y = size.height - 100f)
        )
    }
}