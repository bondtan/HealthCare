package com.example.uiexample.ui.screens.settings.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.uiexample.R
import com.example.uiexample.ui.theme.ExampleTheme

@Composable
fun ExTextBox(
    modifier: Modifier = Modifier,
    style: TextStyle,
    enabled: Boolean,
    clickTextBox: () -> Unit
) {
    Box(
        modifier = modifier
            .size(width = 150.dp, height = 50.dp)
            .background(
                color = ExampleTheme.colors.primaryBackground,
                shape = ExampleTheme.shape.cornerStyle
            )
            .border(
                width = if (enabled) 4.dp else 1.dp,
                color = ExampleTheme.colors.secondaryBackground,
                shape = ExampleTheme.shape.cornerStyle
            )
            .clip(ExampleTheme.shape.cornerStyle)
            .clickable(onClick = clickTextBox),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.example_text),
            color = ExampleTheme.colors.primaryText,
            style = style,
            fontWeight = FontWeight.Bold
        )
    }
}