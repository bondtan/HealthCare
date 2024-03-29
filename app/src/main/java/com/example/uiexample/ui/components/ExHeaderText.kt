package com.example.uiexample.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.uiexample.ui.theme.ExampleTheme

@Composable
fun ExHeaderText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = text,
        style = ExampleTheme.typography.header,
        textAlign = TextAlign.Center,
        color = ExampleTheme.colors.primaryText
    )
}