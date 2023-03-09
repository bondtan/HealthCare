package com.example.uiexample.ui.navigation

import com.example.uiexample.R

sealed class BottomNavItem(
    val title: String,
    val icon: Int,
    val screen_route: String
){
    object Home: BottomNavItem ("Home", R.drawable.ic_home, "home")
    object Pressure: BottomNavItem ("Pressure", R.drawable.ic_heart, "pressure")
    object Pulse: BottomNavItem ("Pulse", R.drawable.ic_show_chart, "pulse")
    object Settings: BottomNavItem ("Settings", R.drawable.ic_settings, "settings")
}

