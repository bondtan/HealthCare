package com.example.uiexample.ui.screens.pressure.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.uiexample.R
import com.example.uiexample.common.dateFormatSingleLine
import com.example.uiexample.common.timeFormat
import com.example.uiexample.ui.screens.pressure.models.PressureViewState
import com.example.uiexample.ui.theme.ExampleTheme

@Composable
fun PressureItem(
    viewState: PressureViewState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(id = R.dimen.padding_large),
                start = dimensionResource(id = R.dimen.padding_large),
                bottom = dimensionResource(id = R.dimen.padding_large)
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PressureItemIcon(
            imageVector = Icons.Default.Schedule,
            contentDescription = stringResource(R.string.time)
        )
        PressureItemText(text = timeFormat(viewState.selectPressureItem.time))
        PressureItemIcon(
            imageVector = Icons.Default.CalendarMonth,
            contentDescription = stringResource(R.string.date)
        )
        PressureItemText(text = dateFormatSingleLine(viewState.selectPressureItem.date))
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PressureItemIcon(
            modifier = Modifier.size(dimensionResource(id = R.dimen.pressure_icon)),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_blood_pressure_monitor),
            contentDescription = stringResource(R.string.blood_pressure)
        )
        PressureItemText(
            text = "${viewState.selectPressureItem.systolicPressure} / " +
                    "${viewState.selectPressureItem.diastolicPressure} mmHg"
        )
    }
}

@Composable
fun PressureItemText(text: String) {
    Text(
        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.padding_large)),
        text = text,
        style = ExampleTheme.typography.subtitle,
        color = ExampleTheme.colors.primaryText
    )
}

@Composable
fun PressureItemIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String
) {
    Icon(
        modifier = modifier
            .size(dimensionResource(id = R.dimen.large_icon))
            .padding(end = dimensionResource(id = R.dimen.padding_large) * 2),
        tint = ExampleTheme.colors.secondaryText,
        imageVector = imageVector,
        contentDescription = contentDescription
    )
}