package com.example.uiexample.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.example.uiexample.R
import com.example.uiexample.ui.theme.ExampleTheme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import java.time.LocalDate

@Composable
fun DatePickerDialog(
    dateDialogState: MaterialDialogState,
    positiveButtonClick: () -> Unit,
    pickedDate: (LocalDate) -> Unit
) {
    MaterialDialog(
        backgroundColor = ExampleTheme.colors.primaryBackground,
        dialogState = dateDialogState,
        buttons = {
            positiveButton(
                text = stringResource(R.string.ok),
                textStyle = TextStyle(
                    color = ExampleTheme.colors.secondaryText
                ),
                onClick = positiveButtonClick
            )
            negativeButton(
                text = stringResource(R.string.cancel),
                textStyle = TextStyle(
                    color = ExampleTheme.colors.secondaryText
                )
            )
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            colors = DatePickerDefaults.colors(
                headerBackgroundColor = ExampleTheme.colors.secondaryBackground,
                dateActiveBackgroundColor = ExampleTheme.colors.accentColor
            ),
            onDateChange = pickedDate
        )
    }
}