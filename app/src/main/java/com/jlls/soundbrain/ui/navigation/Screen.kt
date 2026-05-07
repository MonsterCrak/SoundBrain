package com.jlls.soundbrain.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Navigation routes for the app.
 * Type-safe navigation destinations.
 */
sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Inventory : Screen("inventory")
    data object Analytics : Screen("analytics")
    data object Profile : Screen("profile")
    data object ProductDetail : Screen("product/{productId}") {
        fun createRoute(productId: String) = "product/$productId"
    }
}

/**
 * Bottom navigation items configuration.
 * Defines the 4 main tabs: Home, Inventory, Analytics, Profile.
 */
object BottomNavItems {
    val items = listOf(
        BottomNavItem(
            route = Screen.Home.route,
            label = "Home",
            icon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home
        ),
        BottomNavItem(
            route = Screen.Inventory.route,
            label = "Inventory",
            icon = Icons.Outlined.Inventory2,
            selectedIcon = Icons.Filled.Inventory2
        ),
        BottomNavItem(
            route = Screen.Analytics.route,
            label = "Analytics",
            icon = Icons.Outlined.Analytics,
            selectedIcon = Icons.Filled.Analytics
        ),
        BottomNavItem(
            route = Screen.Profile.route,
            label = "Profile",
            icon = Icons.Outlined.Person,
            selectedIcon = Icons.Filled.Person
        )
    )
}

/**
 * Data class representing a bottom navigation item.
 * @property route Navigation route
 * @property label Display label
 * @property icon Unselected icon
 * @property selectedIcon Selected icon
 */
data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector
)