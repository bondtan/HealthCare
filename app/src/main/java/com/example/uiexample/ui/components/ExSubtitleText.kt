package com.example.uiexample.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.example.uiexample.R
import com.example.uiexample.ui.theme.ExampleTheme

@Composable
fun ExSubtitleText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier.padding(
            top = dimensionResource(id = R.dimen.padding_ordinary),
            start = dimensionResource(id = R.dimen.padding_large) * 3,
            end = dimensionResource(id = R.dimen.padding_large)
        ),
        text = text,
        style = ExampleTheme.typography.subtitle,
        fontWeight = FontWeight.Bold,
        color = ExampleTheme.colors.primaryText
    )
}