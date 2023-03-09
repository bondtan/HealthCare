package com.example.uiexample.ui.screens.mainscreen

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uiexample.data.prefsstore.PrefsStore
import com.example.uiexample.ui.MainViewModel
import com.example.uiexample.ui.components.RequestNotificationPermissionDialog
import com.example.uiexample.ui.navigation.BottomNavItem
import com.example.uiexample.ui.navigation.BottomNavigation
import com.example.uiexample.ui.screens.home.HomeScreen
import com.example.uiexample.ui.screens.home.HomeViewModel
import com.example.uiexample.ui.screens.pressure.PressureScreen
import com.example.uiexample.ui.screens.pressure.PressureViewModel
import com.example.uiexample.ui.screens.pulse.PulseScreen
import com.example.uiexample.ui.screens.pulse.PulseViewModel
import com.example.uiexample.ui.screens.settings.SettingsScreen
import com.example.uiexample.ui.screens.settings.SettingsViewModel
import com.example.uiexample.ui.theme.ExampleTheme
import com.example.uiexample.ui.theme.UIExampleApplicationTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineDispatcher

@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainScreenView(
    mainViewModel: MainViewModel,
    prefsStore: PrefsStore,
    ioDispatcher: CoroutineDispatcher
) {
    val viewModel = remember { MainViewModel(prefsStore, ioDispatcher) }
    val applicationState = viewModel.applicationsSettings.collectAsStateWithLifecycle()

    UIExampleApplicationTheme(
        colorStyle = applicationState.value.color,
        textSize = applicationState.value.sizeText,
        shapeCorners = applicationState.value.shape,
        darkTheme = applicationState.value.isDarkMode
    ) {

        val permissionState = rememberPermissionState(
            permission = Manifest.permission.POST_NOTIFICATIONS
        )

        RequestNotificationPermissionDialog(
            permissionState = permissionState
        )

        val systemUiController = rememberSystemUiController()
        val systemBarColor = ExampleTheme.colors.secondaryBackground
        val isUiControllerDark = !(applicationState.value.isDarkMode)

        /**
         * Set status bar color
         */
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = systemBarColor,
                darkIcons = isUiControllerDark
            )
        }

        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                BottomNavigation(
                    navController = navController,
                    backgroundColor = systemBarColor
                )
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = BottomNavItem.Home.screen_route,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(BottomNavItem.Home.screen_route) {
                    val homeViewModel = hiltViewModel<HomeViewModel>()
                    HomeScreen(
                        homeViewModel = homeViewModel,
                        navController = navController
                    )
                }
                composable(BottomNavItem.Pressure.screen_route) {
                    val pressureViewModel = hiltViewModel<PressureViewModel>()
                    PressureScreen(pressureViewModel = pressureViewModel)
                }
                composable(BottomNavItem.Pulse.screen_route) {
                    val pulseViewModel = hiltViewModel<PulseViewModel>()
                    PulseScreen(pulseViewModel = pulseViewModel)
                }
                composable(BottomNavItem.Settings.screen_route) {
                    val settingsViewModel = hiltViewModel<SettingsViewModel>()
                    SettingsScreen(
                        settingsViewModel = settingsViewModel,
                        permissionState = permissionState,
                    )
                }
            }
        }
    }
    LaunchedEffect(key1 = viewModel, block = {
        mainViewModel.getApplicationSettings()
    })
}