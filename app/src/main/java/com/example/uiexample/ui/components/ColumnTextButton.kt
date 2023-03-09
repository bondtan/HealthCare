package com.example.uiexample.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.uiexample.ui.theme.ExampleTheme

@Composable
fun ColumnTextButton(
    modifier: Modifier = Modifier,
    textTitle: String,
    onButtonClick: () -> Unit,
    buttonTitle: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = textTitle,
            fontWeight = FontWeight.Bold,
            style = ExampleTheme.typography.largeBody,
            color = ExampleTheme.colors.primaryText
        )
        ExButton(
            onButtonClick = onButtonClick ,
            buttonTitle = buttonTitle
        )
    }
}