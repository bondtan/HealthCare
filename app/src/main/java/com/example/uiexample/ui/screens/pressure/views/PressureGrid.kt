package com.example.uiexample.ui.screens.pressure.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.example.uiexample.R

@Composable
fun PressureGrid(
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.height_pressure_card_item))
    ) {

        val coefficientYOffset = arrayOf(0f, 0.25f, 0.5f, 0.75f, 1f)

        coefficientYOffset.forEach {
            drawLine(
                color = Color.Gray,
                start = Offset(0f, size.height * it),
                end = Offset(size.width, size.height * it),
            )
        }
    }
}