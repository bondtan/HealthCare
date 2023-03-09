package com.example.uiexample.ui.screens.pressure.views

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.uiexample.R
import com.example.uiexample.ui.theme.ExampleTheme

@Composable
fun PressureHeader(
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val paint = Paint().apply {
        color = android.graphics.Color.BLACK
        textAlign = Paint.Align.LEFT
        textSize = density.run { 14.sp.toPx() }
    }
    val textPaint = remember(density) { paint }
    Column(
        modifier = Modifier.background(color = ExampleTheme.colors.primaryBackground)

    ) {
        Canvas(
            modifier = modifier
                .width(dimensionResource(id = R.dimen.width_pressure_card_item))
                .height(dimensionResource(id = R.dimen.height_pressure_card_item))
        ) {
            val coefficientYOffset = arrayOf(
                0f to "200", 0.25f to "150", 0.5f to "100", 0.75f to "50", 1f to "0"
            )

            coefficientYOffset.forEach {
                drawLine(
                    color = Color.Gray,
                    start = Offset(0f, size.height * it.first),
                    end = Offset(size.width, size.height * it.first),
                )
                drawContext.canvas.nativeCanvas.drawText(
                    it.second,
                    10f,
                    if (it.second == "0") {
                        size.height * it.first - 10
                    } else {
                        size.height * it.first + 35
                    },
                    textPaint
                )
            }
        }
        Text(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_small)),
            text = stringResource(id = R.string.time_date),
            color = ExampleTheme.colors.primaryText,
            style = ExampleTheme.typography.ordinaryBody
        )
    }
}