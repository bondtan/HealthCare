package com.example.uiexample.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog
import com.example.uiexample.R
import com.example.uiexample.ui.theme.ExampleTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@OptIn(ExperimentalFoundationApi::class, ExperimentalPermissionsApi::class)
@Composable
fun <T> ScrollDropdownMenu(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String,
    dialogTitle: String,
    items: List<T>,
    value: String,
    selectedIndex: Int,
    onItemSelected: (index: Int, item: T) -> Unit,
    isPermissionGranted: MutableState<Boolean> = mutableStateOf(true),
    permissionState: PermissionState
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier.height(IntrinsicSize.Min)) {
        OutlinedTextField(
            label = {
                Text(
                    text = label,
                    style = ExampleTheme.typography.ordinaryBody,
                    fontWeight = FontWeight.Bold,
                    color = ExampleTheme.colors.primaryText
                )
            },
            value = value,
            enabled = enabled,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                    contentDescription = ""
                )
            },
            onValueChange = {},
            readOnly = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = ExampleTheme.colors.primaryText,
                trailingIconColor = ExampleTheme.colors.secondaryBackground,
                backgroundColor = ExampleTheme.colors.primaryBackground,
                focusedBorderColor = ExampleTheme.colors.accentColor,
                unfocusedBorderColor = ExampleTheme.colors.secondaryBackground,
            ),
            textStyle = ExampleTheme.typography.largeBody
        )
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = dimensionResource(id = R.dimen.padding_large))
                .clip(ExampleTheme.shape.cornerStyle)
                .clickable(enabled = enabled) {
                    when {
                        isPermissionGranted.value -> expanded = true
                        else -> permissionState.launchPermissionRequest()
                    }
                },
            color = Color.Transparent
        ) {}
        if (expanded) {
            Dialog(onDismissRequest = { expanded = false }) {
                Surface(
                    modifier = Modifier
                        .fillMaxHeight(0.9f)
                        .fillMaxWidth(0.9f),
                    shape = ExampleTheme.shape.cornerStyle,
                ) {
                    val listState = rememberLazyListState()
                    if (selectedIndex > -1) {
                        LaunchedEffect(selectedIndex) {
                            listState.scrollToItem(index = selectedIndex)
                        }
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = ExampleTheme.colors.primaryBackground)
                            .padding(dimensionResource(id = R.dimen.padding_large))
                    ) {
                        stickyHeader {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = ExampleTheme.colors.primaryBackground)
                                    .padding(bottom = dimensionResource(id = R.dimen.padding_ordinary)),
                                text = dialogTitle,
                                style = ExampleTheme.typography.subtitle,
                                color = ExampleTheme.colors.primaryText,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        itemsIndexed(items) { index, item ->
                            val selectedItem = index == selectedIndex
                            ScrollDropDownMenuItem(text = item.toString(),
                                selected = selectedItem,
                                enabled = true,
                                onClick = {
                                    onItemSelected(index, item)
                                    expanded = false
                                }
                            )
                            if (index < items.lastIndex) {
                                Divider(
                                    color = ExampleTheme.colors.secondaryBackground,
                                    thickness = dimensionResource(id = R.dimen.width_border_row)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ScrollDropDownMenuItem(
    text: String,
    selected: Boolean,
    enabled: Boolean,
    onClick: () -> Unit
) {
    val contentColor = when {
        !enabled -> ExampleTheme.colors.primaryBackground
        selected -> ExampleTheme.colors.secondaryBackground
        else -> ExampleTheme.colors.primaryBackground
    }
    Box(
        modifier = Modifier
            .background(contentColor)
            .clickable(
                enabled = enabled,
                onClick = onClick
            )
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_ordinary)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.padding_small),
                bottom = dimensionResource(id = R.dimen.padding_small)
            ),
            text = text,
            style = ExampleTheme.typography.largeBody,
            color = ExampleTheme.colors.primaryText
        )
    }
}

/*
@Preview(widthDp = 300, heightDp = 100, showBackground = true, showSystemUi = false)
@Composable
fun ScrollDropdownMenuPreview() {
    UIExampleApplicationTheme(
        colorStyle = ExampleColorStyle.YELLOW,
        textSize = ExampleSize.SMALL,
        shapeCorners = ExampleShapeStyle.ROUNDED,
        darkTheme = false
    ) {
        var selectedIndex by remember { mutableStateOf(-1) }
        val items = listOf("00:00", "01:00", "02:00", "03:00")

        ScrollDropdownMenu(
            dialogTitle = "Time",
            label = "Time",
            items = items,
            selectedIndex = selectedIndex,
            onItemSelected = { index, _ -> selectedIndex = index },
            value = items.getOrNull(selectedIndex).toString()
        )
    }
}

@Preview(widthDp = 100, heightDp = 40, showBackground = true, showSystemUi = false)
@Composable
fun ScrollDropDownMenuItemPreview() {
    UIExampleApplicationTheme(
        colorStyle = ExampleColorStyle.YELLOW,
        textSize = ExampleSize.SMALL,
        shapeCorners = ExampleShapeStyle.ROUNDED,
        darkTheme = false
    ) {
        ScrollDropDownMenuItem(
            text = "00:00",
            selected = false,
            enabled = false,
            onClick = {}
        )
    }
}
*/

