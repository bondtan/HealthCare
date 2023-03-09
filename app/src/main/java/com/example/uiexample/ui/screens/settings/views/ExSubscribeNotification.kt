package com.example.uiexample.ui.screens.settings.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.uiexample.R
import com.example.uiexample.common.openNotificationsSettings
import com.example.uiexample.ui.components.ExHeaderDivider
import com.example.uiexample.ui.components.ScrollDropdownMenu
import com.example.uiexample.ui.screens.settings.SettingsViewModel
import com.example.uiexample.ui.screens.settings.models.SettingsEvent
import com.example.uiexample.ui.screens.settings.models.SettingsViewState
import com.example.uiexample.ui.theme.ExampleTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ExSubscribeNotification(
    modifier: Modifier = Modifier,
    permissionState: PermissionState,
    settingsViewModel: SettingsViewModel,
    viewState: SettingsViewState
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { openNotificationsSettings(context) },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.padding_ordinary),
                start = dimensionResource(id = R.dimen.padding_large) * 3,
                end = dimensionResource(id = R.dimen.padding_large),
            ),
            text = stringResource(R.string.subscribe_notification),
            style = ExampleTheme.typography.largeBody,
            fontWeight = FontWeight.Bold,
            color = ExampleTheme.colors.primaryText
        )
        Icon(
            modifier = Modifier
                .padding(end = dimensionResource(id = R.dimen.padding_large) * 2)
                .size(
                    if (permissionState.status.isGranted) dimensionResource(id = R.dimen.ordinary_icon)
                    else 0.dp
                ),
            tint = ExampleTheme.colors.accentColor,
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_check_circle),
            contentDescription = stringResource(R.string.checked)
        )
    }
    when (permissionState.status.isGranted) {
        true -> {
            val timeList =
                stringArrayResource(id = R.array.one_notification_time_array).toList()

            ScrollDropdownMenu(
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_large),
                        start = dimensionResource(id = R.dimen.padding_large) * 2,
                        end = dimensionResource(id = R.dimen.padding_large) * 2,
                        bottom = dimensionResource(id = R.dimen.padding_large)
                    ),
                dialogTitle = stringResource(R.string.dialog_setting_notification_title),
                label = stringResource(id = R.string.time).trim(':'),
                items = timeList,
                onItemSelected = { _, item ->
                    settingsViewModel.obtainEvent(
                        SettingsEvent.SelectedTimeShowNotification(
                            item
                        )
                    )
                },
                value = viewState.selectedTimeShowNotification,
                selectedIndex = timeList.indexOf(viewState.selectedTimeShowNotification),
                permissionState = permissionState
            )
            ExHeaderDivider()
        }
        false -> {
            ExHeaderDivider(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_large))
            )
        }
    }
}