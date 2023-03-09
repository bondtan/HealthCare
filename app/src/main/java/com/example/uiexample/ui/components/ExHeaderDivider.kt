package com.example.uiexample.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.uiexample.R
import com.example.uiexample.ui.theme.ExampleTheme

@Composable
fun ExHeaderDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier
            .padding(
                start = dimensionResource(id = R.dimen.padding_large),
                end = dimensionResource(id = R.dimen.padding_large),
                bottom = dimensionResource(id = R.dimen.padding_large)
            ),
        thickness = 4.dp,
        color = ExampleTheme.colors.secondaryBackground.copy(alpha = 0.6f)
    )
}