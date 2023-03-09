package com.example.uiexample.ui.screens.home.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.uiexample.R
import com.example.uiexample.common.dateFormatTwoLines
import com.example.uiexample.common.timeFormat
import com.example.uiexample.ui.screens.home.HomeViewModel
import com.example.uiexample.ui.screens.home.models.HealthUI
import com.example.uiexample.ui.screens.home.models.HomeEvent
import com.example.uiexample.ui.theme.ExampleTheme

@Composable
fun HealthCardItem(
    modifier: Modifier = Modifier,
    model: HealthUI,
    viewModel: HomeViewModel
) {
    var expandedMenu by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .padding(
                start = dimensionResource(id = R.dimen.padding_large),
                end = dimensionResource(id = R.dimen.padding_large),
            )
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_large) / 2),
        backgroundColor = ExampleTheme.colors.primaryBackground,
        border = BorderStroke(
            width = dimensionResource(id = R.dimen.width_border_row),
            color = ExampleTheme.colors.secondaryText.copy(alpha = 0.4f)
        ),
        shape = ExampleTheme.shape.cornerStyle,
        elevation = dimensionResource(id = R.dimen.elevation_card)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ExCardText(
                text = timeFormat(model.time),
                modifier = Modifier.weight(0.175f)
            )
            ExCardDivider()
            ExCardText(
                text = dateFormatTwoLines(model.date),
                modifier = Modifier.weight(0.225f)
            )
            ExCardDivider()
            ExCardText(
                text = model.systolicPressure.toString(),
                modifier = Modifier.weight(0.2f)
            )
            ExCardDivider()
            ExCardText(
                text = model.diastolicPressure.toString(),
                modifier = Modifier.weight(0.2f)
            )
            ExCardDivider()
            ExCardText(
                text = model.pulse.toString(),
                modifier = Modifier.weight(0.13f)
            )
            Box(
                modifier = Modifier
                    .weight(0.07f)
                    .align(Alignment.CenterVertically)
                    //.wrapContentSize(Alignment.TopStart)
            ) {
                IconButton(
                    //modifier = Modifier.align (Alignment.CenterVertically),
                    onClick = { expandedMenu = true }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = stringResource(R.string.menu_health_card_item)
                    )
                }
                DropdownMenu(
                    modifier = Modifier
                        .background(color = ExampleTheme.colors.secondaryBackground.copy(0.8f)),
                    expanded = expandedMenu,
                    onDismissRequest = { expandedMenu = false }) {
                    DropdownMenuItem(onClick = {
                        viewModel.obtainEvent(HomeEvent.DeleteSelectHealthCardItem(model))
                        expandedMenu = false
                    }) {
                        ExCardText(text = "Delete")
                    }
                }
            }
        }
    }
}

@Composable
fun StickyHeaderHealthCardItem(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = ExampleTheme.colors.secondaryBackground)
            .height(IntrinsicSize.Min)
            .padding(vertical = dimensionResource(id = R.dimen.padding_large)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ExCardText(
            text = stringResource(id = R.string.time).trimEnd(chars = charArrayOf(':')),
            modifier = Modifier.weight(0.175f)
        )
        ExCardDivider()
        ExCardText(
            text = stringResource(id = R.string.date).trimEnd(chars = charArrayOf(':')),
            modifier = Modifier.weight(0.225f)
        )
        ExCardDivider()
        ExCardText(
            text = stringResource(id = R.string.systolic_pressure),
            modifier = Modifier.weight(0.2f)
        )
        ExCardDivider()
        ExCardText(
            text = stringResource(id = R.string.diastolic_pressure),
            modifier = Modifier.weight(0.21f)
        )
        ExCardDivider()
        ExCardText(
            text = stringResource(id = R.string.pulse),
            modifier = Modifier.weight(0.19f)
        )

    }
}

@Composable
fun ExCardText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small)),
        text = text,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Medium,
        style = ExampleTheme.typography.largeBody,
        color = ExampleTheme.colors.primaryText
    )
}

@Composable
fun ExCardDivider() {
    Divider(
        modifier = Modifier
            .fillMaxHeight()
            .width(4.dp),
        color = ExampleTheme.colors.secondaryText.copy(alpha = 0.4f),
    )
}

/*
@Preview("health card")
@Composable
fun PreviewHealthCardItem() {
    UIExampleApplicationTheme {
        Column() {
            StickyHeaderHealthCardItem()
            HealthCardItem(
                model = HealthUI(
                    time = LocalTime.now(),
                    date = LocalDate.now(),
                    systolicPressure = 120,
                    diastolicPressure = 80,
                    pulse = 60
                ),
                viewModel = HomeViewModel()
            )
        }

    }
}
*/
