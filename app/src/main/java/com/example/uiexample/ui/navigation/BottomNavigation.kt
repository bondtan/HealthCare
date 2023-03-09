package com.example.uiexample.ui.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.uiexample.ui.theme.ExampleTheme

@Composable
fun BottomNavigation(
    navController: NavController,
    modifier: Modifier = Modifier,
    backgroundColor: Color

) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Pressure,
        BottomNavItem.Pulse,
        BottomNavItem.Settings
    )
    BottomNavigation(
        backgroundColor = backgroundColor,
        modifier = modifier.navigationBarsPadding(),
        contentColor = ExampleTheme.colors.primaryText,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = ExampleTheme.typography.bottomNav
                    )
                },
                selectedContentColor = ExampleTheme.colors.primaryText,
                unselectedContentColor = ExampleTheme.colors.primaryText.copy(alpha = 0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}