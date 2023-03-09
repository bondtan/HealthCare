package com.example.uiexample.ui.screens.home.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.uiexample.R
import com.example.uiexample.ui.components.*
import com.example.uiexample.ui.screens.home.HomeViewModel
import com.example.uiexample.ui.screens.home.models.HomeEvent
import com.example.uiexample.ui.screens.home.models.HomeViewState
import com.example.uiexample.ui.theme.ExampleTheme
import com.vanpra.composematerialdialogs.MaterialDialogState
import java.util.*

@Composable
fun CurrentMeasureCard(
    viewState: HomeViewState,
    homeViewModel: HomeViewModel,
    dateDialogState: MaterialDialogState,
    timeDialogState: MaterialDialogState,
    formattedTime: String,
    formattedDate: String
) {
    TimePickerDialog(
        timeDialogState = timeDialogState,
        positiveButtonClick = {},
        pickedTime = {
            homeViewModel.obtainEvent(HomeEvent.TimeChanged(it))
        })

    DatePickerDialog(
        dateDialogState = dateDialogState,
        positiveButtonClick = {},
        pickedDate = {
            homeViewModel.obtainEvent(HomeEvent.DataChanged(it))
        })

    Column(
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.padding_large),
                end = dimensionResource(id = R.dimen.padding_large),
            )
            .background(
                color = ExampleTheme.colors.primaryBackground,
                shape = ExampleTheme.shape.cornerStyle
            )
            .padding(dimensionResource(id = R.dimen.padding_large))

    ) {
        ExHeaderIconText(imageVector = Icons.Default.MonitorHeart,
            contentDescription = stringResource(R.string.current_measure),
            title = stringResource(R.string.current_measure))

        ExBorderRow {
            Icon(
                modifier = Modifier.size(dimensionResource(id = R.dimen.large_icon)),
                tint = ExampleTheme.colors.secondaryText,
                imageVector = Icons.Default.Schedule,
                contentDescription = stringResource(R.string.time)

            )
            ColumnTextButton(
                textTitle = stringResource(R.string.time),
                onButtonClick = { timeDialogState.show() },
                buttonTitle = formattedTime
            )
            Icon(
                modifier = Modifier.size(dimensionResource(id = R.dimen.large_icon)),
                tint = ExampleTheme.colors.secondaryText,
                imageVector = Icons.Default.CalendarMonth,
                contentDescription = stringResource(R.string.date)
            )
            ColumnTextButton(
                textTitle = stringResource(R.string.date),
                onButtonClick = { dateDialogState.show() },
                buttonTitle = formattedDate
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_ordinary)))

        ExBorderColumn {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(0.3f, true)
                        .padding(start = dimensionResource(id = R.dimen.padding_ordinary)),
                    text = stringResource(R.string.blood_pressure),
                    fontWeight = FontWeight.Bold,
                    style = ExampleTheme.typography.largeBody,
                    softWrap = true,
                    maxLines = 2,
                    color = ExampleTheme.colors.primaryText
                )
                Box(
                    modifier = Modifier.weight(0.2f, true),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.systolic_pressure),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            style = ExampleTheme.typography.largeBody,
                            maxLines = 1,
                            color = ExampleTheme.colors.primaryText
                        )
                        ExNumberTextField(
                            value = if (viewState.currentSystolicPressure == 0) "" else
                                viewState.currentSystolicPressure.toString(),
                            placeholder = "120",
                            onValueChange = {
                                if (it.isEmpty()) {
                                    homeViewModel.obtainEvent(HomeEvent.SystolicPressureChanged("0"))
                                } else {
                                    homeViewModel.obtainEvent(HomeEvent.SystolicPressureChanged(it))
                                }
                            },
                            isError = !viewState.isSystolicPressureValid
                        )
                    }
                }

                Text(
                    modifier = Modifier.weight(0.05f, true),
                    text = "/",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    style = ExampleTheme.typography.header,
                    color = ExampleTheme.colors.primaryText
                )
                Box(
                    modifier = Modifier.weight(0.2f, true),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.diastolic_pressure),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            style = ExampleTheme.typography.largeBody,
                            maxLines = 1,
                            color = ExampleTheme.colors.primaryText
                        )
                        ExNumberTextField(
                            value = if (viewState.currentDiastolicPressure == 0) "" else
                                viewState.currentDiastolicPressure.toString(),
                            placeholder = "100",
                            onValueChange = {
                                if (it.isEmpty()) {
                                    homeViewModel.obtainEvent(HomeEvent.DiastolicPressureChanged("0"))
                                } else {
                                    homeViewModel.obtainEvent(HomeEvent.DiastolicPressureChanged(it))
                                }
                            },
                            isError = !viewState.isDiastolicPressureValid
                        )
                    }
                }
                Text(
                    modifier = Modifier.weight(0.15f, true),
                    text = stringResource(R.string.mmhg),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    style = ExampleTheme.typography.largeBody,
                    color = ExampleTheme.colors.primaryText
                )
            }
            if (!viewState.isDiastolicPressureValid || !viewState.isSystolicPressureValid) {
                Text(
                    text = "* ".uppercase(Locale.getDefault()) +
                            stringResource(id = R.string.error_blood_pressure),
                    color = ExampleTheme.colors.errorColor,
                    style = ExampleTheme.typography.smallBody
                )
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_ordinary)))

        ExBorderColumn {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(0.35f, true)
                        .padding(start = dimensionResource(id = R.dimen.padding_ordinary)),
                    text = stringResource(R.string.pulse),
                    fontWeight = FontWeight.Bold,
                    style = ExampleTheme.typography.largeBody,
                    softWrap = true,
                    maxLines = 2,
                    color = ExampleTheme.colors.primaryText
                )
                Box(
                    modifier = Modifier.weight(0.2f, true)
                ) {
                    ExNumberTextField(
                        value = if (viewState.currentPulse == 0) "" else
                            viewState.currentPulse.toString(),
                        placeholder = "100",
                        onValueChange = {
                            if (it.isEmpty()) {
                                homeViewModel.obtainEvent(HomeEvent.PulseChanged("0"))
                            } else {
                                homeViewModel.obtainEvent(HomeEvent.PulseChanged(it))
                            }
                        },
                        isError = !viewState.isPulseValid
                    )
                }

                Text(
                    modifier = Modifier
                        .weight(0.45f, true)
                        .padding(end = dimensionResource(id = R.dimen.padding_ordinary)),
                    text = stringResource(R.string.bpm),
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Bold,
                    style = ExampleTheme.typography.largeBody,
                    softWrap = true,
                    maxLines = 2,
                    color = ExampleTheme.colors.primaryText
                )
            }
            if (!viewState.isPulseValid) {
                Text(
                    text = "* ".uppercase(Locale.getDefault()) +
                            stringResource(id = R.string.error_pulse),
                    color = ExampleTheme.colors.errorColor,
                    style = ExampleTheme.typography.smallBody
                )
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_large)))

        ExButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = viewState.isEnableSaveButton,
            shape = ExampleTheme.shape.cornerStyle,
            border = BorderStroke(
                width = dimensionResource(id = R.dimen.width_border_row),
                color = ExampleTheme.colors.secondaryText.copy(alpha = 0.4f)
            ),
            onButtonClick = { homeViewModel.obtainEvent(HomeEvent.SaveClicked) },
            buttonTitle = stringResource(R.string.button_save).uppercase(Locale.getDefault())
        )
    }
}
