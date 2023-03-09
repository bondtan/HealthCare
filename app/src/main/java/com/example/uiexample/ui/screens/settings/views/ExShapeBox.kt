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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.uiexample.ui.theme.ExampleTheme

@Composable
fun ExShapeBox(
    modifier: Modifier = Modifier,
    shape: Shape,
    enabled: Boolean,
    text: String,
    clickShapeBox: () -> Unit
) {
    Box(
        modifier = modifier
            .size(width = 150.dp, height = 50.dp)
            .background(
                color = ExampleTheme.colors.primaryBackground,
                shape = shape
            )
            .border(
                width = if (enabled) 4.dp else 1.dp,
                color = ExampleTheme.colors.secondaryBackground,
                shape = shape
            )
            .clip(shape)
            .clickable(onClick = clickShapeBox),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = ExampleTheme.colors.primaryText,
            style = ExampleTheme.typography.largeBody,
            fontWeight = FontWeight.Bold
        )
    }
}
