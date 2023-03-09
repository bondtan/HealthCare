package com.example.uiexample.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.uiexample.R
import com.example.uiexample.ui.theme.ExampleTheme

@Composable
fun ExBorderRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceEvenly ,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    content: @Composable (RowScope.() -> Unit)
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = dimensionResource(id = R.dimen.width_border_row),
                color = ExampleTheme.colors.secondaryText.copy(alpha = 0.4f),
                shape = ExampleTheme.shape.cornerStyle
            )
            .padding(
                all = dimensionResource(id = R.dimen.padding_ordinary)
            ),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
        content = content
    )
}