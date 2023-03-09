package com.example.uiexample.ui.screens.pulse.views

import android.graphics.Paint
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uiexample.R
import com.example.uiexample.ui.screens.pulse.PulseViewModel
import com.example.uiexample.ui.screens.pulse.models.PulseViewState

@Composable
fun PulseGraph(
    modifier: Modifier = Modifier,
    viewState: PulseViewState,
    viewModel: PulseViewModel,
    headerTextSize: TextUnit = 16.sp,
    fractionHeight: Float = 0.9f
) {
    val yOffsetGrid = arrayOf(0, 25, 50, 75, 100, 125, 150, 175, 200)

    val density = LocalDensity.current
    val paint = Paint().apply {
        color = android.graphics.Color.BLACK
        textAlign = Paint.Align.CENTER
        textSize = density.run { headerTextSize.toPx() }
    }
    val textPaint = remember(density) { paint }

    val xAxisSpaceDp = 70.dp // x step in Dp
    val xAxisSpace = density.run { xAxisSpaceDp.toPx() } // x step in Dp convert to Px
    val verticalStep = 25
    val xOffset = 70f

    Row(
        modifier = modifier
            .fillMaxHeight(fractionHeight)
            .padding(start = dimensionResource(id = R.dimen.padding_large))
    ) {
        val heightXHeader = density.run { headerTextSize.toPx() } * 2.5f
        val yTextOffset = density.run { dimensionResource(id = R.dimen.padding_large).toPx() }

        PulseHeader(
            yOffsetGrid = yOffsetGrid,
            heightXHeader = heightXHeader,
            textPaint = textPaint,
            yTextOffset = yTextOffset
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            PulseGrid(yOffsetGrid = yOffsetGrid, heightXHeader = heightXHeader)

            PulsePath(
                viewState = viewState,
                viewModel = viewModel,
                yOffsetGrid = yOffsetGrid,
                heightXHeader = heightXHeader,
                textPaint = textPaint,
                xAxisSpaceDp = xAxisSpaceDp,
                xAxisSpace = xAxisSpace,
                verticalStep = verticalStep,
                xOffset = xOffset
            )
            /*if (viewState.isSelectedItem) PulseItemInfo(
                viewState = viewState,
                heightXHeader = heightXHeader,
                yOffsetGrid = yOffsetGrid,
                textPaint = textPaint,
                xAxisSpaceDp = xAxisSpaceDp,
                xAxisSpace =  xAxisSpace,
                verticalStep = verticalStep,
                xOffset = xOffset
            )*/
        }
    }
}
