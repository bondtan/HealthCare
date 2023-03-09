package com.example.uiexample.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import com.example.uiexample.R
import com.example.uiexample.ui.theme.ExampleTheme

@Composable
fun ExHeaderIconText (
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String,
    title: String,

) {
    Row (modifier = modifier){
        Icon(
            modifier = Modifier.size(dimensionResource(id = R.dimen.header_icon)),
            tint = ExampleTheme.colors.secondaryText,
            imageVector = imageVector,
            contentDescription = contentDescription
        )
        Text(
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_ordinary)),
            text = title,
            style = ExampleTheme.typography.header,
            color = ExampleTheme.colors.primaryText
        )
    }
}