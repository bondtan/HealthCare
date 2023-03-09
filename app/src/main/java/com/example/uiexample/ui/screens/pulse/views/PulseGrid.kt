package com.example.uiexample.ui.screens.pulse.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun PulseGrid(
    yOffsetGrid: Array<Int>,
    heightXHeader: Float
) {
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val yAxisSpace = (size.height - heightXHeader) / (yOffsetGrid.size - 1)

        yOffsetGrid.forEach {
            drawLine(
                color = Color.Gray,
                start = Offset(
                    0f,
                    size.height - heightXHeader - yAxisSpace * yOffsetGrid.indexOf(it)
                ),
                end = Offset(
                    size.width,
                    size.height - heightXHeader - yAxisSpace * yOffsetGrid.indexOf(it)
                ),
                strokeWidth = 4f
            )
        }
    }
}