package com.example.uiexample.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.example.uiexample.ui.theme.ExampleTheme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import java.time.LocalTime

@Composable
fun TimePickerDialog(
    timeDialogState: MaterialDialogState,
    positiveButtonClick: () -> Unit,
    pickedTime: (LocalTime) -> Unit
    ) {
    MaterialDialog(
        backgroundColor = ExampleTheme.colors.primaryBackground,
        dialogState = timeDialogState,
        buttons = {
            positiveButton(
                text = "Ok",
                textStyle = TextStyle(
                    color = ExampleTheme.colors.secondaryText
                ),
                onClick = positiveButtonClick
            )
            negativeButton(
                text = "Cancel",
                textStyle = TextStyle(
                    color = ExampleTheme.colors.secondaryText
                )
            )
        }
    ) {
        timepicker(
            initialTime = LocalTime.now(),
            colors = TimePickerDefaults.colors(
                inactiveBackgroundColor = ExampleTheme.colors.secondaryBackground,
                activeBackgroundColor = ExampleTheme.colors.accentColor,
                selectorColor = ExampleTheme.colors.accentColor,
            ),
            is24HourClock = true,
            onTimeChange = pickedTime
        )
    }
}