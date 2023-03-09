package com.example.uiexample.ui.screens.pulse.views

import android.graphics.Path
import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import com.example.uiexample.common.dateMonthFormat
import com.example.uiexample.common.timeFormat
import com.example.uiexample.ui.screens.pulse.PulseViewModel
import com.example.uiexample.ui.screens.pulse.models.PulseEvent
import com.example.uiexample.ui.screens.pulse.models.PulseViewState

@Composable
fun PulsePath(
    modifier: Modifier = Modifier,
    viewState: PulseViewState,
    viewModel: PulseViewModel,
    yOffsetGrid: Array<Int>,
    heightXHeader: Float,
    textPaint: android.graphics.Paint,
    xAxisSpaceDp: Dp,
    xAxisSpace: Float,
    verticalStep: Int,
    xOffset: Float
) {
    val controlPoints1 = mutableListOf<PointF>()
    val controlPoints2 = mutableListOf<PointF>()
    val coordinates = mutableListOf<PointF>()
    val clickRect = mutableListOf<Rect>()

    Box(modifier = modifier.horizontalScroll(rememberScrollState())) {
        Canvas(
            modifier = Modifier
                .width(xAxisSpaceDp * (viewState.pulseList.size))
                .fillMaxHeight()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { tapOffset ->
                        var index = 0
                        for (rect in clickRect) {
                            if (rect.contains(tapOffset)) {
                                val indexItem = clickRect.indexOf(rect)
                                viewModel.obtainEvent(PulseEvent.SelectPulseItem(indexItem))
                                break
                            }
                            index++
                        }
                    })
                }
        ) {
            val yAxisSpace = (size.height - heightXHeader) / (yOffsetGrid.size - 1)

            viewState.pulseList.forEachIndexed { index, pulseUi ->
                val x1 = xAxisSpace * index + xOffset
                val y1 = size.height - (yAxisSpace * (pulseUi.pulse / verticalStep.toFloat())) -
                        heightXHeader
                coordinates.add(PointF(x1, y1))
                clickRect.add(
                    Rect(
                        left = x1 - xAxisSpace / 2 + xOffset,
                        top = size.height - (yAxisSpace * (pulseUi.pulse / verticalStep.toFloat())) -
                                heightXHeader * 3,
                        right = x1 + xAxisSpace / 2 + xOffset,
                        bottom = size.height - heightXHeader
                    )
                )
                drawCircle(
                    color = Color.DarkGray,
                    radius = 15f,
                    center = Offset(x1, y1)
                )
            }

            for (i in 1 until coordinates.size) {
                controlPoints1.add(
                    PointF(
                        (coordinates[i].x + coordinates[i - 1].x) / 2,
                        coordinates[i - 1].y
                    )
                )
                controlPoints2.add(
                    PointF(
                        (coordinates[i].x + coordinates[i - 1].x) / 2,
                        coordinates[i].y
                    )
                )
            }
            val stroke = androidx.compose.ui.graphics.Path().apply {
                reset()
                moveTo(coordinates.first().x, coordinates.first().y)
                for (i in 0 until coordinates.size - 1) {
                    cubicTo(
                        controlPoints1[i].x, controlPoints1[i].y,
                        controlPoints2[i].x, controlPoints2[i].y,
                        coordinates[i + 1].x, coordinates[i + 1].y
                    )
                }
            }
            val fillPath = Path(stroke.asAndroidPath())
                .asComposePath()
                .apply {
                    lineTo(
                        xAxisSpace * (viewState.pulseList.size - 1) + xOffset,
                        size.height - heightXHeader
                    )
                    lineTo(0f + xOffset, size.height - heightXHeader)
                    close()
                }
            drawPath(
                path = fillPath,
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF2799F1), Color(0xFFC2D1DD)),
                    endY = size.height - heightXHeader
                ),
            )
            drawPath(
                stroke,
                alpha = 0.6f,
                color = Color.Black,
                style = Stroke(
                    width = 8f,
                    cap = StrokeCap.Round
                )
            )
            viewState.pulseList.forEachIndexed { index, pulseUi ->
                drawContext.canvas.nativeCanvas.drawText(
                    timeFormat(pulseUi.time),
                    xAxisSpace * (index) + xOffset,
                    size.height - heightXHeader / 2,
                    textPaint
                )
                drawContext.canvas.nativeCanvas.drawText(
                    dateMonthFormat(pulseUi.date),
                    xAxisSpace * (index) + xOffset,
                    size.height,
                    textPaint
                )
                drawLine(
                    color = Color.Red,
                    start = Offset(xAxisSpace * (index) + xOffset, 0f),
                    end = Offset(
                        xAxisSpace * (index) + xOffset,
                        size.height - heightXHeader
                    ),
                    strokeWidth = 2f,
                )
            }
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
            if (viewState.isSelectedItem)
                drawRoundRect(
                    color = Color(0xFFC2D1DD),
                    topLeft = Offset(
                        x = xAxisSpace * (viewState.pulseList.indexOf(viewState.selectPulseItem)) + xOffset - 50f,
                        y = size.height - (yAxisSpace * (viewState.selectPulseItem?.pulse?.div(
                            verticalStep.toFloat()
                        ) ?: 0f)) - heightXHeader * 1.6f - 50f
                    ),
                    size = Size(width = 100f, height = 80f),
                    cornerRadius = CornerRadius(x = 20f, y = 20f)
                )

            drawContext.canvas.nativeCanvas.drawText(
                "${viewState.selectPulseItem?.pulse}",
                xAxisSpace * (viewState.pulseList.indexOf(viewState.selectPulseItem)) + xOffset,
                size.height - (yAxisSpace * (viewState.selectPulseItem?.pulse?.div(verticalStep.toFloat())
                    ?: 0f)) - heightXHeader * 1.6f,
                textPaint
            )
            drawCircle(
                color = Color.Red,
                radius = 20f,
                center = Offset(
                    x = xAxisSpace * (viewState.pulseList.indexOf(viewState.selectPulseItem)) + xOffset,
                    y = size.height - (yAxisSpace * (viewState.selectPulseItem?.pulse?.div(
                        verticalStep.toFloat()
                    ) ?: 0f)) - heightXHeader
                )
            )
        }
    }
}

