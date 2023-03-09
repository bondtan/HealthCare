package com.example.uiexample.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import com.example.uiexample.ui.theme.ExampleTheme

@Composable
fun ExButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    onButtonClick: () -> Unit,
    buttonTitle: String
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        border = border,
        onClick = onButtonClick ,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ExampleTheme.colors.secondaryBackground,
            contentColor = ExampleTheme.colors.primaryText,
            disabledBackgroundColor = ExampleTheme.colors.secondaryBackground.copy(alpha = 0.5f),
            disabledContentColor = ExampleTheme.colors.primaryText.copy(alpha = 0.5f)
        )
    ) {
        Text(
            text = buttonTitle,
            color = ExampleTheme.colors.primaryText,
            fontWeight = FontWeight.Medium,
            style = ExampleTheme.typography.largeBody)
    }
}