package com.example.uiexample.ui.screens.pulse.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp

@Composable
fun PulseHeader(
    modifier: Modifier = Modifier,
    yOffsetGrid: Array<Int>,
    heightXHeader: Float,
    textPaint: android.graphics.Paint,
    yTextOffset: Float
) {

    Canvas(
        modifier = modifier
            .width(30.dp)
            .fillMaxHeight()
    ) {
        val yAxisSpace = (size.height - heightXHeader) / (yOffsetGrid.size - 1)
        yOffsetGrid.forEachIndexed { index, i ->
            drawContext.canvas.nativeCanvas.drawText(
                "$i",
                yTextOffset,
                when (index) {
                    0 -> size.height - heightXHeader - 15f
                    else -> size.height - yAxisSpace * index - heightXHeader + 50f
                },
                textPaint
            )
            drawLine(
                color = Color.Gray,
                start = Offset(
                    0f, size.height - heightXHeader -
                            yAxisSpace * yOffsetGrid.indexOf(i)
                ),
                end = Offset(
                    size.width, size.height - heightXHeader -
                            yAxisSpace * yOffsetGrid.indexOf(i)
                ),
                strokeWidth = 4f
            )
        }
    }
}