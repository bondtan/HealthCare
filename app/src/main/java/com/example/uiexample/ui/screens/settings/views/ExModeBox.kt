package com.example.uiexample.ui.screens.settings.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
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
fun ExModeBox(
    modifier: Modifier = Modifier,
    color: Color,
    enabled: Boolean,
    clickModeBox: () -> Unit,
    text: String,
    textColor: Color
) {
    Box(
        modifier = modifier
            .size(width = 150.dp, height = 50.dp)
            .background(
                color = color,
                shape = ExampleTheme.shape.cornerStyle
            )
            .border(
                width = if (enabled) 4.dp else 1.dp,
                color = ExampleTheme.colors.secondaryBackground,
                shape = ExampleTheme.shape.cornerStyle
            )
            .clip(ExampleTheme.shape.cornerStyle)
            .clickable(onClick = clickModeBox)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
               // .padding(dimensionResource(id = R.dimen.padding_small)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
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
            Text(
                text = text,
                style = ExampleTheme.typography.largeBody,
                color = textColor
            )
        }
    }
}