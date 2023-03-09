package com.example.uiexample.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.uiexample.ui.theme.ExampleTheme

@Composable
fun ExNumberTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit,
    isError: Boolean = false
) {
    TextField(
        modifier = modifier,
            //.fillMaxWidth(0.8f),
        value = value,
        enabled = enabled,
        placeholder = {
            Text(
                modifier = Modifier.padding(start = 0.dp),
                text = placeholder,
                color = ExampleTheme.colors.primaryText.copy(0.4f),
                style = ExampleTheme.typography.largeBody,
            )
        },
        textStyle =  ExampleTheme.typography.largeBody,
        shape = ExampleTheme.shape.cornerStyle,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = ExampleTheme.colors.secondaryBackground,
            textColor = ExampleTheme.colors.primaryText,
            disabledIndicatorColor = ExampleTheme.colors.primaryBackground,
            errorIndicatorColor = ExampleTheme.colors.errorColor,
            focusedIndicatorColor = ExampleTheme.colors.primaryBackground,
            unfocusedIndicatorColor = ExampleTheme.colors.primaryBackground
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        onValueChange = onValueChange,
        isError = isError
    )
}

/*
@Composable
@Preview(showBackground = true)
fun ExTextFieldPreview() {
    UIExampleApplicationTheme() {
        Box(
            modifier = Modifier.size(200.dp),
            //color = ExampleTheme.colors.primaryBackground
        ) {
            ExNumberTextField(
                value = "",
                placeholder = "100",
                onValueChange = {

                }
            )
        }
    }
}*/
