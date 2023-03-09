package com.example.uiexample.ui.screens.pressure.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.uiexample.R
import com.example.uiexample.common.MAX_SYSTOLIC_PRESSURE
import com.example.uiexample.common.dateMonthFormat
import com.example.uiexample.common.timeFormat
import com.example.uiexample.ui.screens.pressure.models.PressureUi
import com.example.uiexample.ui.theme.ExampleTheme
import com.example.uiexample.ui.values.Dimensions

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PressureGraphCardItem(
    modifier: Modifier = Modifier,
    model: PressureUi,
    onCardItemClick: ((Int?) -> Unit)
) {
    // The value of the maximum pressure for calculating the coordinates
    // of the location of the rectangles on the canvas
    val heightPressure = MAX_SYSTOLIC_PRESSURE.toFloat()

    Card(
        onClick = { onCardItemClick(model.id) },
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.padding_large),
                end = dimensionResource(id = R.dimen.padding_large)
            ),
        backgroundColor = ExampleTheme.colors.primaryBackground,
        shape = ExampleTheme.shape.cornerStyle,
        elevation = 0.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Canvas(
                modifier = modifier
                    .height(dimensionResource(id = R.dimen.height_pressure_card_item))
                    .width(dimensionResource(id = R.dimen.width_pressure_card_item))
                    .background(
                        color = ExampleTheme.colors.secondaryBackground.copy(alpha = 0.4f),
                        shape = ExampleTheme.shape.cornerStyle
                    )
                    .border(
                        width = dimensionResource(id = R.dimen.width_border_row),
                        color = ExampleTheme.colors.secondaryText.copy(alpha = 0.4f),
                        shape = ExampleTheme.shape.cornerStyle
                    )
            )
            {
                drawRoundRect(
                    brush = Brush.verticalGradient(
                        listOf(Color(0xFFE42B2B), Color(0xFFE0D0CE))
                    ),
                    topLeft = Offset(
                        x = 0f,
                        y = size.height * (1f - model.systolicPressure.toFloat() / heightPressure)
                    ),
                    size = Size(
                        width = size.width,
                        height = size.height * (model.systolicPressure.toFloat() / heightPressure)
                    ),
                    cornerRadius = CornerRadius(
                        x = Dimensions.cornerOrdinary.toPx(),
                        y = Dimensions.cornerOrdinary.toPx()
                    )
                )
                drawRoundRect(
                    brush = Brush.verticalGradient(
                        listOf(Color(0xFF479FE4), Color(0xFFC2D1DD))
                    ),
                    topLeft = Offset(
                        x = 0f,
                        y = size.height * (1f - model.diastolicPressure.toFloat() / heightPressure)
                    ),
                    size = Size(
                        width = size.width,
                        height = size.height * (model.diastolicPressure.toFloat() / heightPressure)
                    ),
                    cornerRadius = CornerRadius(
                        x = Dimensions.cornerOrdinary.toPx(),
                        y = Dimensions.cornerOrdinary.toPx()
                    )
                )
            }
            Text(
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.padding_small),
                    bottom = dimensionResource(id = R.dimen.padding_small)
                ),
                text = "${timeFormat(model.time)}\n" +
                        dateMonthFormat(model.date),
                color = ExampleTheme.colors.primaryText,
                style = ExampleTheme.typography.ordinaryBody
            )
        }
    }
}