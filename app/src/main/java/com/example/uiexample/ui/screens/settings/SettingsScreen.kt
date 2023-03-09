package com.example.uiexample.ui.screens.settings

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.uiexample.R
import com.example.uiexample.ui.components.*
import com.example.uiexample.ui.screens.settings.models.SettingsEvent
import com.example.uiexample.ui.screens.settings.models.SettingsViewState
import com.example.uiexample.ui.screens.settings.views.*
import com.example.uiexample.ui.theme.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel,
    permissionState: PermissionState,
) {
    val viewState: SettingsViewState by settingsViewModel.viewState.collectAsStateWithLifecycle()
    var selectedIndex by remember { mutableStateOf(-1) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ExampleTheme.colors.primaryBackground)
            .verticalScroll(rememberScrollState())
    ) {
        ExHeaderIconText(
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.padding_large),
                    start = dimensionResource(id = R.dimen.padding_large),
                    end = dimensionResource(id = R.dimen.padding_large),
                    bottom = dimensionResource(id = R.dimen.padding_small)
                ),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_settings),
            contentDescription = stringResource(id = R.string.settings),
            title = stringResource(id = R.string.pulse)
        )
        ExHeaderDivider()
        ExMobileBox(
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.padding_large))
                .align(Alignment.CenterHorizontally)
        )
        ExHeaderDivider()
        ExSubtitleText(text = stringResource(R.string.theme))
        ExSettingsRow {
            ExModeBox(
                modifier = Modifier.padding(end = dimensionResource(id = R.dimen.padding_large) * 2),
                color = Color.White,
                enabled = viewState.selectedMode == ExampleMode.LIGHT,
                clickModeBox = { settingsViewModel.obtainEvent(SettingsEvent.SelectedMode(true)) },
                text = stringResource(R.string.light_mode),
                textColor = Color.Black
            )
            ExModeBox(
                color = Color.DarkGray,
                enabled = viewState.selectedMode == ExampleMode.DARK,
                clickModeBox = { settingsViewModel.obtainEvent(SettingsEvent.SelectedMode(false)) },
                text = stringResource(R.string.dark_mode),
                textColor = Color.White
            )
        }
        ExHeaderDivider()
        ExSubtitleText(text = stringResource(R.string.color))
        ExSettingsRow {
            ExColorBox(
                primaryColor = blueLightPalette.primaryBackground,
                secondaryColor = blueLightPalette.secondaryBackground,
                accentColor = blueLightPalette.accentColor,
                enabled = viewState.selectedColor == ExampleColorStyle.BLUE,
                clickColorBox = {
                    settingsViewModel.obtainEvent(
                        SettingsEvent.SelectedColor(ExampleColorStyle.BLUE)
                    )
                }
            )
            ExColorBox(
                primaryColor = yellowLightPalette.primaryBackground,
                secondaryColor = yellowLightPalette.secondaryBackground,
                accentColor = yellowLightPalette.accentColor,
                enabled = viewState.selectedColor == ExampleColorStyle.YELLOW,
                clickColorBox = {
                    settingsViewModel.obtainEvent(
                        SettingsEvent.SelectedColor(ExampleColorStyle.YELLOW)
                    )
                }
            )
        }
        ExSettingsRow {
            ExColorBox(
                primaryColor = greyLightPalette.primaryBackground,
                secondaryColor = greyLightPalette.secondaryBackground,
                accentColor = greyLightPalette.accentColor,
                enabled = viewState.selectedColor == ExampleColorStyle.GRAY,
                clickColorBox = {
                    settingsViewModel.obtainEvent(
                        SettingsEvent.SelectedColor(ExampleColorStyle.GRAY)
                    )
                }
            )
            ExColorBox(
                primaryColor = purpleLightPalette.primaryBackground,
                secondaryColor = purpleLightPalette.secondaryBackground,
                accentColor = purpleLightPalette.accentColor,
                enabled = viewState.selectedColor == ExampleColorStyle.PURPLE,
                clickColorBox = {
                    settingsViewModel.obtainEvent(
                        SettingsEvent.SelectedColor(ExampleColorStyle.PURPLE)
                    )
                }
            )
        }
        ExHeaderDivider()
        ExSubtitleText(text = stringResource(R.string.text_size))
        ExSettingsRow {
            ExTextBox(style = smallTypography.largeBody,
                enabled = viewState.selectedSizeText == ExampleSize.SMALL,
                clickTextBox = {
                    settingsViewModel.obtainEvent(
                        SettingsEvent.SelectedSizeText(
                            ExampleSize.SMALL
                        )
                    )
                })
            ExTextBox(style = bigTypography.largeBody,
                enabled = viewState.selectedSizeText == ExampleSize.BIG,
                clickTextBox = {
                    settingsViewModel.obtainEvent(
                        SettingsEvent.SelectedSizeText(
                            ExampleSize.BIG
                        )
                    )
                })
        }
        ExHeaderDivider()
        ExSubtitleText(text = stringResource(R.string.view_shape))
        ExSettingsRow {
            ExShapeBox(
                shape = shapeRounded.cornerStyle,
                enabled = viewState.selectedShape == ExampleShapeStyle.ROUNDED,
                text = stringResource(R.string.shape_round),
                clickShapeBox = {
                    settingsViewModel.obtainEvent(
                        SettingsEvent.SelectedShape(ExampleShapeStyle.ROUNDED)
                    )
                }
            )
            ExShapeBox(
                shape = shapeSquire.cornerStyle,
                enabled = viewState.selectedShape == ExampleShapeStyle.SQUIRE,
                text = stringResource(R.string.shape_squire),
                clickShapeBox = {
                    settingsViewModel.obtainEvent(
                        SettingsEvent.SelectedShape(ExampleShapeStyle.SQUIRE)
                    )
                }
            )
        }
        ExHeaderDivider()
        ExSubtitleText(text = stringResource(R.string.notification))
        ExSubscribeNotification(
            permissionState = permissionState,
            settingsViewModel = settingsViewModel,
            viewState = viewState)

        LaunchedEffect(key1 = Unit, block = {
            settingsViewModel.getAppSettings()
            settingsViewModel.getNotificationSettings()
        })
    }
}