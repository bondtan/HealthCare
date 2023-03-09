package com.example.uiexample.ui.screens.settings.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.uiexample.R
import com.example.uiexample.ui.theme.ExampleTheme

@Composable
fun ExColorBox(
    modifier: Modifier = Modifier,
    primaryColor: Color,
    secondaryColor: Color,
    accentColor: Color,
    enabled: Boolean,
    clickColorBox: () -> Unit
) {
    val boxWidth = 150.dp
    val boxHeight = 50.dp

    Box(
        modifier = modifier
            .size(width = boxWidth, height = boxHeight)
            .background(
                color = accentColor,
                shape = ExampleTheme.shape.cornerStyle
            )
            .border(
                width = if (enabled) 4.dp else 1.dp,
                color = ExampleTheme.colors.secondaryBackground,
                shape = ExampleTheme.shape.cornerStyle
            )
            .clip(ExampleTheme.shape.cornerStyle)
            .clickable(onClick = clickColorBox),
        contentAlignment = Alignment.Center
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .width(boxWidth / 3)
                    .fillMaxHeight()
                    .background(color = primaryColor)
            )
            Box(
                modifier = Modifier
                    .width(boxWidth / 3)
                    .fillMaxHeight()
                    .background(color = secondaryColor)
            )
            Box(
                modifier = Modifier
                    .width(boxWidth / 3)
                    .fillMaxHeight()
                    .background(color = accentColor)
            )
        }
        Icon(
            modifier = Modifier
                .size(
                    if (enabled) dimensionResource(id = R.dimen.header_icon) else 0.dp
                )
                .padding(end = dimensionResource(id = R.dimen.padding_ordinary)),
            tint = ExampleTheme.colors.accentColor,
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_check_circle),
            contentDescription = stringResource(R.string.checked)
        )
    }
}